package io.github.hacimertgokhan.m1.core; 

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static io.github.hacimertgokhan.m1.tools.M1Logger.*;

public class Database {
    private static final Map<String, Database> instances = new ConcurrentHashMap<>();
    private final StorageManager storageManager;
    private final WriteAheadLog wal;
    private final Map<String, Table> tables;

    private static final Pattern PATTERN_SELECT = Pattern.compile("SELECT\\s+(.+)\\s+FROM\\s+(\\w+)(?:\\s+WHERE\\s+(.+))?", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_CREATE_TABLE = Pattern.compile("CREATE\\s+TABLE\\s+(\\w+)\\s*\\((.+)\\)", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_INSERT = Pattern.compile("INSERT\\s+INTO\\s+(\\w+)\\s+VALUES\\s*\\((.+)\\)", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_UPDATE = Pattern.compile("UPDATE\\s+(\\w+)\\s+SET\\s+(.+?)\\s*(?:WHERE\\s+(.+))?", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_DELETE = Pattern.compile("DELETE\\s+FROM\\s+(\\w+)(?:\\s+WHERE\\s+(.+))?", Pattern.CASE_INSENSITIVE);
    private final AtomicInteger changesSinceLastCheckpoint = new AtomicInteger(0);
    private static final int CHECKPOINT_THRESHOLD = 100;

    
    private Database(String filePath) throws SQLException {
        this.storageManager = new StorageManager(filePath);
        try {
            this.wal = new WriteAheadLog(filePath);
        } catch (IOException e) {
            throw new SQLException("Write-Ahead Log cannot started.", e);
        }

        Map<String, Table> loadedTables;
        try {
            loadedTables = this.storageManager.load();
            info("'" + filePath + "' loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            error("An error occured while loading database file: " + e.getMessage() + ". Starting with empty database file.");
            loadedTables = new ConcurrentHashMap<>();
        }

        
        this.tables = loadedTables;

        
        recoverFromLog();
    }

    public static synchronized Database getInstance(String filePath) throws SQLException {
        if (!instances.containsKey(filePath)) {
            instances.put(filePath, new Database(filePath));
        }
        return instances.get(filePath);
    }

    private void recoverFromLog() throws SQLException {
        try {
            List<String> commandsToRecover = wal.recover();
            if (!commandsToRecover.isEmpty()) {
                info(commandsToRecover.size() + " process loading from log file...");
                for (String sql : commandsToRecover) {
                    executeUpdateInternal(sql, false);
                }
                info("Recovered.");
                checkpoint();
            }
        } catch (IOException e) {
            throw new SQLException("Cannot recovered.", e);
        }
    }

    private synchronized void checkpoint() throws SQLException {
        info("Starting checkpoint...");
        try {
            storageManager.save(this.tables);
            wal.clear();
            changesSinceLastCheckpoint.set(0);
            info("Checkpoint done.");
        } catch (IOException e) {
            throw new SQLException("Checkpoint action failed.", e);
        }
    }

    public Table executeQuery(String sql) throws SQLException {
        // Null ve boş string kontrolü
        if (sql == null || sql.trim().isEmpty()) {
            throw new SQLException("SQL query cannot be null or empty");
        }

        String trimmedSql = sql.trim();
        Matcher matcher = PATTERN_SELECT.matcher(trimmedSql);

        if (!matcher.matches()) {
            throw new SQLException("Unknown or unsupported select query: " + sql);
        }

        try {
            String columns = matcher.group(1).trim();
            String tableName = matcher.group(2).trim().toUpperCase();
            String whereClause = matcher.group(3);

            // Tablo var mı kontrolü
            Table table = tables.get(tableName);
            if (table == null) {
                throw new SQLException("Table not found: " + tableName);
            }

            // Sonuç tablosu oluşturma
            Table resultTable = createResultTable(table, columns);

            // Satırları filtreleme ve ekleme
            filterAndAddRows(table, resultTable, whereClause);

            return resultTable;

        } catch (IndexOutOfBoundsException e) {
            throw new SQLException("Invalid SQL syntax: " + sql, e);
        } catch (Exception e) {
            throw new SQLException("Error executing query: " + e.getMessage(), e);
        }
    }

    /**
     * Sonuç tablosunu oluşturur ve sütunları belirler
     */
    private Table createResultTable(Table sourceTable, String columns) throws SQLException {
        Table resultTable = new Table("RESULT");

        if ("*".equals(columns)) {
            // Tüm sütunları ekle
            sourceTable.getColumnNames().forEach(resultTable::addColumn);
        } else {
            // Belirli sütunları ekle
            String[] selectedColumns = columns.split("\\s*,\\s*");
            List<String> availableColumns = sourceTable.getColumnNames();

            for (String column : selectedColumns) {
                String cleanColumn = column.trim();
                if (availableColumns.contains(cleanColumn)) {
                    resultTable.addColumn(cleanColumn);
                } else {
                    throw new SQLException("Column not found: " + cleanColumn);
                }
            }
        }

        return resultTable;
    }

    /**
     * Satırları filtreler ve sonuç tablosuna ekler
     */
    private void filterAndAddRows(Table sourceTable, Table resultTable, String whereClause) {
        List<String> sourceColumns = sourceTable.getColumnNames();
        List<String> resultColumns = resultTable.getColumnNames();

        for (List<Object> row : sourceTable.getRows()) {
            if (whereClause == null || rowMatches(sourceTable, row, whereClause)) {
                List<Object> resultRow = new ArrayList<>();

                // Sadece seçilen sütunların değerlerini al
                for (String resultColumn : resultColumns) {
                    int sourceIndex = sourceColumns.indexOf(resultColumn);
                    if (sourceIndex >= 0 && sourceIndex < row.size()) {
                        resultRow.add(row.get(sourceIndex));
                    } else {
                        resultRow.add(null); // Güvenlik için
                    }
                }

                resultTable.addRow(resultRow);
            }
        }
    }

    /**
     * Geliştirilmiş query executor
     */
    public CompletableFuture<Table> executeQueryAsync(String sql) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return executeQuery(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Sayfalama desteği ile query çalıştırma
     */
    public Table executeQueryWithPagination(String sql, int offset, int limit) throws SQLException {
        Table fullResult = executeQuery(sql);

        if (offset < 0 || limit <= 0) {
            throw new SQLException("Invalid pagination parameters");
        }

        Table paginatedResult = new Table("PAGINATED_RESULT");
        fullResult.getColumnNames().forEach(paginatedResult::addColumn);

        List<List<Object>> rows = fullResult.getRows();
        int startIndex = Math.min(offset, rows.size());
        int endIndex = Math.min(offset + limit, rows.size());

        for (int i = startIndex; i < endIndex; i++) {
            paginatedResult.addRow(rows.get(i));
        }

        return paginatedResult;
    }

    /**
     * Query sonucunda kaç satır döneceğini hesaplar (COUNT işlevi)
     */
    public int countRows(String sql) throws SQLException {
        Table result = executeQuery(sql);
        return result.getRows().size();
    }

    /**
     * Caching mekanizması ile query çalıştırma
     */
    private final Map<String, Table> queryCache = new ConcurrentHashMap<>();
    private final long CACHE_EXPIRY_MS = 300000; // 5 dakika
    private final Map<String, Long> cacheTimestamps = new ConcurrentHashMap<>();

    public Table executeQueryWithCache(String sql) throws SQLException {
        String normalizedSql = sql.trim().toLowerCase();

        // Cache kontrolü
        if (queryCache.containsKey(normalizedSql)) {
            Long timestamp = cacheTimestamps.get(normalizedSql);
            if (timestamp != null && System.currentTimeMillis() - timestamp < CACHE_EXPIRY_MS) {
                return queryCache.get(normalizedSql);
            } else {
                // Cache süresi dolmuş, temizle
                queryCache.remove(normalizedSql);
                cacheTimestamps.remove(normalizedSql);
            }
        }

        // Query'yi çalıştır ve cache'e ekle
        Table result = executeQuery(sql);
        queryCache.put(normalizedSql, result);
        cacheTimestamps.put(normalizedSql, System.currentTimeMillis());

        return result;
    }

    public int executeUpdate(String sql) throws SQLException {
        return executeUpdateInternal(sql, true);
    }

    private int executeUpdateInternal(String sql, boolean shouldLogAndCheckpoint) throws SQLException {
        if (shouldLogAndCheckpoint) {
            try {
                wal.log(sql);
            } catch (IOException e) {
                throw new SQLException("Transaction cancelled, error: ", e);
            }
        }
        sql = sql.trim();
        Matcher matcher;
        int affectedRows = 0;
        if ((matcher = PATTERN_CREATE_TABLE.matcher(sql)).matches()) {
            String tableName = matcher.group(1).toUpperCase();
            String[] columns = matcher.group(2).split(",");
            if (tables.containsKey(tableName)) throw new SQLException("Table already exists: " + tableName);
            Table newTable = new Table(tableName);
            for (String col : columns) {
                newTable.addColumn(col.trim());
            }
            tables.put(tableName, newTable);
            affectedRows = 0;
        }
        else if ((matcher = PATTERN_INSERT.matcher(sql)).matches()) {
            String tableName = matcher.group(1).toUpperCase();
            String[] values = parseValues(matcher.group(2));
            Table table = tables.get(tableName);
            if (table == null) throw new SQLException("Table not found: " + tableName);
            List<Object> rowData = Arrays.stream(values).map(this::autoParseType).collect(Collectors.toList());
            table.addRow(rowData);
            affectedRows = 1;
        }
        else if ((matcher = PATTERN_UPDATE.matcher(sql)).matches()) {
            String tableName = matcher.group(1).toUpperCase();
            String setClause = matcher.group(2);
            String whereClause = matcher.group(3);
            Table table = tables.get(tableName);
            if (table == null) throw new SQLException("Table not found: " + tableName);
            String[] setParts = setClause.split("=");
            if(setParts.length != 2) throw new SQLException("Unknow set argument: " + setClause);
            String colToUpdate = setParts[0].trim().toUpperCase();
            Object newValue = autoParseType(setParts[1].trim());
            int colIndex = table.getColumnNames().indexOf(colToUpdate);
            if (colIndex == -1) throw new SQLException("Column not found: " + colToUpdate);
            int updatedCount = 0;
            for (List<Object> row : table.getInternalRows()) {
                if (whereClause == null || rowMatches(table, row, whereClause)) {
                    row.set(colIndex, newValue);
                    updatedCount++;
                }
            }
            affectedRows = updatedCount;
        }
        else if ((matcher = PATTERN_DELETE.matcher(sql)).matches()) {
            String tableName = matcher.group(1).toUpperCase();
            String whereClause = matcher.group(2);
            Table table = tables.get(tableName);
            if (table == null) throw new SQLException("Table not found: " + tableName);
            int initialSize = table.getInternalRows().size();
            table.getInternalRows().removeIf(row -> whereClause == null || rowMatches(table, row, whereClause));
            affectedRows = initialSize - table.getInternalRows().size();
        }
        else {
            throw new SQLException("Unsupported DML/DDL command: " + sql);
        }
        if (shouldLogAndCheckpoint && affectedRows > 0) {
            if (changesSinceLastCheckpoint.incrementAndGet() >= CHECKPOINT_THRESHOLD) {
                checkpoint();
            }
        }
        return affectedRows;
    }

    public void shutdown() throws SQLException {
        info("M1 shutting down...");
        checkpoint();
        try {
            wal.close();
        } catch (IOException e) {
            error("An error occured while shutting down WAL, Error: " + e.getMessage());
        }
    }

    private boolean rowMatches(Table table, List<Object> row, String whereClause) {
        try {
            String[] parts = whereClause.split("=");
            if(parts.length != 2) return false;
            String columnName = parts[0].trim().toUpperCase();
            Object expectedValue = autoParseType(parts[1].trim());
            int colIndex = table.getColumnNames().indexOf(columnName);
            if (colIndex == -1) return false;
            Object actualValue = row.get(colIndex);
            return actualValue != null && actualValue.equals(expectedValue);
        } catch (Exception e) {
            return false;
        }
    }

    private Object autoParseType(String value) {
        value = value.trim();
        if (value.startsWith("'") && value.endsWith("'")) {
            return value.substring(1, value.length() - 1);
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    private String[] parseValues(String valuesString) {
        return Arrays.stream(valuesString.split(","))
                .map(String::trim)
                .toArray(String[]::new);
    }
    public java.util.Set<String> getTableNames() {
        return new java.util.HashSet<>(this.tables.keySet());
    }
}