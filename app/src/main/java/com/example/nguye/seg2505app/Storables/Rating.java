package com.example.nguye.seg2505app.Storables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguye.seg2505app.MyDBHandler;

import java.util.ArrayList;

public class Rating extends Storable {

    // Structure of the table "Ratings"
    public static final String TABLE_NAME = "Ratings";
    public static final String COL_ID = "ID";
    public static final String COL_PROVIDER_ID = "ProviderID";
    public static final String COL_CUSTOMER_ID = "ClientID";
    public static final String COL_COMMENT = "Comment";
    public static final String COL_RATING = "Rating";
    public static final ArrayList<String[]> COLUMNS = new ArrayList<>();
    static {
        COLUMNS.add(new String[] {COL_ID, "INTEGER PRIMARY KEY AUTOINCREMENT"});
        COLUMNS.add(new String[] {COL_PROVIDER_ID, "INTEGER"});
        COLUMNS.add(new String[] {COL_CUSTOMER_ID, "INTEGER"});
        COLUMNS.add(new String[] {COL_COMMENT, "TEXT"});
        COLUMNS.add(new String[] {COL_RATING, "DOUBLE"}); // TODO replace by INTEGER
    }

    // Attributes
    private int p_ID;
    private int c_ID;
    private String description;
    private double rating; // TODO replace by int

    // Constructor
    public Rating() {
    }

    // Constructor
    public Rating(int pID, int cID, String description, double rating){
        this.p_ID = pID;
        this.c_ID = cID;
        this.description = description;
        this.rating = rating;
    }

    // Constructor
    public Rating(int ID, int pID, int cID, String description, double rating){
        this.ID = ID;
        this.p_ID = pID;
        this.c_ID = cID;
        this.description = description;
        this.rating = rating;
    }

    /**
     * Put the Rating's attributes into a ContentValues object to use with database functions
     * @return the ContentValues object
     */
    public ContentValues valuePutter() {
        ContentValues values = new ContentValues();
        values.put(COL_CUSTOMER_ID, getCID());
        values.put(COL_PROVIDER_ID, getPID());
        values.put(COL_COMMENT, getComment());
        values.put(COL_RATING, getRating());
        return values;
    }

    /**
     * Search for the record that has the specified value in the specified field in the table "Ratings".
     * @param fieldName Name of the column to look into
     * @param value Value to search for
     * @return The first record that matches.
     */
    public Rating find(Context context, String fieldName, Object value, boolean quotedValue) {
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
        Rating rating = null;
        if(cursor.moveToFirst()) {
            rating = new Rating();
            rating.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
            rating.setPID(cursor.getInt(getFieldIndex(COL_PROVIDER_ID, COLUMNS)));
            rating.setCID(cursor.getInt(getFieldIndex(COL_CUSTOMER_ID, COLUMNS)));
            rating.setComment(cursor.getString(getFieldIndex(COL_COMMENT, COLUMNS)));
            rating.setRating(cursor.getDouble(getFieldIndex(COL_RATING, COLUMNS)));
        }
        db.close();
        return rating;
    }


    // Getters and setters
    public String getTableName() { return TABLE_NAME; }

    public int getPID(){ return this.p_ID; }
    public void setPID(int pID) { this.p_ID= pID; }

    public int getCID(){ return this.c_ID; }
    public void setCID(int cID) { this.c_ID= cID; }

    public double getRating(){ return this.rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getComment(){return this.description;}
    public void setComment(String comment){this.description = comment;}

    public  static ArrayList<Rating> findAll(Context context, String fieldName, Object value, boolean quotedValue){
        // Connect to the database
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        String quotes = "";
        if (quotedValue) {
            quotes = "\"";
        }
        String query = "SELECT * FROM " + Rating.TABLE_NAME
                + " WHERE " + fieldName + " = " + quotes + value + quotes;
        System.out.println(query);

        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Rating> ratings = new ArrayList<>();
        Rating rating = null;
        while(cursor.moveToNext()){
            rating = new Rating();
            rating.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
            rating.setPID(cursor.getInt(getFieldIndex(COL_PROVIDER_ID, COLUMNS)));
            rating.setCID(cursor.getInt(getFieldIndex(COL_CUSTOMER_ID, COLUMNS)));
            rating.setRating(cursor.getDouble(getFieldIndex(COL_RATING, COLUMNS)));
            rating.setComment(cursor.getString((getFieldIndex(COL_COMMENT, COLUMNS))));
            ratings.add(rating);
        }
        db.close();
        return ratings;
    }
}
