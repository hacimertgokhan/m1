package io.github.hacimertgokhan.m1.core; 

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
                info("Kurtarma işlemi tamamlandı.");
                checkpoint();
            }
        } catch (IOException e) {
            throw new SQLException("Log dosyasından kurtarma başarısız oldu.", e);
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
        Matcher matcher = PATTERN_SELECT.matcher(sql.trim());
        if (matcher.matches()) {
            String columns = matcher.group(1);
            String tableName = matcher.group(2).toUpperCase();
            String whereClause = matcher.group(3);
            Table table = tables.get(tableName);
            if (table == null) throw new SQLException("No table found with this argument: " + tableName);
            if (!columns.trim().equals("*")) throw new SQLException("Şimdilik sadece 'SELECT *' desteklenmektedir.");
            Table resultTable = new Table("RESULT");
            table.getColumnNames().forEach(resultTable::addColumn);
            for(List<Object> row : table.getRows()) {
                if(whereClause == null || rowMatches(table, row, whereClause)){
                    resultTable.addRow(row);
                }
            }
            return resultTable;
        }
        throw new SQLException("Unknow or unsupported select query: " + sql);
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