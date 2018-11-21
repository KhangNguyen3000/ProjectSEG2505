package com.example.nguye.seg2505app.Storables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguye.seg2505app.MyDBHandler;

import java.util.ArrayList;

/**
 * Service types established by the administrator.
 * The providers will be able to offer one or more of those services
 */
public class ServiceType extends Storable {

    // Structure of the table "ServiceTypes"
    public static final String TABLE_NAME = "ServiceTypes";
    public static final String COL_NAME = "Name";
    public static final String COL_MAXRATE = "MaxHourlyRate";
    public static final ArrayList<String[]> COLUMNS = new ArrayList<>();
    static {
        COLUMNS.add(new String[] {COL_ID, "INTEGER PRIMARY KEY AUTOINCREMENT"});
        COLUMNS.add(new String[] {COL_NAME, "TEXT"});
        COLUMNS.add(new String[] {COL_MAXRATE, "DOUBLE"});
    }

    // Attributes
    private String name;
    private double maxRate; // Max hourly rate of the service. The provider will not be able to set a higher hourly rate.

    // Constructor
    public ServiceType() {
    }

//    // Constructor
//    public ServiceType(Context context) {
//        this.context = context;
//    }

    // Constructor
    public ServiceType(String name, double maxRate){
        this.name = name;
        this.maxRate = maxRate;
    }

    // Constructor
    public ServiceType(int ID, String name, double maxRate){
        this.ID = ID;
        this.name = name;
        this.maxRate = maxRate;
    }

    /**
     * Put the ServiceType's attributes into a ContentValues object to use with database functions
     * @return the ContentValues object
     */
    public ContentValues valuePutter() {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, getName());
        values.put(COL_MAXRATE, getMaxRate());
        return values;
    }

    /**
     * Search for the record that has the specified value in the specified field in the table "Services".
     * @param fieldName Name of the column to look into
     * @param value Value to search for
     * @return The first record that matches.
     */
    public ServiceType find(Context context, String fieldName, Object value, boolean quotedValue) {
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
        ServiceType service = null;
        if(cursor.moveToFirst()) {
            service = new ServiceType();
            service.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
            service.setName(cursor.getString(getFieldIndex(COL_NAME, COLUMNS)));
            service.setMaxRate(cursor.getDouble(getFieldIndex(COL_MAXRATE, COLUMNS)));
        }
        db.close();
        return service;
    }

    // Getters and setters
    public String getTableName() { return TABLE_NAME; }

    public String getName(){ return name; }
    public void setName(String name) { this.name = name; }

    public double getMaxRate(){ return maxRate; }
    public void setMaxRate(double maxRate) { this.maxRate = maxRate; }

}
