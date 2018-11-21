package com.example.nguye.seg2505app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.DefaultSchedule;
import com.example.nguye.seg2505app.Storables.OfferedService;
import com.example.nguye.seg2505app.Storables.ServiceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 22;
    private static final String DATABASE_NAME = "TBD";
    // The whole database's structure can be represented by a HashMap where the keys correspond
    //  to each table and the values are the fieldsets
    private static final HashMap<String, ArrayList<String[]>> DATABASE = new HashMap<>();

//    // Structure of the table "Accounts"
//    public static final String TABLE_ACCOUNTS = "Accounts";
//    public static final String ACCOUNTS_ID = "ID";
//    public static final String ACCOUNTS_EMAIL = "Email";
//    public static final String ACCOUNTS_TYPE = "Type";
//    public static final String ACCOUNTS_FIRSTNAME = "FirstName";
//    public static final String ACCOUNTS_LASTNAME = "LastName";
//    public static final String ACCOUNTS_STREETNUMBER = "StreetNumber";
//    public static final String ACCOUNTS_STREETNAME = "StreetName";
//    public static final String ACCOUNTS_CITY = "City";
//    public static final String ACCOUNTS_PROVINCE = "Province";
//    public static final String ACCOUNTS_COUNTRY = "Country";
//    public static final String ACCOUNTS_POSTALCODE = "PostalCode";
//    public static final String ACCOUNTS_PHONE = "PhoneNumber";
//    public static final String ACCOUNTS_PASSWORD = "Password";
//
//    // Structure of the table "ServiceTypes"
//    public static final String TABLE_SERVICETYPES = "ServiceTypes";
//    public static final String SERVICETYPES_ID = "ID";
//    public static final String SERVICETYPES_NAME = "Name";
//    public static final String SERVICETYPES_MAXRATE = "MaxRate";


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creating new database....");
        for (String table : DATABASE.keySet()) {
            String createTable = "CREATE TABLE " + table + "(";
            String fields = "";
            System.out.println("TABLE '" + table + "' created.");
            for (String[] column : DATABASE.get(table)) {
                fields = fields + column[0] + " " + column[1] + ", ";
                System.out.println(column[0] + "\t \t \t \t" + column[1]);
            }
            fields = fields.substring(0, fields.length() - 2) + ")"; // Remove the ", " after the last element and add ")"
//            fields = fields + ")";
            db.execSQL(createTable + fields);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Upgrading the database...");
        DATABASE.put(Account.TABLE_NAME, Account.COLUMNS);
        DATABASE.put(OfferedService.TABLE_NAME, OfferedService.COLUMNS);
        DATABASE.put(ServiceType.TABLE_NAME, ServiceType.COLUMNS);
        DATABASE.put(DefaultSchedule.TABLE_NAME, DefaultSchedule.COLUMNS);
        for (String table : DATABASE.keySet()) {
            db.execSQL("DROP TABLE IF EXISTS " + table);
            System.out.println("Table '" + table + "' dropped.");
        }
        db.execSQL("DROP TABLE IF EXISTS " + "ServicesTypes");
        db.execSQL("DROP TABLE IF EXISTS " + "ServicesType");
        onCreate(db);
    }
//
//    // Adds an account to the table Accounts
//    public void addAccount(Account account) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(ACCOUNTS_EMAIL, account.getEmail());
//        values.put(ACCOUNTS_TYPE, account.getType());
//        values.put(ACCOUNTS_FIRSTNAME, account.getFirstName());
//        values.put(ACCOUNTS_LASTNAME, account.getLastName());
//        values.put(ACCOUNTS_STREETNUMBER, account.getStreetNumber());
//        values.put(ACCOUNTS_STREETNAME, account.getStreetName());
//        values.put(ACCOUNTS_CITY, account.getCity());
//        values.put(ACCOUNTS_PROVINCE, account.getProvince());
//        values.put(ACCOUNTS_COUNTRY, account.getCountry());
//        values.put(ACCOUNTS_POSTALCODE, account.getPostalCode());
//        values.put(ACCOUNTS_PHONE, account.getPhoneNumber());
//        values.put(ACCOUNTS_PASSWORD, account.getPassword());
//
//        db.insert(TABLE_ACCOUNTS, null, values);
//        db.close();
//    }

//    // Modify an account in the table Accounts
//    public void updateAccount(Account account) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
////        values.put(ACCOUNTS_ID, account.getId());
//        values.put(ACCOUNTS_EMAIL, account.getEmail());
//        values.put(ACCOUNTS_TYPE, account.getType());
//        values.put(ACCOUNTS_FIRSTNAME, account.getFirstName());
//        values.put(ACCOUNTS_LASTNAME, account.getLastName());
//        values.put(ACCOUNTS_STREETNUMBER, account.getStreetNumber());
//        values.put(ACCOUNTS_STREETNAME, account.getStreetName());
//        values.put(ACCOUNTS_CITY, account.getCity());
//        values.put(ACCOUNTS_PROVINCE, account.getProvince());
//        values.put(ACCOUNTS_COUNTRY, account.getCountry());
//        values.put(ACCOUNTS_POSTALCODE, account.getPostalCode());
//        values.put(ACCOUNTS_PHONE, account.getPhoneNumber());
//        values.put(ACCOUNTS_PASSWORD, account.getPassword());
//
//        db.update(TABLE_ACCOUNTS, values, "ID=" + account.getId(), null);
//        System.out.println(account.getId());
//        db.close();
//    }

