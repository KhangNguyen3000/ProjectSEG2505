package com.example.nguye.seg2505app.Storables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import com.example.nguye.seg2505app.MyDBHandler;

import java.util.ArrayList;
import java.util.List;


public abstract class Storable implements java.io.Serializable {

    // Every table will have a column called "ID"
    public static final String COL_ID = "ID";

    // Attributes
    // protected Context context;
    protected int ID;

    // Methods to be implemented by subclasses
    abstract String getTableName();
    abstract ContentValues valuePutter();


    /**
     * Every subclass of Storable has its columns stored in an ArrayList. This function allows to
     *  retrieve the index of the specified column name.
     * @param columnName The column name that we want to get the index of
     * @param fields The ArrayList containing the pairs ColumnName-ColumnType
     * @return The index of the column with the specified name
     */
    public static int getFieldIndex(String columnName, ArrayList<String[]> fields) {
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i)[0].equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Add a record containing the item's information to its corresponding table
     * @param context
     * @return true if the item was successfully added, false otherwise
     */
    public boolean add(Context context) {
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = this.valuePutter();

        boolean result;
        if (db.insert(this.getTableName(), null, values) > 0) {
            System.out.println("The record has been added.");
            result = true;
        } else {
            System.out.println("The record could not be found.");
            result = false;
        }
        db.close();
        return result;
    }

    /**
     * Update the record with the item's information using its ID as primary key
     * @param context
     * @return true if the item was successfully updated, false otherwise
     */
    public boolean update(Context context) {
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        ContentValues values = this.valuePutter();
        boolean result;
        if (db.update(this.getTableName(), values, COL_ID + " = " + this.getID(), null) > 0) {
            System.out.println("The record has been updated.");
            result = true;
        } else {
            System.out.println("The record could not be found.");
            result = false;
        }
        db.close();
        return result;
    }

    /**
     * Delete the record with the corresponding ID from the item's respective table
     * @param context
     * @return true if the item was successfully deleted, false otherwise
     */
    public boolean delete(Context context) {
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        boolean result;
        if (db.delete(this.getTableName(), COL_ID + " = " + this.getID(), null) > 0) {
            System.out.println("The record has been deleted.");
            result = true;
        } else {
            System.out.println("The record could not be found.");
            result = false;
        }
        db.close();
        return result;
    }

    public static String search(Context context, String tableName, String fieldName, Object key, Object value, boolean quotedValue) {
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String quotes = "";
        if (quotedValue) {
            quotes = "\"";
        }
        String query = "SELECT " + fieldName + " FROM " + tableName
                + " WHERE " + key + " = " + quotes + value + quotes;
        System.out.println(query);

        // Store the item's data into a Storable using a cursor
        Cursor cursor = db.rawQuery(query, null);
        String foundValue = "";
        if(cursor.moveToFirst()) {
            foundValue = cursor.getString(0);
        }
        db.close();
        return foundValue;
    }



    /**
     * Get a list of all the values from the specified field in the specified table where
     *  the specified condition is met.
     * @param context
     * @param fieldName Name of the field from which you want to pull the data
     * @param tableName
     * @param where String representing a SQL WHERE condition
     * @return
     */
    public static ArrayList<String> selectAllInColumn(Context context, String fieldName, String tableName, String where) {
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "SELECT " + fieldName + " FROM " + tableName
                + " WHERE " + where;
        System.out.println(query);

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<String> array = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                array.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        db.close();
        return array;
    }

    /**
     * Put all the values from a record from the specified table into an ArrayList
     * @param context
     * @param numberOfFields Number of columns in the row
     * @param tableName
     * @param where String representing a SQL WHERE condition
     * @return
     */
    public static ArrayList<String> selectAllInRow(Context context, int numberOfFields, String tableName, String where) {
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM " + tableName
                + " WHERE " + where;
        System.out.println(query);

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<String> array = new ArrayList<>();
        if (cursor.moveToFirst()) {
            for (int i = 0; i < numberOfFields; i++) {
                array.add(cursor.getString(i));
            }
        }
        cursor.close();
        db.close();
        return array;
    }

