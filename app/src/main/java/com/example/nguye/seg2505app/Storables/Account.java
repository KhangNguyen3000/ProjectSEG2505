package com.example.nguye.seg2505app.Storables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguye.seg2505app.MyDBHandler;

import java.util.ArrayList;

public class Account extends Storable implements java.io.Serializable {

    // Structure of the table "Accounts"
    public static final String TABLE_NAME = "Accounts";
    public static final String COL_ID = "ID";
    public static final String COL_EMAIL = "Email";
    public static final String COL_TYPE = "Type";
    public static final String COL_FIRSTNAME = "FirstName";
    public static final String COL_LASTNAME = "LastName";
    public static final String COL_STREETNUMBER = "StreetNumber";
    public static final String COL_STREETNAME = "StreetName";
    public static final String COL_CITY = "City";
    public static final String COL_PROVINCE = "Province";
    public static final String COL_COUNTRY = "Country";
    public static final String COL_POSTALCODE = "PostalCode";
    public static final String COL_PHONENUMBER = "PhoneNumber";
    public static final String COL_PASSWORD = "Password";
    public static final String COL_COMPANY = "CompanyName";
    public static final String COL_DESCRIPTION = "Description";
    public static final String COL_LICENSED = "LICENSED";
    public static final ArrayList<String[]> COLUMNS = new ArrayList<>();
    static {
        COLUMNS.add(new String[] {COL_ID, "INTEGER PRIMARY KEY AUTOINCREMENT"});
        COLUMNS.add(new String[] {COL_EMAIL, "TEXT"});
        COLUMNS.add(new String[] {COL_TYPE, "INTEGER"});
        COLUMNS.add(new String[] {COL_FIRSTNAME, "TEXT"});
        COLUMNS.add(new String[] {COL_LASTNAME, "TEXT"});
        COLUMNS.add(new String[] {COL_STREETNUMBER, "INTEGER"});
        COLUMNS.add(new String[] {COL_STREETNAME, "TEXT"});
        COLUMNS.add(new String[] {COL_CITY, "TEXT"});
        COLUMNS.add(new String[] {COL_PROVINCE, "TEXT"});
        COLUMNS.add(new String[] {COL_COUNTRY, "TEXT"});
        COLUMNS.add(new String[] {COL_POSTALCODE, "TEXT"});
        COLUMNS.add(new String[] {COL_PHONENUMBER, "TEXT"});
        COLUMNS.add(new String[] {COL_PASSWORD, "TEXT"});
        COLUMNS.add(new String[] {COL_COMPANY, "TEXT"});
        COLUMNS.add(new String[] {COL_DESCRIPTION, "TEXT"});
        COLUMNS.add(new String[] {COL_LICENSED, "INTEGER"});

    }

    private static Account currentAccount = new Account();

    // Attributes
    private String email;
    private int type;
    private String firstName;
    private String lastName;
    private int streetNumber;
    private String streetName;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String phoneNumber;
    private String password;
    private String companyName = "NO COMPANY";
    private String description = "NO DESCRIPTION";
    private int licensed = 0;

