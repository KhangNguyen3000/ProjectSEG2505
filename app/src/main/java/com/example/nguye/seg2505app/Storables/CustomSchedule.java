package com.example.nguye.seg2505app.Storables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.ScheduleClasses.ScheduleState;

import java.util.ArrayList;

public class CustomSchedule extends Storable {

    // Structure of the table "DefaultSchedules"
    public static final String TABLE_NAME = "CustomSchedules";
    public static final String COL_PROVIDER = "ProviderID";
    public static final String COL_DATE = "Date";
    public static final String COL_START = "Start";
    public static final String COL_END = "End";
    public static final String COL_AVAILABILITY = "Availability";

    public static final ArrayList<String[]> COLUMNS = new ArrayList<>();
    static {
        COLUMNS.add(new String[] {COL_ID, "INTEGER PRIMARY KEY AUTOINCREMENT"});
        COLUMNS.add(new String[] {COL_PROVIDER, "INTEGER"});
        COLUMNS.add(new String[] {COL_DATE, "TEXT"});
        COLUMNS.add(new String[] {COL_START, "INTEGER"});
        COLUMNS.add(new String[] {COL_END, "INTEGER"});
        COLUMNS.add(new String[] {COL_AVAILABILITY, "TEXT"});
    }

    // Attributes
    private int providerID;
    private String date;
    private int startTime;
    private int endTime;
    private ScheduleState state;

    // Constructor (full)
    public CustomSchedule(int providerID, String date, int startTime, int endTime, ScheduleState state) {
        this.providerID = providerID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }

    /**
     * Put the DefaultSchedule's attributes into a ContentValues object to use with database functions
     * @return the ContentValues object
     */
    public ContentValues valuePutter() {
        ContentValues values = new ContentValues();
        values.put(COL_PROVIDER, getProviderID());
        values.put(COL_DATE, getDate());
        values.put(COL_START, getStartTime());
        values.put(COL_END, getEndTime());
        values.put(COL_AVAILABILITY, getState().toString());
        return values;
    }

    // Getters and setters
    public String getTableName() { return this.TABLE_NAME; }

    public int getProviderID() { return this.providerID; }
//    public void setProviderID(int providerID) { this.providerID = providerID; }

    public String getDate() { return this.date; }
    public void setDate(String date) { this.date = date; }

    public int getStartTime() { return this.startTime; }
    public void setStartTime(int startTime) { this.startTime = startTime; }

    public int getEndTime() { return this.endTime; }
    public void setEndTime(int endTime) { this.endTime = endTime; }

    public ScheduleState getState() { return this.state; }
    public void setState(ScheduleState state) { this.state = state; }


}
