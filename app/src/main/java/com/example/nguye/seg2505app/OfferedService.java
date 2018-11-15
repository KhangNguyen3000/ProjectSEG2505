package com.example.nguye.seg2505app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OfferedService extends Storable {

    // Structure of the table "Services"
    public static final String TABLE_NAME = "Services";
    public static final String COL_TYPE = "Type";
    public static final String COL_PROVIDER = "Provider";
    public static final String COL_HOURLYRATE = "HourlyRate";
    public static final ArrayList<String[]> COLUMNS = new ArrayList<>();
    static {
        COLUMNS.add(new String[] {COL_ID, "INTEGER PRIMARY KEY AUTOINCREMENT"});
        COLUMNS.add(new String[] {COL_TYPE, "INTEGER"});
        COLUMNS.add(new String[] {COL_PROVIDER, "INTEGER"});
        COLUMNS.add(new String[] {COL_HOURLYRATE, "DOUBLE"});
    }

    // Attributes
    private int typeID;
    private int providerID;
    private double hourlyRate;

    // Constructor
    public OfferedService () {
    }

    // Constructor
    public OfferedService (Context context) {
        this.context = context;
    }

    // Constructor
    public OfferedService (Context context, int typeID, int providerID, double hourlyRate) {
        this.context = context;
        this.typeID = typeID;
        this.providerID = providerID;
        this.hourlyRate = hourlyRate;
    }

    /**
     * Use a cursor to store a record into an OfferedService object
     * @param db
     * @param cursor
     * @return the created OfferedService. Its attributes will be null if the record is not found.
     */
    public OfferedService cursorHandler(SQLiteDatabase db, Cursor cursor) {
        OfferedService service = new OfferedService(this.getContext());
        if(cursor.moveToFirst()) {
            service.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
            service.setTypeID(cursor.getInt(getFieldIndex(COL_TYPE, COLUMNS)));
            service.setProviderID(cursor.getInt(getFieldIndex(COL_PROVIDER, COLUMNS)));
            service.setHourlyRate(cursor.getDouble(getFieldIndex(COL_HOURLYRATE, COLUMNS)));
        }
        return service;
    }

    /**
     * Put the OfferedService's attributes into a ContentValues object to use with database functions
     * @return the ContentValues object
     */
    public ContentValues valuePutter() {
        ContentValues values = new ContentValues();
        values.put(COL_TYPE, getTypeID());
        values.put(COL_PROVIDER, getProviderID());
        values.put(COL_HOURLYRATE, getHourlyRate());
        return values;
    }

    // Getters and setters
    public String getTableName() { return TABLE_NAME; }

    public int getTypeID() { return this.typeID; }
    public void setTypeID(int typeID) { this.typeID = typeID; }

    public int getProviderID() { return providerID; }
    public void setProviderID(int providerID) { this.providerID = providerID; }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }
}

