package com.example.nguye.seg2505app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NAME = "TBD";

    // Structure of the table "Accounts"
    public static final String TABLE_ACCOUNTS = "Accounts";
    public static final String ACCOUNTS_ID = "ID";
    public static final String ACCOUNTS_EMAIL = "Email";
    public static final String ACCOUNTS_TYPE = "Type";
    public static final String ACCOUNTS_FIRSTNAME = "FirstName";
    public static final String ACCOUNTS_LASTNAME = "LastName";
    public static final String ACCOUNTS_STREETNUMBER = "StreetNumber";
    public static final String ACCOUNTS_STREETNAME = "StreetName";
    public static final String ACCOUNTS_CITY = "City";
    public static final String ACCOUNTS_PROVINCE = "Province";
    public static final String ACCOUNTS_COUNTRY = "Country";
    public static final String ACCOUNTS_POSTALCODE = "PostalCode";
    public static final String ACCOUNTS_PHONE = "PhoneNumber";
    public static final String ACCOUNTS_PASSWORD = "Password";

    // Structure of the table "ServiceTypes"
    public static final String TABLE_SERVICETYPES = "ServiceTypes";
    public static final String SERVICETYPES_ID = "ID";
    public static final String SERVICETYPES_TYPE = "Type";
    public static final String SERVICETYPES_MAXRATE = "MaxRate";


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_ACCOUNTS =
                "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + ACCOUNTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACCOUNTS_EMAIL + " TEXT, "
                + ACCOUNTS_TYPE + " INTEGER, "
                + ACCOUNTS_FIRSTNAME + " TEXT, "
                + ACCOUNTS_LASTNAME + " TEXT, "
                + ACCOUNTS_STREETNUMBER + " INTEGER, "
                + ACCOUNTS_STREETNAME + " TEXT, "
                + ACCOUNTS_CITY + " TEXT, "
                + ACCOUNTS_PROVINCE + " TEXT, "
                + ACCOUNTS_COUNTRY + " TEXT, "
                + ACCOUNTS_POSTALCODE + " TEXT, "
                + ACCOUNTS_PHONE + " TEXT, "
                + ACCOUNTS_PASSWORD + " TEXT)";

        String CREATE_TABLE_SERVICETYPES =
                "CREATE TABLE " + TABLE_SERVICETYPES + "("
                + SERVICETYPES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SERVICETYPES_TYPE + " TEXT, "
                + SERVICETYPES_MAXRATE + " DOUBLE)";

        db.execSQL(CREATE_TABLE_ACCOUNTS);
        db.execSQL(CREATE_TABLE_SERVICETYPES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICETYPES);
        onCreate(db);
    }

    // Adds an account to the table Accounts
    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACCOUNTS_EMAIL, account.getEmail());
        values.put(ACCOUNTS_TYPE, account.getType());
        values.put(ACCOUNTS_FIRSTNAME, account.getFirstName());
        values.put(ACCOUNTS_LASTNAME, account.getLastName());
        values.put(ACCOUNTS_STREETNUMBER, account.getStreetNumber());
        values.put(ACCOUNTS_STREETNAME, account.getStreetName());
        values.put(ACCOUNTS_CITY, account.getCity());
        values.put(ACCOUNTS_PROVINCE, account.getProvince());
        values.put(ACCOUNTS_COUNTRY, account.getCountry());
        values.put(ACCOUNTS_POSTALCODE, account.getPostalCode());
        values.put(ACCOUNTS_PHONE, account.getPhoneNumber());
        values.put(ACCOUNTS_PASSWORD, account.getPassword());

        db.insert(TABLE_ACCOUNTS, null, values);
        db.close();
    }

    // Modify an account in the table Accounts
    public void updateAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(ACCOUNTS_ID, account.getId());
        values.put(ACCOUNTS_EMAIL, account.getEmail());
        values.put(ACCOUNTS_TYPE, account.getType());
        values.put(ACCOUNTS_FIRSTNAME, account.getFirstName());
        values.put(ACCOUNTS_LASTNAME, account.getLastName());
        values.put(ACCOUNTS_STREETNUMBER, account.getStreetNumber());
        values.put(ACCOUNTS_STREETNAME, account.getStreetName());
        values.put(ACCOUNTS_CITY, account.getCity());
        values.put(ACCOUNTS_PROVINCE, account.getProvince());
        values.put(ACCOUNTS_COUNTRY, account.getCountry());
        values.put(ACCOUNTS_POSTALCODE, account.getPostalCode());
        values.put(ACCOUNTS_PHONE, account.getPhoneNumber());
        values.put(ACCOUNTS_PASSWORD, account.getPassword());

        db.update(TABLE_ACCOUNTS, values, "ID=" + account.getId(), null);
        System.out.println(account.getId());
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
            String pkStr = cursor.getString(1); // primary key string
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

        // Create an instance of Account and set its attributes to the values found in the record
        //  with the corresponding email address.
        Account account = new Account();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            account.setId(cursor.getInt(0));
            account.setEmail(cursor.getString(1));
            account.setType(Integer.parseInt(cursor.getString(2)));
            account.setFirstName(cursor.getString(3));
            account.setLastName(cursor.getString(4));
            account.setStreetNumber(cursor.getInt(5));
            account.setStreetName(cursor.getString(6));
            account.setCity(cursor.getString(7));
            account.setProvince(cursor.getString(8));
            account.setCountry(cursor.getString(9));
            account.setPostalCode(cursor.getString(10));
            account.setPhoneNumber(cursor.getString(11));
            account.setPassword(cursor.getString(12));
        } else {
            account = null;
        }
        db.close();
        return account;
    }

    // Adds a service type to the table ServiceTypes
    public void addServiceType(String type, double maxRate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SERVICETYPES_TYPE, type);
        values.put(SERVICETYPES_MAXRATE, maxRate);

        db.insert(TABLE_SERVICETYPES, null, values);
        db.close();
    }


    public void modifyServiceType(int id, String type, double maxRate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SERVICETYPES_TYPE, type);
        values.put(SERVICETYPES_TYPE, maxRate);

        db.update(TABLE_SERVICETYPES, values, "ID="+id, null);
        db.close();
    }

    public boolean deleteServiceType(int id) {
        boolean result;
        SQLiteDatabase db = this.getWritableDatabase();

        result = db.delete(TABLE_SERVICETYPES,SERVICETYPES_ID + " = " + id, null) > 0;

        db.close();
        return result;
//        String query =
//                "SELECT * FROM " + TABLE_SERVICETYPES
//                        + " WHERE " + SERVICETYPES_ID + " = " + id
//                ;
//        Cursor cursor = db.rawQuery(query, null);
//        if(cursor.moveToFirst()){
//            int primaryKey = Integer.parseInt(cursor.getString(0)); // primary key
//            db.delete(TABLE_SERVICETYPES, SERVICETYPES_ID + " = " + primaryKey, null);
//            cursor.close();
//            result = true;
//        }
//        db.close();
//        return result;
    }


    public void createAdmin(){
        Account account = new Account();
        account.setFirstName("admin");
        account.setLastName("admin");
        account.setEmail("admin@admin.admin");
        account.setPassword("admin0");
        account.setStreetNumber(0);
        account.setStreetName("admin");
        account.setCity("admin");
        account.setProvince("admin");
        account.setCountry("admin");
        account.setPostalCode("a0a0a0");
        account.setPhoneNumber("1000000000");
        account.setType(1);

        addAccount(account);

        System.out.println("Administrateur créé");
    }

    public boolean existsType(int type) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =

                "SELECT * FROM " + TABLE_ACCOUNTS
                        + " WHERE " + ACCOUNTS_TYPE + " = " + type
                ;


        Cursor cursor = db.rawQuery(query, null);


        if(cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getUsers (){
        List<String> mArrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query ="SELECT " + ACCOUNTS_EMAIL + " FROM "+ TABLE_ACCOUNTS;
        Cursor users = db.rawQuery(query,null);
        users.moveToFirst();
        while(!users.isAfterLast()) {
            mArrayList.add(users.getString(0)); //add the item
            users.moveToNext();
        }
        return mArrayList;
    }

}