    /**
     * Get a list of all the values from the specified field in the specified table where
     *  the specified condition is met.
     * @param context
     * @param fieldName
     * @param tableName
     * @param where String representing a SQL WHERE condition
     * @return
     */
    public static String selectFirst(Context context, String fieldName, String tableName, String where) {
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "SELECT " + fieldName + " FROM " + tableName
                + " WHERE " + where;
        System.out.println(query);

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            db.close();
            return cursor.getString(0);
        } else {
            db.close();
            return "VALUE_NOT_FOUND";
        }
    }


//    /**
//     * Return an ArrayList that contains arrays of String (String[]) where each String[] represents
//     *  a record where every value is returned as a String. Those values can be parsed after if
//     *  necessary. This allows to get only specific information rather than whole objects.
//     * @param context
//     * @param query The fully defined SQL query
//     * @param numOfFields The number of fields that will be retrieved according to the query. This
//     *                    is to set the size of the String[]
//     * @return
//     */
//    public static ArrayList<String[]> select(Context context, String query, int numOfFields) {
//        // Connect to the database
//        MyDBHandler dbHandler = new MyDBHandler(context);
//        SQLiteDatabase db = dbHandler.getReadableDatabase();
//        ArrayList<String[]> records = new ArrayList<>();
//
//        Cursor cursor = db.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            while (!cursor.isAfterLast()) {
//                String[] fieldValues = new String[numOfFields];
//                for (int i = 0; i < numOfFields; i++) {
//                    fieldValues[i] = cursor.getString(i);
//                }
//                records.add(fieldValues);
//                cursor.moveToNext();
//            }
//        }
//        db.close();
//        return records;
//    }

    /**
     * Return an ArrayList that contains arrays of String (String[]) where each String[] represents
     *  a record where every value is returned as a String. Those values can be parsed after if
     *  necessary. This allows to get only specific information rather than whole objects.
     * @param context
     * @param query The fully defined SQL query
     * @param numOfFields The number of fields that will be retrieved according to the query. This
     *                    is to set the size of the String[]
     * @return
     */
    public static ArrayList<String[]> select(Context context, String query, int numOfFields) {
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        ArrayList<String[]> records = new ArrayList<>();

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String[] fieldValues = new String[numOfFields];
            for (int i = 0; i < numOfFields; i++) {
                fieldValues[i] = cursor.getString(i);
            }
            records.add(fieldValues);
        }
        db.close();
        return records;
    }



    /**
     * Simply return the value (as a String) of the specified field in the specified table in the
     *  record that corresponds to the specified ID. Use this for quick lookup.
     * @param context
     * @param tableName Name of the table that contains the specified field
     * @param fieldName Name of the field that you want to get the value from
     * @param ID ID of the record
     * @return
     */
    public static String findFieldByID(Context context, String tableName, String fieldName, int ID) {
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String query = "SELECT " + fieldName + " FROM " + tableName
                + " WHERE " + COL_ID + " = " + ID;
        System.out.println(query);

        String foundValue = "NO_RECORD_FOUND";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            foundValue = cursor.getString(0);
        }
        return foundValue;
    }

    /**
     * Checks if there is a record in the specified table that matches the condition
     * @param context
     * @param tableName
     * @param where The condition
     * @return
     */
    public static boolean exists(Context context, String tableName, String where) {
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        String query = "SELECT " + COL_ID + " FROM " + tableName
                + " WHERE " + where;
        System.out.println(query);
        Cursor cursor = db.rawQuery(query, null);

        boolean result;
        if (cursor.moveToFirst()) {
            result = true;
        } else {
            result = false;
        }
        db.close();
        return result;
    }

    /**
     * Count the number of records that correspond to the condition specified in the query
     * @param context
     * @param query
     * @return
     */
    public static int count(Context context, String query) {
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);

        cursor.close();
        db.close();
        return count;
    }

    public int getID() { return this.ID; }
    public void setID(int ID) { this.ID = ID; }
}
