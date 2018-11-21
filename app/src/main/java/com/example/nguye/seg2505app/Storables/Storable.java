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
//    protected Context context;
    protected int ID;

    // Methods to be implemented by subclasses
    abstract String getTableName();
//    abstract Storable cursorHandler(SQLiteDatabase db, Cursor cursor);
    //<T extends Storable> T
    abstract ContentValues valuePutter();
    abstract Storable find(Context context, String fieldName, Object value, boolean quotedValue);

//    // Constructor
//    public Storable() {
//    }
//
//    // Constructor
//    public Storable(Context context) {
//        this.context = context;
//    }

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

    //    public <T extends Storable> Storable find(Context context, Class<T> cls, int ID) {
//    /**
//     * Search for the item's record in its respective table
//     * @param ID
//     * @return
//     */
//    public Storable findByID(Context context, int ID) {
//        try {
//            // Instantiate the calling class
//            Storable storable = this.getClass().newInstance();
//
//            // Connect to the database
//            MyDBHandler dbHandler = new MyDBHandler(context);
//            SQLiteDatabase db = dbHandler.getReadableDatabase();
//
//            // Store the item's data into a Storable using a cursor
//            String query = "SELECT * FROM " + storable.getTableName()
//                    + " WHERE " + "ID" + " = " + ID;
//            System.out.println(query);
//            Cursor cursor = db.rawQuery(query, null);
//            storable = storable.cursorHandler(db, cursor);
//            db.close();
//
//            return storable;
////            return this.getClass().cast(storable);
//        } catch (IllegalAccessException e) {
//            System.out.println("Illegal access exception.");
//            return null;
//        } catch (InstantiationException e) {
//            System.out.println("Instantiation exception.");
//            return null;
//        }
//    }

//    /**
//     * Search for the record that has the specified value in the specified field in the calling class' respective table.
//     * @param fieldName Name of the column to look into
//     * @param value Value to search for
//     * @return The first record that matches.
//     */
//    public Storable find(Context context, String fieldName, Object value) {
//        try {
//            // Instantiate the calling class
//            Storable storable = this.getClass().newInstance();
//
//            // Connect to the database
//            MyDBHandler dbHandler = new MyDBHandler(context);
//            SQLiteDatabase db = dbHandler.getReadableDatabase();
//
//            // Store the item's data into a Storable using a cursor
//            String query = "SELECT * FROM " + storable.getTableName()
//                    + " WHERE " + fieldName + " = " + value;
//            System.out.println(query);
//            Cursor cursor = db.rawQuery(query, null);
//            storable = storable.cursorHandler(db, cursor);
//            db.close();
//            Class currentClass = this.getClass();
//            System.out.println(currentClass);
//            return storable;
////            return this.getClass().cast(storable);
//        } catch (IllegalAccessException e) {
//            System.out.println("Illegal access exception.");
//            return null;
//        } catch (InstantiationException e) {
//            System.out.println("Instantiation exception.");
//            return null;
//        }
//    }

    // Getters and setters
//    public Context getContext() { return this.context; }
//    public void setContext(Context context) { this.context = context; }


    public int getID() { return this.ID; }
    public void setID(int ID) { this.ID = ID; }
}
