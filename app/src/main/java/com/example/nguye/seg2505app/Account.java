package com.example.nguye.seg2505app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Account extends Storable {

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
    }

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

    // Constructor
    public Account(){
        this.email = "empty"; // This is to prevent errors on a null value
    }

    public Account(Context context) {
        this.context = context;
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


    public boolean add() {
        boolean result;
        com.example.vincent.testing.MyDBHandler dbHandler = new com.example.vincent.testing.MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getWritableDatabase();

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

        result = db.insert(TABLE_NAME, null, values) > 0;
        db.close();
        return result;
    }

    public boolean update() {
        boolean result;
        com.example.vincent.testing.MyDBHandler dbHandler = new com.example.vincent.testing.MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getWritableDatabase();

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

        result = db.update(TABLE_NAME, values, COL_ID + " = " + getID(), null) > 0;
        db.close();
        return result;
    }

    public boolean delete() {
        return true;
    }

    public Account cursorHandler(SQLiteDatabase db, Cursor cursor) {
        Account account = new Account(context);
        if(cursor.moveToFirst()) {

        } else {
            account = null;
        }
        return account;
    }


    public ContentValues valuePutter() {
        ContentValues values = new ContentValues();
        values.put(COL_TYPE, getType());
        return values;
    }






}
