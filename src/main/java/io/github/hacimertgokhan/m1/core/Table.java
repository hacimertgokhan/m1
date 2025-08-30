package io.github.hacimertgokhan.m1.core;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table implements Serializable {
    private final String name;
    private final List<String> columnNames = new ArrayList<>();
    private final List<List<Object>> rows = new ArrayList<>();
    private final Map<Object, List<Integer>> index = new HashMap<>();
    private String indexedColumn = null;

    private static final long serialVersionUID = 1L;


    public Table(String name) {
        this.name = name.toUpperCase();
    }

    public void addColumn(String columnName) {
        this.columnNames.add(columnName.toUpperCase());
    }

    public void addRow(List<Object> row) {
        if (row.size() != columnNames.size()) {
            throw new IllegalArgumentException("Row size is not matching with column size (" + row.size() + " row), ("+ columnNames.size() +" column).");
        }
        this.rows.add(new ArrayList<>(row));
    }

    public String getName() {
        return name;
    }

    public List<String> getColumnNames() {
        return new ArrayList<>(columnNames);
    }

    public List<List<Object>> getRows() {
        return new ArrayList<>(rows);
    }

    public void createIndex(String columnName) {
        this.indexedColumn = columnName.toUpperCase();
        int colIndex = columnNames.indexOf(this.indexedColumn);
        if (colIndex == -1) throw new IllegalArgumentException("No column found with specified column name: " + columnName);

        index.clear();
        for (int i = 0; i < rows.size(); i++) {
            Object value = rows.get(i).get(colIndex);
            index.computeIfAbsent(value, k -> new ArrayList<>()).add(i);
        }
    }

    protected List<List<Object>> getInternalRows() {
        return this.rows;
    }
}