package com.example.nguye.seg2505app.Storables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import java.util.ArrayList;
import java.util.Calendar;

public class DefaultSchedule extends Storable {

    // Structure of the table "DefaultSchedules"
    public static final String TABLE_NAME = "DefaultSchedules";
    public static final String COL_PROVIDER = "ProviderID";
    public static final String COL_EFFECTIVEDATE = "EffectiveDate";
    public static final String COL_SUNDAYSTART = "SundayStart";
    public static final String COL_SUNDAYEND = "SundayEnd";
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

    public static final ArrayList<String[]> COLUMNS = new ArrayList<>();
    static {
        COLUMNS.add(new String[] {COL_ID, "INTEGER PRIMARY KEY AUTOINCREMENT"});
        COLUMNS.add(new String[] {COL_PROVIDER, "INTEGER"});
        COLUMNS.add(new String[] {COL_EFFECTIVEDATE, "TEXT"});
        COLUMNS.add(new String[] {COL_SUNDAYSTART, "INTEGER"});
        COLUMNS.add(new String[] {COL_SUNDAYEND, "INTEGER"});
        COLUMNS.add(new String[] {COL_MONDAYSTART, "INTEGER"});
        COLUMNS.add(new String[] {COL_MONDAYEND, "INTEGER"});
        COLUMNS.add(new String[] {COL_TUESDAYSTART, "INTEGER"});
        COLUMNS.add(new String[] {COL_TUESDAYEND, "INTEGER"});
        COLUMNS.add(new String[] {COL_WEDNESDAYSTART, "INTEGER"});
        COLUMNS.add(new String[] {COL_WEDNESDAYEND, "INTEGER"});
        COLUMNS.add(new String[] {COL_THURSDAYSTART, "INTEGER"});
        COLUMNS.add(new String[] {COL_THURSDAYEND, "INTEGER"});
        COLUMNS.add(new String[] {COL_FRIDAYSTART, "INTEGER"});
        COLUMNS.add(new String[] {COL_FRIDAYEND, "INTEGER"});
        COLUMNS.add(new String[] {COL_SATURDAYSTART, "INTEGER"});
        COLUMNS.add(new String[] {COL_SATURDAYEND, "INTEGER"});
    }

    // Attributes
    private int providerID;
    private String effectiveDate;
    private int[] startTimes;
    private int[] endTimes;
    
    // Constructor (empty)
    public DefaultSchedule() {
        this.effectiveDate = "1970-01-01";
        this.startTimes = new int[] {0, 0, 0, 0, 0, 0, 0};
        this.endTimes = new int[] {0, 0, 0, 0, 0, 0, 0};
    }

