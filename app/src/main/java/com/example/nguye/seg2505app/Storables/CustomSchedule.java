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
        COLUMNS.add(new String[] {COL_START, "TEXT"});
        COLUMNS.add(new String[] {COL_END, "TEXT"});
        COLUMNS.add(new String[] {COL_AVAILABILITY, "TEXT"});
    }

    // Attributes
    private int providerID;
    private String date;
    private String startTime;
    private String endTime;
    private ScheduleState state;

//    private int mondayStart;
//    private int mondayEnd;
//    private int tuesdayStart;
//    private int tuesdayEnd;
//    private int wednesdayStart;
//    private int wednesdayEnd;
//    private int thursdayStart;
//    private int thursdayEnd;
//    private int fridayStart;
//    private int fridayEnd;
//    private int saturdayStart;
//    private int saturdayEnd;
//    private int sundayStart;
//    private int sundayEnd;


    // Constructor (full)
    public CustomSchedule(int providerID, String date, String startTime, String endTime, ScheduleState state) {
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

//    /**
//     * Search for the record that has the specified value in the specified field in the table "CustomSchedules".
//     * @param fieldName Name of the column to look into
//     * @param value Value to search for
//     * @return The first record that matches.
//     */
//    public CustomSchedule find(Context context, String fieldName, Object value, boolean quotedValue) {
//        // Connect to the database
//        MyDBHandler dbHandler = new MyDBHandler(context);
//        SQLiteDatabase db = dbHandler.getReadableDatabase();
//
//        String quotes = "";
//        if (quotedValue) {
//            quotes = "\"";
//        }
//        String query = "SELECT * FROM " + this.getTableName()
//                + " WHERE " + fieldName + " = " + quotes + value + quotes;
//        System.out.println(query);
//
//        // Store the item's data into a Storable using a cursor
//        Cursor cursor = db.rawQuery(query, null);
//        DefaultSchedule schedule = new DefaultSchedule();
//        if(cursor.moveToFirst()) {
//            schedule = new DefaultSchedule();
//            schedule.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
//            schedule.setProviderID(cursor.getInt(getFieldIndex(COL_PROVIDER, COLUMNS)));
//            schedule.setEffectiveDate(cursor.getString(getFieldIndex(COL_EFFECTIVEDATE, COLUMNS)));
//            schedule.setStartTimes(0, cursor.getString(getFieldIndex(COL_MONDAYSTART, COLUMNS)));
//            schedule.setEndTimes(0, cursor.getString(getFieldIndex(COL_MONDAYEND, COLUMNS)));
//            schedule.setStartTimes(1, cursor.getString(getFieldIndex(COL_TUESDAYSTART, COLUMNS)));
//            schedule.setEndTimes(1, cursor.getString(getFieldIndex(COL_TUESDAYEND, COLUMNS)));
//            schedule.setStartTimes(2, cursor.getString(getFieldIndex(COL_WEDNESDAYSTART, COLUMNS)));
//            schedule.setEndTimes(2, cursor.getString(getFieldIndex(COL_WEDNESDAYEND, COLUMNS)));
//            schedule.setStartTimes(3, cursor.getString(getFieldIndex(COL_THURSDAYSTART, COLUMNS)));
//            schedule.setEndTimes(3, cursor.getString(getFieldIndex(COL_THURSDAYEND, COLUMNS)));
//            schedule.setStartTimes(4, cursor.getString(getFieldIndex(COL_FRIDAYSTART, COLUMNS)));
//            schedule.setEndTimes(4, cursor.getString(getFieldIndex(COL_FRIDAYEND, COLUMNS)));
//            schedule.setStartTimes(5, cursor.getString(getFieldIndex(COL_SATURDAYSTART, COLUMNS)));
//            schedule.setEndTimes(5, cursor.getString(getFieldIndex(COL_SATURDAYEND, COLUMNS)));
//            schedule.setStartTimes(6, cursor.getString(getFieldIndex(COL_SUNDAYSTART, COLUMNS)));
//            schedule.setEndTimes(6, cursor.getString(getFieldIndex(COL_SUNDAYEND, COLUMNS)));
//        }
//        db.close();
//        return schedule;
//    }

//    /**
//     * Search for the record that has the specified value in the specified field in the table "CustomSchedules".
//     * @param fieldName Name of the column to look into
//     * @param value Value to search for
//     * @return The first record that matches.
//     */
//    public CustomSchedule findAll(Context context, String fieldName, Object value, boolean quotedValue) {
//        // Connect to the database
//        MyDBHandler dbHandler = new MyDBHandler(context);
//        SQLiteDatabase db = dbHandler.getReadableDatabase();
//
//        String quotes = "";
//        if (quotedValue) {
//            quotes = "\"";
//        }
//        String query = "SELECT * FROM " + this.getTableName()
//                + " WHERE " + fieldName + " = " + quotes + value + quotes;
//        System.out.println(query);
//
//        // Store the item's data into a Storable using a cursor
//        Cursor cursor = db.rawQuery(query, null);
//        DefaultSchedule schedule = new DefaultSchedule();
//        if(cursor.moveToFirst()) {
//            schedule = new DefaultSchedule();
//            schedule.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
//            schedule.setProviderID(cursor.getInt(getFieldIndex(COL_PROVIDER, COLUMNS)));
//            schedule.setEffectiveDate(cursor.getString(getFieldIndex(COL_EFFECTIVEDATE, COLUMNS)));
//            schedule.setStartTimes(0, cursor.getString(getFieldIndex(COL_MONDAYSTART, COLUMNS)));
//            schedule.setEndTimes(0, cursor.getString(getFieldIndex(COL_MONDAYEND, COLUMNS)));
//            schedule.setStartTimes(1, cursor.getString(getFieldIndex(COL_TUESDAYSTART, COLUMNS)));
//            schedule.setEndTimes(1, cursor.getString(getFieldIndex(COL_TUESDAYEND, COLUMNS)));
//            schedule.setStartTimes(2, cursor.getString(getFieldIndex(COL_WEDNESDAYSTART, COLUMNS)));
//            schedule.setEndTimes(2, cursor.getString(getFieldIndex(COL_WEDNESDAYEND, COLUMNS)));
//            schedule.setStartTimes(3, cursor.getString(getFieldIndex(COL_THURSDAYSTART, COLUMNS)));
//            schedule.setEndTimes(3, cursor.getString(getFieldIndex(COL_THURSDAYEND, COLUMNS)));
//            schedule.setStartTimes(4, cursor.getString(getFieldIndex(COL_FRIDAYSTART, COLUMNS)));
//            schedule.setEndTimes(4, cursor.getString(getFieldIndex(COL_FRIDAYEND, COLUMNS)));
//            schedule.setStartTimes(5, cursor.getString(getFieldIndex(COL_SATURDAYSTART, COLUMNS)));
//            schedule.setEndTimes(5, cursor.getString(getFieldIndex(COL_SATURDAYEND, COLUMNS)));
//            schedule.setStartTimes(6, cursor.getString(getFieldIndex(COL_SUNDAYSTART, COLUMNS)));
//            schedule.setEndTimes(6, cursor.getString(getFieldIndex(COL_SUNDAYEND, COLUMNS)));
//        }
//        db.close();
//        return schedule;
//    }

    // Getters and setters
    public String getTableName() { return this.TABLE_NAME; }

    public int getProviderID() { return this.providerID; }
//    public void setProviderID(int providerID) { this.providerID = providerID; }

    public String getDate() { return this.date; }
    public void setDate(String date) { this.date = date; }

    public String getStartTime() { return this.startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return this.endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public ScheduleState getState() { return this.state; }
    public void setState(ScheduleState state) { this.state = state; }


}
