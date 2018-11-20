package com.example.nguye.seg2505app.Storables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguye.seg2505app.MyDBHandler;

import java.util.ArrayList;

public class DefaultSchedule extends Storable {

    // Structure of the table "DefaultSchedules"
    public static final String TABLE_NAME = "DefaultSchedules";
    public static final String COL_PROVIDER = "ProviderID";
    public static final String COL_EFFECTIVEDATE = "EffectiveDate";
    public static final String COL_MONDAYSTART = "MondayStart";
    public static final String COL_MONDAYEND = "MondayEnd";
    public static final String COL_TUESDAYSTART = "TuesdayStart";
    public static final String COL_TUESDAYEND = "TuesdayEnd";
    public static final String COL_WEDNESDAYSTART = "WednesdayStart";
    public static final String COL_WEDNESDAYEND = "WednesdayEnd";
    public static final String COL_THURSDAYSTART = "ThursdayStart";
    public static final String COL_THURSDAYEND = "ThursdayEnd";
    public static final String COL_FRIDAYSTART = "FridayStart";
    public static final String COL_FRIDAYEND = "FridayEnd";
    public static final String COL_SATURDAYSTART = "SaturdayStart";
    public static final String COL_SATURDAYEND = "SaturdayEnd";
    public static final String COL_SUNDAYSTART = "SundayStart";
    public static final String COL_SUNDAYEND = "SundayEnd";
    public static final ArrayList<String[]> COLUMNS = new ArrayList<>();
    static {
        COLUMNS.add(new String[] {COL_ID, "INTEGER PRIMARY KEY AUTOINCREMENT"});
        COLUMNS.add(new String[] {COL_PROVIDER, "INTEGER"});
        COLUMNS.add(new String[] {COL_EFFECTIVEDATE, "TEXT"});
        COLUMNS.add(new String[] {COL_MONDAYSTART, "TEXT"});
        COLUMNS.add(new String[] {COL_MONDAYEND, "TEXT"});
        COLUMNS.add(new String[] {COL_TUESDAYSTART, "TEXT"});
        COLUMNS.add(new String[] {COL_TUESDAYEND, "TEXT"});
        COLUMNS.add(new String[] {COL_WEDNESDAYSTART, "TEXT"});
        COLUMNS.add(new String[] {COL_WEDNESDAYEND, "TEXT"});
        COLUMNS.add(new String[] {COL_THURSDAYSTART, "TEXT"});
        COLUMNS.add(new String[] {COL_THURSDAYEND, "TEXT"});
        COLUMNS.add(new String[] {COL_FRIDAYSTART, "TEXT"});
        COLUMNS.add(new String[] {COL_FRIDAYEND, "TEXT"});
        COLUMNS.add(new String[] {COL_SATURDAYSTART, "TEXT"});
        COLUMNS.add(new String[] {COL_SATURDAYEND, "TEXT"});
        COLUMNS.add(new String[] {COL_SUNDAYSTART, "TEXT"});
        COLUMNS.add(new String[] {COL_SUNDAYEND, "TEXT"});
    }

    // Attributes
    private int providerID;
    private String effectiveDate;
    private String[] startTimes;
    private String[] endTimes;

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

    // Constructor (empty)
    public DefaultSchedule() {
    }

    // Constructor (full)
    public DefaultSchedule(int providerID, String effectiveDate, String mondayStart, String mondayEnd,
                           String tuesdayStart, String tuesdayEnd, String wednesdayStart, String wednesdayEnd,
                           String thursdayStart, String thursdayEnd, String fridayStart, String fridayEnd,
                           String saturdayStart, String saturdayEnd, String sundayStart, String sundayEnd) {
        this.providerID = providerID;
        this.effectiveDate = effectiveDate;
        this.startTimes = new String[] {mondayStart, tuesdayStart, wednesdayStart,
                thursdayStart, fridayStart, saturdayStart, sundayStart};
        this.endTimes = new String[] {mondayEnd, tuesdayEnd, wednesdayEnd,
                thursdayEnd, fridayEnd, saturdayEnd, sundayEnd};
    }

