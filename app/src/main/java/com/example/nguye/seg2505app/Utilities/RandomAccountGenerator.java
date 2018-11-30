package com.example.nguye.seg2505app.Utilities;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.nguye.seg2505app.Activities.Schedule;
import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.ScheduleClasses.ScheduleState;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.CustomSchedule;
import com.example.nguye.seg2505app.Storables.DefaultSchedule;
import com.example.nguye.seg2505app.Storables.OfferedService;
import com.example.nguye.seg2505app.Storables.Provider;
import com.example.nguye.seg2505app.Storables.ServiceType;
import com.example.nguye.seg2505app.Storables.Storable;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import java.util.Random;

/**
 * Generate a few new random accounts on every start
 */
public class RandomAccountGenerator {

    public static Account generateNewAccount(Context context) {
        Account account = new Account();
        account.setID(getNumOfAccounts(context) + 1);
        int type = new Random().nextInt(2) + 2;
        if (type == 2) {
            account.setCompanyName("The one and only Company");
            account.setDescription("Bla bla bla...");
            account.setLicensed(new Random().nextInt(2));
        }
        account.setType(type);
        account.setFirstName((Character.toString((char) (new Random().nextInt(26)+65))));
        account.setLastName((Character.toString((char) (new Random().nextInt(26)+65))));
        account.setEmail(account.getFirstName().toLowerCase() + account.getLastName().toLowerCase() + getNumOfAccounts(context) + "@" + type + "rga.com");
        account.setStreetNumber(999);
        account.setStreetName("1st Avenue");
        account.setCity("Ottawa");
        account.setProvince("Ontario");
        account.setCountry("Canada");
        account.setPostalCode("A1A1A1");
        String phoneNumber = "";
        for (int i = 0; i < 10; i++) {
            phoneNumber += new Random().nextInt(10);
        }
        account.setPhoneNumber(phoneNumber);
        account.setPassword("password");

        return account;
    }

    public static OfferedService generateNewService(Context context, Account account) {
        int type = new Random().nextInt(getNumOfServiceTypes(context)) + 1;
        double maxRate = Double.parseDouble(Storable.findFieldByID(context, ServiceType.TABLE_NAME, ServiceType.COL_MAXRATE, type));
        double hourlyRate = new Random().nextDouble()*(maxRate -25) + 25;
        OfferedService service = new OfferedService(type, account.getID(), hourlyRate);

        return service;
    }

    public static DefaultSchedule generateNewDefaultSchedule(Context context, Account account) {
        String randomDate = "2018-"
                + (new Random().nextInt(12) + 1) + "-"
                + (new Random().nextInt(28) + 1);
        randomDate = FormatValue.dateYMD(randomDate);

        int randomStartTime[] = new int[7];
        int randomEndTime[] = new int[7];
        for (int i = 0; i < 7; i++) {
            randomStartTime[i] = new Random().nextInt(1141) + 60;
            randomEndTime[i] = new Random().nextInt(1440 - randomStartTime[i] + 60) + randomStartTime[i] - 60;
        }

        DefaultSchedule schedule = new DefaultSchedule(account.getID(),
                randomDate,
                randomStartTime[0], randomEndTime[0],
                randomStartTime[1], randomEndTime[1],
                randomStartTime[2], randomEndTime[2],
                randomStartTime[3], randomEndTime[3],
                randomStartTime[4], randomEndTime[4],
                randomStartTime[5], randomEndTime[5],
                randomStartTime[6], randomEndTime[6]);

        return schedule;
    }

    public static CustomSchedule generateNewCustomSchedule(Context context, Account account) {
        String randomDate = new Random().nextInt(2) + 2018 + "-"
                + (new Random().nextInt(12) + 1) + "-"
                + (new Random().nextInt(28) + 1);
        randomDate = FormatValue.dateYMD(randomDate);

        int randomStartTime = new Random().nextInt(1141) + 60;
        int randomEndTime = new Random().nextInt(1440 - randomStartTime + 60) + randomStartTime - 60;

        ScheduleState state = ScheduleState.AVAILABLE;
        if (new Random().nextInt(2) == 0) {
            state = ScheduleState.UNAVAILABLE;
        }

        CustomSchedule schedule = new CustomSchedule(account.getID(),
                randomDate,
                randomStartTime,
                randomEndTime,
                state);

        return schedule;
    }

    public static void generateStuff(Context context, int numOfNew) {
        for (int n = 0; n < numOfNew; n++) {
            // Generate a new account, either Client or Provider
            Account account = generateNewAccount(context);
            account.add(context);

            if (account.getType() == 2) {
                // Associate new services to the provider
                int numOfServices = new Random().nextInt(3) + 1;
                for (int i = 0; i < numOfServices; i++) {
                    OfferedService service = generateNewService(context, account);
                    service.add(context);
                }

                // Associate new DefaultSchedules to the provider
                int numOfDefaultSchedules = new Random().nextInt(3) + 1;
                for (int i = 0; i < numOfDefaultSchedules; i++) {
                    DefaultSchedule schedule = generateNewDefaultSchedule(context, account);
                    schedule.add(context);
                    System.out.println(schedule);
                }

                // Associate 0 to 2 CustomSchedules to the provider
                int numOfCustomSchedules = new Random().nextInt(10);
                if (numOfCustomSchedules > 0) {
                    for (int i = 0; i < numOfCustomSchedules; i++) {
                        CustomSchedule schedule = generateNewCustomSchedule(context, account);
                        schedule.add(context);
                    }
                }
            }
        }
    }

    public static int getNumOfAccounts(Context context) {
        // From https://stackoverflow.com/questions/18097748/how-to-get-row-count-in-sqlite-using-android
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, Account.TABLE_NAME);
        db.close();
        return (int) count;
    }

    public static int getNumOfServiceTypes(Context context) {
        // From https://stackoverflow.com/questions/18097748/how-to-get-row-count-in-sqlite-using-android
        MyDBHandler dbHandler = new MyDBHandler(context);
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, ServiceType.TABLE_NAME);
        db.close();
        return (int) count;
    }
}
