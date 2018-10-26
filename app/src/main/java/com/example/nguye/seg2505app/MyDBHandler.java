package com.example.nguye.seg2505app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "TBD";

    // Structure of the table "Accounts"
    public static final String TABLE_ACCOUNTS = "Accounts";
//    public static final String ACCOUNTS_ID = "ID INTEGER PRIMARY KEY AUTOINCREMENT";

    public static final String ACCOUNTS_EMAIL = "Email";
    public static final String ACCOUNTS_TYPE = "Type";
    public static final String ACCOUNTS_FIRSTNAME = "FirstName";
    public static final String ACCOUNTS_LASTNAME = "LastName";
    public static final String ACCOUNTS_STREETNUMBER = "StreetNumber";
    public static final String ACCOUNTS_STREETNAME = "StreetName";
//    public static final String ACCOUNTS_APARTMENT = "Apartment";
    public static final String ACCOUNTS_CITY = "City";
    public static final String ACCOUNTS_PROVINCE = "Province";
    public static final String ACCOUNTS_COUNTRY = "Country";
    public static final String ACCOUNTS_POSTALCODE = "PostalCode";
    public static final String ACCOUNTS_PHONE = "PhoneNumber";
    public static final String ACCOUNTS_PASSWORD = "Password";


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
                + ACCOUNTS_EMAIL + " TEXT, "
                + ACCOUNTS_TYPE + " INTEGER, "
                + ACCOUNTS_FIRSTNAME + " TEXT, "
                + ACCOUNTS_LASTNAME + " TEXT, "
                + ACCOUNTS_STREETNUMBER + " INTEGER, "
                + ACCOUNTS_STREETNAME + " TEXT, "
//                + ACCOUNTS_APARTMENT + " TEXT, "
                + ACCOUNTS_CITY + " TEXT, "
                + ACCOUNTS_PROVINCE + " TEXT, "
                + ACCOUNTS_COUNTRY + " TEXT, "
                + ACCOUNTS_POSTALCODE + " TEXT, "
                + ACCOUNTS_PHONE + " BIGINT, "
                + ACCOUNTS_PASSWORD + " TEXT)";


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
        values.put(ACCOUNTS_EMAIL, account.getEmail());
        values.put(ACCOUNTS_TYPE, account.getType());
        values.put(ACCOUNTS_FIRSTNAME, account.getFirstName());
        values.put(ACCOUNTS_LASTNAME, account.getLastName());
        values.put(ACCOUNTS_STREETNUMBER, account.getStreetNumber());
        values.put(ACCOUNTS_STREETNAME, account.getStreetName());
//        values.put(ACCOUNTS_APARTMENT, account.getApartment());
        values.put(ACCOUNTS_CITY, account.getCity());
        values.put(ACCOUNTS_PROVINCE, account.getProvince());
        values.put(ACCOUNTS_COUNTRY, account.getCountry());
        values.put(ACCOUNTS_POSTALCODE, account.getPostalCode());
        values.put(ACCOUNTS_PHONE, account.getPhoneNumber());
        values.put(ACCOUNTS_PASSWORD, account.getPassword());


        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }

    /**
     * Deletes from the Accounts table the record that contains the specified email
     * @param email
     * @return
     */
    public boolean deleteAccount(String email) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query =
                "SELECT * FROM " + TABLE_ACCOUNTS
                + " WHERE " + ACCOUNTS_EMAIL + " = \"" + email + "\""
        ;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            String pkStr = cursor.getString(0); // primary key string
            db.delete(TABLE_ACCOUNTS, ACCOUNTS_EMAIL + " = \"" + pkStr + "\"", null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
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

                "SELECT * FROM " + TABLE_ACCOUNTS
                + " WHERE " + ACCOUNTS_EMAIL + " = \"" + email + "\""
        ;


        Cursor cursor = db.rawQuery(query, null);
        Account account = new Account();


        if(cursor.moveToFirst()) {
            account.setEmail(cursor.getString(0));
            account.setType(Integer.parseInt(cursor.getString(1)));
            account.setFirstName(cursor.getString(2));
            account.setLastName(cursor.getString(3));
            account.setStreetNumber(Integer.parseInt(cursor.getString(4)));
            account.setStreetName(cursor.getString(5));
//            account.setApartment(cursor.getString(6));
            account.setCity(cursor.getString(6));
            account.setProvince(cursor.getString(7));
            account.setCountry(cursor.getString(8));
            account.setPostalCode(cursor.getString(9));
            account.setPhoneNumber(Long.parseLong(cursor.getString(10)));
            account.setPassword(cursor.getString(11));
        } else {
            account = null;
        }
        db.close();
        return account;
    }

   /* public String[] getUsers{
        mysql> SELECT Email
                -> FROM customers;

    }*/
}