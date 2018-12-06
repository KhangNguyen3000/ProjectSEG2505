package com.example.nguye.seg2505app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.CustomSchedule;
import com.example.nguye.seg2505app.Storables.DefaultSchedule;
import com.example.nguye.seg2505app.Storables.OfferedService;
import com.example.nguye.seg2505app.Storables.Rating;
import com.example.nguye.seg2505app.Storables.ServiceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 104;
    private static final String DATABASE_NAME = "TBD";
    // The whole database's structure can be represented by a HashMap where the keys correspond
    //  to each table and the values are the fieldsets
    private static final HashMap<String, ArrayList<String[]>> DATABASE = new HashMap<>();


    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
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
        DATABASE.put(CustomSchedule.TABLE_NAME, CustomSchedule.COLUMNS);
        DATABASE.put(Rating.TABLE_NAME, Rating.COLUMNS);
        for (String table : DATABASE.keySet()) {
            db.execSQL("DROP TABLE IF EXISTS " + table);
            System.out.println("Table '" + table + "' dropped.");
        }
        db.execSQL("DROP TABLE IF EXISTS " + "ServicesTypes");
        db.execSQL("DROP TABLE IF EXISTS " + "ServicesType");
        onCreate(db);
    }

    /**
     * Add an administrator account to the database
     */
    public void createAdmin(Context context){
        // Set all the attributes of the admin account.
        Account account = new Account();
        account.setFirstName("admin");
        account.setLastName("admin");
        account.setEmail("admin@admin.admin");
        account.setPassword(hash("admin0"));
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

    public void createProvider(Context context){
        // Set all the attributes of the admin account.
        Account account = new Account();
        account.setFirstName("Provider");
        account.setLastName("Provider");
        account.setEmail("pro@pro.pro");
        account.setPassword(hash("pro123"));
        account.setStreetNumber(1);
        account.setStreetName("provider");
        account.setCity("Provider");
        account.setProvince("Provider");
        account.setCountry("Provider");
        account.setPostalCode("A1A1A1");
        account.setPhoneNumber("1234567890");
        account.setType(2);

        // Add the account to the database
        account.add(context);
    }

    public void createClient(Context context){
        // Set all the attributes of the admin account.
        Account account = new Account();
        account.setFirstName("Client");
        account.setLastName("Client");
        account.setEmail("client@client.client");
        account.setPassword(hash("client"));
        account.setStreetNumber(3);
        account.setStreetName("client");
        account.setCity("Client");
        account.setProvince("Client");
        account.setCountry("Client");
        account.setPostalCode("A2A2A2");
        account.setPhoneNumber("0987654321");
        account.setType(3);

        // Add the account to the database
//        addAccount(account);
        account.add(context);
//        System.out.println("Administrateur créé");
    }

    static int hash(String s){
        int hash = 7;
        for(int i = 0; i < s.length(); i++){
            hash = hash*31 + s.charAt(i);
        }
        return hash;
    }

    public void createServiceTypes(Context context) {
        ServiceType service1 = new ServiceType("Plumber", 100);
        service1.add(context);
        ServiceType service2 = new ServiceType("Wifi Dude", 50);
        service2.add(context);
        ServiceType service3 = new ServiceType("Exterminator", 150);
        service3.add(context);
        ServiceType service4 = new ServiceType("Snow Remover", 75);
        service4.add(context);
        ServiceType service5 = new ServiceType("Caterer", 200);
        service5.add(context);
        ServiceType service6 = new ServiceType("Painter", 100);
        service6.add(context);
        ServiceType service7 = new ServiceType("Electrician", 150);
        service7.add(context);
        ServiceType service8 = new ServiceType("Lopper", 100);
        service8.add(context);
        ServiceType service9 = new ServiceType("Gardener", 200);
        service9.add(context);
        ServiceType service10 = new ServiceType("Driver", 50);
        service10.add(context);
    }

    /**
     * Check if an account of the specifies type already exists in the database.
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
     * @param elem, column/field name in the table
     * @param table, name of the table
     * @return
     */
    public List<String> getList(String elem, String table){
        List<String> mArrayList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + elem + " FROM "+ table;
        Cursor users = db.rawQuery(query,null);
        users.moveToFirst();
        while(!users.isAfterLast()) {
            mArrayList.add(users.getString(0)); //add the item
            users.moveToNext();
        }
        return mArrayList;
    }
}