    // Constructor
    public Account(){
        this.email = "empty"; // This is to prevent errors on a null value
    }

//    // Constructor
//    public Account(Context context) {
//        this.context = context;
//        this.email = "empty"; // This is to prevent errors on a null value
//    }




//    public boolean add() {
//        boolean result;
//        com.example.vincent.testing.MyDBHandler dbHandler = new com.example.vincent.testing.MyDBHandler(context);
//        SQLiteDatabase db = dbHandler.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COL_EMAIL, getEmail());
//        values.put(COL_TYPE, getType());
//        values.put(COL_FIRSTNAME, getFirstName());
//        values.put(COL_LASTNAME, getLastName());
//        values.put(COL_STREETNUMBER, getStreetNumber());
//        values.put(COL_STREETNAME, getStreetName());
//        values.put(COL_CITY, getCity());
//        values.put(COL_PROVINCE, getProvince());
//        values.put(COL_COUNTRY, getCountry());
//        values.put(COL_POSTALCODE, getPostalCode());
//        values.put(COL_PHONENUMBER, getPhoneNumber());
//        values.put(COL_PASSWORD, getPassword());
//
//        result = db.insert(TABLE_NAME, null, values) > 0;
//        db.close();
//        return result;
//    }
//
//    public boolean update() {
//        boolean result;
//        com.example.vincent.testing.MyDBHandler dbHandler = new com.example.vincent.testing.MyDBHandler(context);
//        SQLiteDatabase db = dbHandler.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COL_EMAIL, getEmail());
//        values.put(COL_TYPE, getType());
//        values.put(COL_FIRSTNAME, getFirstName());
//        values.put(COL_LASTNAME, getLastName());
//        values.put(COL_STREETNUMBER, getStreetNumber());
//        values.put(COL_STREETNAME, getStreetName());
//        values.put(COL_CITY, getCity());
//        values.put(COL_PROVINCE, getProvince());
//        values.put(COL_COUNTRY, getCountry());
//        values.put(COL_POSTALCODE, getPostalCode());
//        values.put(COL_PHONENUMBER, getPhoneNumber());
//        values.put(COL_PASSWORD, getPassword());
//
//        result = db.update(TABLE_NAME, values, COL_ID + " = " + getID(), null) > 0;
//        db.close();
//        return result;
//    }




//    /**
//     * Use a cursor to store a record into an Account object
//     * @param db
//     * @param cursor
//     * @return the created Account. Its attributes will be null if the record is not found.
//     */
//    public Account cursorHandler(SQLiteDatabase db, Cursor cursor) {
//        Account account = new Account();
//        if(cursor.moveToFirst()) {
//            account.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_EMAIL, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_TYPE, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_FIRSTNAME, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_LASTNAME, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_STREETNUMBER, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_STREETNAME, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_CITY, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_PROVINCE, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_COUNTRY, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_POSTALCODE, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_PHONENUMBER, COLUMNS)));
//            account.setID(cursor.getInt(getFieldIndex(COL_PASSWORD, COLUMNS)));
//        }
//        return account;
//    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public static void logout() {
        currentAccount = new Account();
    }

    /**
     * Put the Account's attributes into a ContentValues object to use with database functions
     * @return the ContentValues object
     */
    public ContentValues valuePutter() {
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, getEmail());
        values.put(COL_TYPE, getType());
        values.put(COL_FIRSTNAME, getFirstName());
        values.put(COL_LASTNAME, getLastName());
        values.put(COL_STREETNUMBER, getStreetNumber());
        values.put(COL_STREETNAME, getStreetName());
        values.put(COL_CITY, getCity());
        values.put(COL_PROVINCE, getProvince());
        values.put(COL_COUNTRY, getCountry());
        values.put(COL_POSTALCODE, getPostalCode());
        values.put(COL_PHONENUMBER, getPhoneNumber());
        values.put(COL_PASSWORD, getPassword());
        values.put(COL_COMPANY, getCompanyName());
        values.put(COL_DESCRIPTION, getDescription());
        values.put(COL_LICENSED, getLicensed());
        return values;
    }

    /**
     * Search for the record that has the specified value in the specified field in the table "Accounts".
     * @param fieldName Name of the column to look into
     * @param value Value to search for
     * @return The first record that matches.
     */
    public Account find(Context context, String fieldName, Object value, boolean quotedValue) {
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
        Account account = null;
        if(cursor.moveToFirst()) {
            account = new Account();
            account.setID(cursor.getInt(getFieldIndex(COL_ID, COLUMNS)));
            account.setEmail(cursor.getString(getFieldIndex(COL_EMAIL, COLUMNS)));
            account.setType(cursor.getInt(getFieldIndex(COL_TYPE, COLUMNS)));
            account.setFirstName(cursor.getString(getFieldIndex(COL_FIRSTNAME, COLUMNS)));
            account.setLastName(cursor.getString(getFieldIndex(COL_LASTNAME, COLUMNS)));
            account.setStreetNumber(cursor.getInt(getFieldIndex(COL_STREETNUMBER, COLUMNS)));
            account.setStreetName(cursor.getString(getFieldIndex(COL_STREETNAME, COLUMNS)));
            account.setCity(cursor.getString(getFieldIndex(COL_CITY, COLUMNS)));
            account.setProvince(cursor.getString(getFieldIndex(COL_PROVINCE, COLUMNS)));
            account.setCountry(cursor.getString(getFieldIndex(COL_COUNTRY, COLUMNS)));
            account.setPostalCode(cursor.getString(getFieldIndex(COL_POSTALCODE, COLUMNS)));
            account.setPhoneNumber(cursor.getString(getFieldIndex(COL_PHONENUMBER, COLUMNS)));
            account.setPassword(cursor.getString(getFieldIndex(COL_PASSWORD, COLUMNS)));
            account.setCompanyName(cursor.getString(getFieldIndex(COL_COMPANY, COLUMNS)));
            account.setDescription(cursor.getString(getFieldIndex(COL_DESCRIPTION, COLUMNS)));
            account.setLicensed(cursor.getInt(getFieldIndex(COL_LICENSED, COLUMNS)));
        }
        db.close();
        return account;
    }



    // Setters and getters
    public String getTableName() { return TABLE_NAME; }

    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email = email;}

    public int getType(){return this.type;}
    public void setType(int type){this.type = type;}

    public String getFirstName(){return this.firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName(){return this.lastName;}
    public void setLastName(String lastName){this.lastName = lastName;}

    public int getStreetNumber(){return this.streetNumber;}
    public void setStreetNumber(int streetNumber){this.streetNumber = streetNumber;}

    public String getStreetName(){return this.streetName;}
    public void setStreetName(String streetName){this.streetName = streetName;}

    public String getCity(){return this.city;}
    public void setCity(String city){this.city = city;}

    public String getProvince(){return this.province;}
    public void setProvince(String province){this.province = province;}

    public String getCountry(){return this.country;}
    public void setCountry(String country){this.country = country;}

    public String getPostalCode(){return this.postalCode;}
    public void setPostalCode(String postalCode){this.postalCode = postalCode;}

    public String getPhoneNumber(){return this.phoneNumber;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password = password;}

    public String getCompanyName() { return this.companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public int getLicensed() { return this.licensed; }
    public void setLicensed(int licensed) { this.licensed = licensed; }

}