//    /**
//     * Deletes from the Accounts table the record that contains the specified email
//     * @param email
//     * @return
//     */
//    public boolean deleteAccount(String email) {
//        boolean result = false;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String query =
//                "SELECT * FROM " + TABLE_ACCOUNTS
//                        + " WHERE " + ACCOUNTS_EMAIL + " = \"" + email + "\""
//                ;
//        Cursor cursor = db.rawQuery(query, null);
//        if(cursor.moveToFirst()){
//            String pkStr = cursor.getString(1); // primary key string
//            db.delete(TABLE_ACCOUNTS, ACCOUNTS_EMAIL + " = \"" + pkStr + "\"", null);
//            cursor.close();
//            result = true;
//        }
//        db.close();
//        return result;
//    }

//    /**
//     * Retrieve an accounts information using the email as primary key
//     * @param email
//     * @return
//     */
//    // Later we can add different ways of searching, like a search by City
//    // that returns an array of Account
//    public Account findAccount(String email) {
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query =
//                "SELECT * FROM " + TABLE_ACCOUNTS
//                        + " WHERE " + ACCOUNTS_EMAIL + " = \"" + email + "\""
//                ;
//
//        // Create an instance of Account and set its attributes to the values found in the record
//        //  with the corresponding email address.
//        Account account = new Account();
//        Cursor cursor = db.rawQuery(query, null);
//        if(cursor.moveToFirst()) {
//            account.setId(cursor.getInt(0));
//            account.setEmail(cursor.getString(1));
//            account.setType(Integer.parseInt(cursor.getString(2)));
//            account.setFirstName(cursor.getString(3));
//            account.setLastName(cursor.getString(4));
//            account.setStreetNumber(cursor.getInt(5));
//            account.setStreetName(cursor.getString(6));
//            account.setCity(cursor.getString(7));
//            account.setProvince(cursor.getString(8));
//            account.setCountry(cursor.getString(9));
//            account.setPostalCode(cursor.getString(10));
//            account.setPhoneNumber(cursor.getString(11));
//            account.setPassword(cursor.getString(12));
//        } else {
//            account = null;
//        }
//        db.close();
//        return account;
//    }
//
//    /**
//     * Adds a service type to the table ServiceTypes
//     * @param serviceType
//     */
//    public void addServiceType(ServiceType serviceType) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(SERVICETYPES_NAME, serviceType.getName());
//        values.put(SERVICETYPES_MAXRATE, serviceType.getRate());
//
//        db.insert(TABLE_SERVICETYPES, null, values);
//        db.close();
//    }
//
//    /**
//     * Update a service type in the table ServiceTypes
//     * @param serviceType
//     */
//    public void updateServiceType(ServiceType serviceType) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(SERVICETYPES_NAME, serviceType.getName());
//        values.put(SERVICETYPES_MAXRATE, serviceType.getRate());
//
//        db.update(TABLE_SERVICETYPES, values, "ID=" + serviceType.getID(), null);
//        db.close();
//    }
//
//    /**
//     * Delete a service type from the table ServiceTypes
//     * @param serviceType
//     * @return whether the deletion was successful
//     */
//    public boolean deleteServiceType(ServiceType serviceType) {
//        boolean result;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        result = db.delete(TABLE_SERVICETYPES,SERVICETYPES_ID + " = " + serviceType.getID(), null) > 0;
//
//        db.close();
//        return result;
//    }
//
//    public ServiceType findServiceType(String name){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query =
//                "SELECT * FROM " + TABLE_SERVICETYPES
//                        + " WHERE " + SERVICETYPES_NAME + " = \"" + name + "\""
//                ;
//        Cursor cursor = db.rawQuery(query, null);
//        if(cursor.moveToFirst()) {
//            ServiceType service = new ServiceType(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2));
//            db.close();
//            return service;
//        } else {
//            ServiceType service = null;
//            db.close();
//            return service;
//        }
//    }

    /**
     * Add an administrator account to the database
     */
    public void createAdmin(Context context) {
        // Set all the attributes of the admin account.
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

        // Add the account to the database
//        addAccount(account);
        account.add(context);
//        System.out.println("Administrateur créé");
    }

    /**
     * Check if an account of the specifies type already exists in the database.
     *
     * @param type (1 = Administrator, 2 = Provider, 3 = Client)
     * @return true if an account of the specified type exists in the database
     */
    public boolean existsType(int type) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Account.TABLE_NAME
                + " WHERE " + Account.COL_TYPE + " = " + type;
        System.out.println(query);

        Cursor cursor = db.rawQuery(query, null);
        return cursor.moveToFirst();
//        if (cursor.moveToFirst()) {
//            return true;
//        } else {
//            return false;
//        }
    }

    /**
     * Return a List of string that contains the value of the specified field in the specified table
     *
     * @param elem,  column/field name in the table
     * @param table, name of the table
     * @return
     */

    public List<String> getList(String elem, String table) {
        List<String> mArrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + elem + " FROM " + table;
        Cursor users = db.rawQuery(query, null);
        users.moveToFirst();
        while (!users.isAfterLast()) {
            mArrayList.add(users.getString(0)); //add the item
            users.moveToNext();
        }
        return mArrayList;
    }

    public List<String> getListKey(String searchElem, String searchTable, String key, String elem, String table) {
        List<String> mArrayList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String searchQuery = "SELECT " + searchElem + " FROM " + searchTable;
        String query = "SELECT " + elem + " FROM " + table;
        Cursor search = db.rawQuery(searchQuery, null);
        Cursor out = db.rawQuery(query, null);
        search.moveToFirst();
        out.moveToFirst();
        while (!search.isAfterLast()) {
            if (search.getString(0) == "key") {
                mArrayList.add(out.getString(0)); //add the item
                search.moveToNext();
                out.moveToNext();
            }
        }
        return mArrayList;

    }
//    public static void setDatabaseStructure() {
//        DATABASE.put("Services", OfferedService.FIELDS);
//    }
}
