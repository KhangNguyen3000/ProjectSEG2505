package com.example.nguye.seg2505app.Storables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguye.seg2505app.MyDBHandler;

import java.util.ArrayList;

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

//    // Constructor
//    public OfferedService (Context context) {
//        this.context = context;
//    }

    // Constructor
    public OfferedService (int typeID, int providerID, double hourlyRate) {
        this.typeID = typeID;
        this.providerID = providerID;
        this.hourlyRate = hourlyRate;
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

    /**
     * Search for the record that has the specified value in the specified field in the table "Services".
     * @param fieldName Name of the column to look into
     * @param value Value to search for
     * @return The first record that matches.
     */
    public OfferedService find(Context context, String fieldName, Object value, boolean quotedValue) {
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String quotes = "";
        if (quotedValue) {
            quotes = "\"";
        }
        String query = "SELECT * FROM " + this.getTableName()
                + " WHERE " + fieldName + " = " + quotes + value + quotes;
        System.out.println(query);

        // Store the item's data into a Storable using a cursor
        Cursor cursor = db.rawQuery(query, null);
        OfferedService service = null;
        if(cursor.moveToFirst()) {
            service = new OfferedService();
            service.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
            service.setTypeID(cursor.getInt(getFieldIndex(COL_TYPE, COLUMNS)));
            service.setProviderID(cursor.getInt(getFieldIndex(COL_PROVIDER, COLUMNS)));
            service.setHourlyRate(cursor.getDouble(getFieldIndex(COL_HOURLYRATE, COLUMNS)));
        }
        db.close();
        return service;
    }

    public  static ArrayList<OfferedService> findAll(Context context, String fieldName, Object value, boolean quotedValue){
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String quotes = "";
        if (quotedValue) {
            quotes = "\"";
        }
        String query = "SELECT * FROM " + OfferedService.TABLE_NAME
                + " WHERE " + fieldName + " = " + quotes + value + quotes;
        System.out.println(query);

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<OfferedService> serviceList = new ArrayList<>();
        OfferedService service = null;
        while(cursor.moveToNext()){
            service = new OfferedService();
            service.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
            service.setTypeID(cursor.getInt(getFieldIndex(COL_TYPE, COLUMNS)));
            service.setProviderID(cursor.getInt(getFieldIndex(COL_PROVIDER, COLUMNS)));
            service.setHourlyRate(cursor.getDouble(getFieldIndex(COL_HOURLYRATE, COLUMNS)));
            serviceList.add(service);
        }
        db.close();
        return serviceList;
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