    // Constructor (full)
    public DefaultSchedule(int providerID, String effectiveDate, int mondayStart, int mondayEnd,
                           int tuesdayStart, int tuesdayEnd, int wednesdayStart, int wednesdayEnd,
                           int thursdayStart, int thursdayEnd, int fridayStart, int fridayEnd,
                           int saturdayStart, int saturdayEnd, int sundayStart, int sundayEnd) {
        this.providerID = providerID;
        this.effectiveDate = effectiveDate;
        this.startTimes = new int[] {mondayStart, tuesdayStart, wednesdayStart,
                thursdayStart, fridayStart, saturdayStart, sundayStart};
        this.endTimes = new int[] {mondayEnd, tuesdayEnd, wednesdayEnd,
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
        DefaultSchedule schedule = new DefaultSchedule();
        if(cursor.moveToFirst()) {
            schedule = new DefaultSchedule();
            schedule.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
            schedule.setProviderID(cursor.getInt(getFieldIndex(COL_PROVIDER, COLUMNS)));
            schedule.setEffectiveDate(cursor.getString(getFieldIndex(COL_EFFECTIVEDATE, COLUMNS)));
            schedule.setStartTimes(0, cursor.getInt(getFieldIndex(COL_MONDAYSTART, COLUMNS)));
            schedule.setEndTimes(0, cursor.getInt(getFieldIndex(COL_MONDAYEND, COLUMNS)));
            schedule.setStartTimes(1, cursor.getInt(getFieldIndex(COL_TUESDAYSTART, COLUMNS)));
            schedule.setEndTimes(1, cursor.getInt(getFieldIndex(COL_TUESDAYEND, COLUMNS)));
            schedule.setStartTimes(2, cursor.getInt(getFieldIndex(COL_WEDNESDAYSTART, COLUMNS)));
            schedule.setEndTimes(2, cursor.getInt(getFieldIndex(COL_WEDNESDAYEND, COLUMNS)));
            schedule.setStartTimes(3, cursor.getInt(getFieldIndex(COL_THURSDAYSTART, COLUMNS)));
            schedule.setEndTimes(3, cursor.getInt(getFieldIndex(COL_THURSDAYEND, COLUMNS)));
            schedule.setStartTimes(4, cursor.getInt(getFieldIndex(COL_FRIDAYSTART, COLUMNS)));
            schedule.setEndTimes(4, cursor.getInt(getFieldIndex(COL_FRIDAYEND, COLUMNS)));
            schedule.setStartTimes(5, cursor.getInt(getFieldIndex(COL_SATURDAYSTART, COLUMNS)));
            schedule.setEndTimes(5, cursor.getInt(getFieldIndex(COL_SATURDAYEND, COLUMNS)));
            schedule.setStartTimes(6, cursor.getInt(getFieldIndex(COL_SUNDAYSTART, COLUMNS)));
            schedule.setEndTimes(6, cursor.getInt(getFieldIndex(COL_SUNDAYEND, COLUMNS)));
        }
        db.close();
        return schedule;
    }

    /**
     * Create a list of DefaultSchedules built with the data stored in the database. This function
     *  gives more freedom and allows for more precise search by using a fully defined SQL query.
     * @param context
     * @param query
     * @return
     */
    public static ArrayList<DefaultSchedule> findByQuery(Context context, String query) {
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        // Store the item's data into a Storable using a cursor
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<DefaultSchedule> DefSchedules = new ArrayList<>();
        DefaultSchedule schedule;
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                schedule = new DefaultSchedule();
                schedule.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
                schedule.setProviderID(cursor.getInt(getFieldIndex(COL_PROVIDER, COLUMNS)));
                schedule.setEffectiveDate(cursor.getString(getFieldIndex(COL_EFFECTIVEDATE, COLUMNS)));
                schedule.setStartTimes(0, cursor.getInt(getFieldIndex(COL_MONDAYSTART, COLUMNS)));
                schedule.setEndTimes(0, cursor.getInt(getFieldIndex(COL_MONDAYEND, COLUMNS)));
                schedule.setStartTimes(1, cursor.getInt(getFieldIndex(COL_TUESDAYSTART, COLUMNS)));
                schedule.setEndTimes(1, cursor.getInt(getFieldIndex(COL_TUESDAYEND, COLUMNS)));
                schedule.setStartTimes(2, cursor.getInt(getFieldIndex(COL_WEDNESDAYSTART, COLUMNS)));
                schedule.setEndTimes(2, cursor.getInt(getFieldIndex(COL_WEDNESDAYEND, COLUMNS)));
                schedule.setStartTimes(3, cursor.getInt(getFieldIndex(COL_THURSDAYSTART, COLUMNS)));
                schedule.setEndTimes(3, cursor.getInt(getFieldIndex(COL_THURSDAYEND, COLUMNS)));
                schedule.setStartTimes(4, cursor.getInt(getFieldIndex(COL_FRIDAYSTART, COLUMNS)));
                schedule.setEndTimes(4, cursor.getInt(getFieldIndex(COL_FRIDAYEND, COLUMNS)));
                schedule.setStartTimes(5, cursor.getInt(getFieldIndex(COL_SATURDAYSTART, COLUMNS)));
                schedule.setEndTimes(5, cursor.getInt(getFieldIndex(COL_SATURDAYEND, COLUMNS)));
                schedule.setStartTimes(6, cursor.getInt(getFieldIndex(COL_SUNDAYSTART, COLUMNS)));
                schedule.setEndTimes(6, cursor.getInt(getFieldIndex(COL_SUNDAYEND, COLUMNS)));
                DefSchedules.add(schedule);
                cursor.moveToNext();
            }
        }
        db.close();
        return DefSchedules;
    }

    public static String[] getWeekdayField(String dateString) {
        String[] fields = new String[2];

        long timeInMillis = FormatValue.dateToLong(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);

        String startTimeField = "";
        String endTimeField = "";
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case (Calendar.SUNDAY):
                startTimeField = DefaultSchedule.COL_SUNDAYSTART;
                endTimeField = DefaultSchedule.COL_SUNDAYEND;
                break;
            case (Calendar.MONDAY):
                startTimeField = DefaultSchedule.COL_MONDAYSTART;
                endTimeField = DefaultSchedule.COL_MONDAYEND;
                break;
            case (Calendar.TUESDAY):
                startTimeField = DefaultSchedule.COL_TUESDAYSTART;
                endTimeField = DefaultSchedule.COL_TUESDAYEND;
                break;
            case (Calendar.WEDNESDAY):
                startTimeField = DefaultSchedule.COL_WEDNESDAYSTART;
                endTimeField = DefaultSchedule.COL_WEDNESDAYEND;
                break;
            case (Calendar.THURSDAY):
                startTimeField = DefaultSchedule.COL_THURSDAYSTART;
                endTimeField = DefaultSchedule.COL_THURSDAYEND;
                break;
            case (Calendar.FRIDAY):
                startTimeField = DefaultSchedule.COL_FRIDAYSTART;
                endTimeField = DefaultSchedule.COL_FRIDAYEND;
                break;
            case (Calendar.SATURDAY):
                startTimeField = DefaultSchedule.COL_SATURDAYSTART;
                endTimeField = DefaultSchedule.COL_SATURDAYEND;
                break;
            default:
                break;
        }
        fields[0] = startTimeField;
        fields[1] = endTimeField;
        return fields;
    }

    // Getters and setters
    public String getTableName() { return this.TABLE_NAME; }

    public int getProviderID() { return this.providerID; }
    public void setProviderID(int providerID) { this.providerID = providerID; }

    public String getEffectiveDate() { return this.effectiveDate; }
    public void setEffectiveDate(String effectiveDate) { this.effectiveDate = effectiveDate; }

    public int[] getStartTimes() { return this.startTimes; }
    public int getStartTimes(int dayIndex) { return this.startTimes[dayIndex]; }
    public void setStartTimes(int dayIndex, int startTime) { this.startTimes[dayIndex] = startTime; }

    public int[] getEndTimes() { return this.endTimes; }
    public int getEndTimes(int dayIndex) { return this.endTimes[dayIndex]; }
    public void setEndTimes(int dayIndex, int endTime) { this.endTimes[dayIndex] = endTime; }

    public String toString() {
        String string = "DEFAULT SCHEDULE\n";
        string += "Provider: " + getProviderID() + "\n";
        string += "Effective date: " + getEffectiveDate() + "\n";
        string += "SunStart: " + getStartTimes(0) + "\n";
        string += "SunEnd: " + getEndTimes(0) + "\n";
        string += "MonStart: " + getStartTimes(1) + "\n";
        string += "MonEnd: " + getEndTimes(1) + "\n";
        string += "TueStart: " + getStartTimes(2) + "\n";
        string += "TueEnd: " + getEndTimes(2) + "\n";
        string += "WedStart: " + getStartTimes(3) + "\n";
        string += "WedEnd: " + getEndTimes(3) + "\n";
        string += "ThuStart: " + getStartTimes(4) + "\n";
        string += "ThuEnd: " + getEndTimes(4) + "\n";
        string += "FriStart: " + getStartTimes(5) + "\n";
        string += "FriEnd: " + getEndTimes(5) + "\n";
        string += "SatStart: " + getStartTimes(6) + "\n";
        string += "SatEnd: " + getEndTimes(6) + "\n";

        return string;
    }
}
