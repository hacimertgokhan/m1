package io.github.hacimertgokhan.m1.core; // Paket adını kendi projeninkine göre kontrol et

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

    // --- DOĞRU SIRALAMAYA SAHİP CONSTRUCTOR ---
    private Database(String filePath) throws SQLException {
        this.storageManager = new StorageManager(filePath);
        try {
            this.wal = new WriteAheadLog(filePath);
        } catch (IOException e) {
            throw new SQLException("Write-Ahead Log başlatılamadı.", e);
        }

        Map<String, Table> loadedTables;
        try {
            loadedTables = this.storageManager.load();
            System.out.println("Veritabanı ana dosyası '" + filePath + "' başarıyla yüklendi.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Veritabanı ana dosyası yüklenirken hata: " + e.getMessage() + ". Boş bir veritabanı ile devam ediliyor.");
            loadedTables = new ConcurrentHashMap<>();
        }

        // 1. ADIM: ÖNCE `this.tables` değişkenine değerini ata (TEMELİ AT)
        this.tables = loadedTables;

        // 2. ADIM: ARTIK `this.tables` null olmadığı için, onu kullanan metodu güvenle çağır (EŞYALARI TAŞI)
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
                System.out.println(commandsToRecover.size() + " adet işlem log dosyasından kurtarılıyor...");
                for (String sql : commandsToRecover) {
                    executeUpdateInternal(sql, false);
                }
                System.out.println("Kurtarma işlemi tamamlandı.");
                checkpoint();
            }
        } catch (IOException e) {
            throw new SQLException("Log dosyasından kurtarma başarısız oldu.", e);
        }
    }

    private synchronized void checkpoint() throws SQLException {
        System.out.println("Checkpoint başlatılıyor...");
        try {
            storageManager.save(this.tables);
            wal.clear();
            changesSinceLastCheckpoint.set(0);
            System.out.println("Checkpoint tamamlandı.");
        } catch (IOException e) {
            throw new SQLException("Checkpoint işlemi başarısız oldu.", e);
        }
    }

    public Table executeQuery(String sql) throws SQLException {
        Matcher matcher = PATTERN_SELECT.matcher(sql.trim());
        if (matcher.matches()) {
            String columns = matcher.group(1);
            String tableName = matcher.group(2).toUpperCase();
            String whereClause = matcher.group(3);
            Table table = tables.get(tableName);
            if (table == null) throw new SQLException("Tablo bulunamadı: " + tableName);
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
        throw new SQLException("Desteklenmeyen veya geçersiz SELECT sorgusu: " + sql);
    }

    public int executeUpdate(String sql) throws SQLException {
        return executeUpdateInternal(sql, true);
    }

    private int executeUpdateInternal(String sql, boolean shouldLogAndCheckpoint) throws SQLException {
        if (shouldLogAndCheckpoint) {
            try {
                wal.log(sql);
            } catch (IOException e) {
                throw new SQLException("İşlem log dosyasına yazılamadı. Veritabanı değişikliği iptal edildi.", e);
            }
        }
        sql = sql.trim();
        Matcher matcher;
        int affectedRows = 0;
        if ((matcher = PATTERN_CREATE_TABLE.matcher(sql)).matches()) {
            String tableName = matcher.group(1).toUpperCase();
            String[] columns = matcher.group(2).split(",");
            if (tables.containsKey(tableName)) throw new SQLException("Tablo zaten var: " + tableName);
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
            if (table == null) throw new SQLException("Tablo bulunamadı: " + tableName);
            List<Object> rowData = Arrays.stream(values).map(this::autoParseType).collect(Collectors.toList());
            table.addRow(rowData);
            affectedRows = 1;
        }
        else if ((matcher = PATTERN_UPDATE.matcher(sql)).matches()) {
            String tableName = matcher.group(1).toUpperCase();
            String setClause = matcher.group(2);
            String whereClause = matcher.group(3);
            Table table = tables.get(tableName);
            if (table == null) throw new SQLException("Tablo bulunamadı: " + tableName);
            String[] setParts = setClause.split("=");
            if(setParts.length != 2) throw new SQLException("Geçersiz SET ifadesi: " + setClause);
            String colToUpdate = setParts[0].trim().toUpperCase();
            Object newValue = autoParseType(setParts[1].trim());
            int colIndex = table.getColumnNames().indexOf(colToUpdate);
            if (colIndex == -1) throw new SQLException("Kolon bulunamadı: " + colToUpdate);
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
            if (table == null) throw new SQLException("Tablo bulunamadı: " + tableName);
            int initialSize = table.getInternalRows().size();
            table.getInternalRows().removeIf(row -> whereClause == null || rowMatches(table, row, whereClause));
            affectedRows = initialSize - table.getInternalRows().size();
        }
        else {
            throw new SQLException("Desteklenmeyen veya geçersiz DML/DDL komutu: " + sql);
        }
        if (shouldLogAndCheckpoint && affectedRows > 0) {
            if (changesSinceLastCheckpoint.incrementAndGet() >= CHECKPOINT_THRESHOLD) {
                checkpoint();
            }
        }
        return affectedRows;
    }

    public void shutdown() throws SQLException {
        System.out.println("Veritabanı kapatılıyor, son checkpoint yapılıyor...");
        checkpoint();
        try {
            wal.close();
        } catch (IOException e) {
            System.err.println("WAL kapatılırken hata oluştu: " + e.getMessage());
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