    /**
     * Put the com.example.nguye.seg2505app.Storables.DefaultSchedule's attributes into a ContentValues object to use with database functions
     * @return the ContentValues object
     */
    public ContentValues valuePutter() {
        ContentValues values = new ContentValues();
        values.put(COL_PROVIDER, getProviderID());
        values.put(COL_EFFECTIVEDATE, getEffectiveDate());
        values.put(COL_MONDAYSTART, getStartTimes(0));
        values.put(COL_MONDAYEND, getEndTimes(0));
        values.put(COL_TUESDAYSTART, getStartTimes(1));
        values.put(COL_TUESDAYEND, getEndTimes(1));
        values.put(COL_WEDNESDAYSTART, getStartTimes(2));
        values.put(COL_WEDNESDAYEND, getEndTimes(2));
        values.put(COL_THURSDAYSTART, getStartTimes(3));
        values.put(COL_THURSDAYEND, getEndTimes(3));
        values.put(COL_FRIDAYSTART, getStartTimes(4));
        values.put(COL_FRIDAYEND, getEndTimes(4));
        values.put(COL_SATURDAYSTART, getStartTimes(5));
        values.put(COL_SATURDAYEND, getEndTimes(5));
        values.put(COL_SUNDAYSTART, getStartTimes(6));
        values.put(COL_SUNDAYEND, getEndTimes(6));
        return values;
    }

    public DefaultSchedule find(Context context, String fieldName, Object value, boolean quotedValue) {
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
        DefaultSchedule schedule = null;
        if(cursor.moveToFirst()) {
            schedule = new DefaultSchedule();
            schedule.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
            schedule.setProviderID(cursor.getInt(getFieldIndex(COL_PROVIDER, COLUMNS)));
            schedule.setEffectiveDate(cursor.getString(getFieldIndex(COL_EFFECTIVEDATE, COLUMNS)));
            schedule.setStartTimes(0, cursor.getString(getFieldIndex(COL_MONDAYSTART, COLUMNS)));
            schedule.setEndTimes(0, cursor.getString(getFieldIndex(COL_MONDAYEND, COLUMNS)));
            schedule.setStartTimes(1, cursor.getString(getFieldIndex(COL_TUESDAYSTART, COLUMNS)));
            schedule.setEndTimes(1, cursor.getString(getFieldIndex(COL_TUESDAYEND, COLUMNS)));
            schedule.setStartTimes(2, cursor.getString(getFieldIndex(COL_WEDNESDAYSTART, COLUMNS)));
            schedule.setEndTimes(2, cursor.getString(getFieldIndex(COL_WEDNESDAYEND, COLUMNS)));
            schedule.setStartTimes(3, cursor.getString(getFieldIndex(COL_THURSDAYSTART, COLUMNS)));
            schedule.setEndTimes(3, cursor.getString(getFieldIndex(COL_THURSDAYEND, COLUMNS)));
            schedule.setStartTimes(4, cursor.getString(getFieldIndex(COL_FRIDAYSTART, COLUMNS)));
            schedule.setEndTimes(4, cursor.getString(getFieldIndex(COL_FRIDAYEND, COLUMNS)));
            schedule.setStartTimes(5, cursor.getString(getFieldIndex(COL_SATURDAYSTART, COLUMNS)));
            schedule.setEndTimes(5, cursor.getString(getFieldIndex(COL_SATURDAYEND, COLUMNS)));
            schedule.setStartTimes(6, cursor.getString(getFieldIndex(COL_SUNDAYSTART, COLUMNS)));
            schedule.setEndTimes(6, cursor.getString(getFieldIndex(COL_SUNDAYEND, COLUMNS)));
        }
        db.close();
        return schedule;
    }

    // Getters and setters
    public String getTableName() { return this.TABLE_NAME; }

    public int getProviderID() { return this.providerID; }
    public void setProviderID(int providerID) { this.providerID = providerID; }

    public String getEffectiveDate() { return this.effectiveDate; }
    public void setEffectiveDate(String effectiveDate) { this.effectiveDate = effectiveDate; }

    public String[] getStartTimes() { return this.startTimes; }
    public String getStartTimes(int dayIndex) { return this.startTimes[dayIndex]; }
    public void setStartTimes(int dayIndex, String startTime) { this.startTimes[dayIndex] = startTime; }

    public String[] getEndTimes() { return this.endTimes; }
    public String getEndTimes(int dayIndex) { return this.endTimes[dayIndex]; }
    public void setEndTimes(int dayIndex, String endTime) { this.endTimes[dayIndex] = endTime; }
}
