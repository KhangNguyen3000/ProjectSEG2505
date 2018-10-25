package com.example.nguye.seg2505app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TBD";

    // Structure of the table "Accounts"
    public static final String TABLE_ACCOUNTS = "Accounts";
//    public static final String ACCOUNTS_ID = "ID INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String ACCOUNTS_EMAIL = "Email TEXT";
    public static final String ACCOUNTS_TYPE = "Type INTEGER";
    public static final String ACCOUNTS_FIRSTNAME = "FirstName TEXT";
    public static final String ACCOUNTS_LASTNAME = "LastName TEXT";
    public static final String ACCOUNTS_STREETNUMBER = "StreetNumber INTEGER";
    public static final String ACCOUNTS_STREETNAME = "StreetName TEXT";
    public static final String ACCOUNTS_APPARTMENT = "Appartment TEXT";
    public static final String ACCOUNTS_CITY = "City TEXT";
    public static final String ACCOUNTS_PROVINCE = "Province TEXT";
    public static final String ACCOUNTS_COUNTRY = "Country TEXT";
    public static final String ACCOUNTS_POSTALCODE = "PostalCode TEXT";
    public static final String ACCOUNTS_PHONE = "PhoneNumber BIGINT";
    public static final String ACCOUNTS_PASSWORD = "StreetName TEXT";

    public static final String TABLE_SERVICES = "Services";
    public static final String SERVICES_ID = "ID INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final String SERVICES_TYPE = "Type INTEGER";
    public static final String SERVICES_PROVIDER = "Provider INTEGER";


//    public static final String TABLE_ACCOUNTTYPES = "AccountTypes";
//    public static final String ACCOUNTTYPES_ID_TITLE = "ID";
//    public static final String ACCOUNTTYPES_ID_TYPE = "INTEGER PRIMARY KEY";
//    public static final String ACCOUNTTYPES_TYPE_TITLE = "Type";
//    public static final String ACCOUNTTYPES_TYPE_TYPE = "TEXT";
//
//    public static final String TABLE_USERS = "Users";
//    public static final String USERS_ID_TITLE = "ID";
//    public static final String USERS_ID_TYPE = "INTEGER PRIMARY KEY";
//    public static final String USERS_TYPE_TITLE = "Type";
//    public static final String USERS_TYPE_TYPE = "TEXT";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_ACCOUNTS =
                "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + ACCOUNTS_EMAIL + ", "
                + ACCOUNTS_TYPE + ", "
                + ACCOUNTS_FIRSTNAME + ", "
                + ACCOUNTS_LASTNAME + ", "
                + ACCOUNTS_STREETNUMBER + ", "
                + ACCOUNTS_STREETNAME + ", "
                + ACCOUNTS_APPARTMENT + ", "
                + ACCOUNTS_CITY + ", "
                + ACCOUNTS_PROVINCE + ", "
                + ACCOUNTS_COUNTRY + ", "
                + ACCOUNTS_POSTALCODE + ", "
                + ACCOUNTS_PHONE + ", "
                + ACCOUNTS_PASSWORD + ")";

        String CREATE_TABLE_SERVICES =
                "CREATE TABLE " + TABLE_SERVICES + "("
                + SERVICES_ID + ", "
                + SERVICES_TYPE + ", "
                + SERVICES_PROVIDER + ")";

        db.execSQL(CREATE_TABLE_ACCOUNTS);
        db.execSQL(CREATE_TABLE_SERVICES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        onCreate(db);
    }

    // Adds an account to the database
    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACCOUNTS_TYPE, account.getAccountType());
        // ... all other data

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }


    /**
     * Retrieve an accounts information using the email as primary key
     * @param email
     * @return
     */
    // Later we can add different ways of searching, like a search by City
    // that returns an array of Account
    public Account findAccount(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query =
                "Select * FROM" + TABLE_ACCOUNTS
                + " WHERE " + ACCOUNTS_EMAIL + " = " + email;

        Cursor cursor = db.rawQuery(query, null);
        Account account = new Account();

        if(cursor.moveToFirst()) {
            account.setEmail(cursor.getString(0));
            account.setType(Integer.parseInt(cursor.getString(1)));
            account.setFirstName(cursor.getString(2));
            account.setLastName(cursor.getString(3));
            account.setStreetNumber(Integer.parseInt(cursor.getString(4)));
            account.setStreetName(cursor.getString(5));
            account.setAppartment(cursor.getString(6));
            account.setCity(cursor.getString(7));
            account.setProvince(cursor.getString(8));
            account.setCountry(cursor.getString(9));
            account.setPostalCode(cursor.getString(10));
            account.setPhoneNumber(Long.parseLong(cursor.getString(11)));
            account.setPassword(cursor.getString(12));
        }
    }
}