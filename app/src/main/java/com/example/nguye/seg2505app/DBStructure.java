package com.example.nguye.seg2505app;

public class DBStructure {

    public String tableName;
    public String[] columnNames;
    public String[] columnTypes;

    public DBStructure(String tableName, int numOfCols) {
        this.tableName = tableName;
        this.columnNames = new String[numOfCols];
        this.columnTypes = new String[numOfCols];
    }
}
