package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.ScheduleClasses.CurrentUserSchedule;
import com.example.nguye.seg2505app.ScheduleClasses.DailySchedule;
import com.example.nguye.seg2505app.ScheduleClasses.Day;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.DefaultSchedule;
import com.example.nguye.seg2505app.Storables.Storable;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ScheduleManagement extends AppCompatActivity {
    
    private int mTextColor, mBorderColor;
    private int mTextViewBorderWidth, mTableBorderWidth;
    final int numOfDaysToShow = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_management);
        mTextColor = 0xff111111;
        mBorderColor = 0xAA444444;
        mTextViewBorderWidth = 1;
        mTableBorderWidth = mTextViewBorderWidth * 2;
        TextView tvNextNDays = findViewById(R.id.clarificationText);
        tvNextNDays.setText("My availabilities for the next " + numOfDaysToShow + " days.");
        TableLayout layout = (TableLayout)findViewById(R.id.layout);
        setupTable(layout);

        // TODO implement the self-cleaning features for CustomSchedules and DefaultSchedules
        // Might not be necessary since there will never be a lot of data for this project, but would be fun
    }


    public void onClickDefaultScheduleButton (View view) {
        Intent intent = new Intent(getApplicationContext(), DefaultAvail.class);
        Account provider = new Account().find(this, Account.COL_ID, Account.getCurrentAccount().getID(), false);
        intent.putExtra("schedule", provider);
        startActivity(intent);
    }

    public void onClickCustomScheduleButton(View view){
        Intent intent = new Intent(this, CustomDispo.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        TableLayout layout = (TableLayout) findViewById(R.id.layout);
        layout.removeAllViews();
        setupTable(layout);
        generateSchedule();
    }

    // TODO days are shifted (monday's schedule is written beside Sunday, tuesday beside monday, etc)
    private void setupTable(TableLayout layout){
        Calendar cal = Calendar.getInstance();
        TableRow tableRow;
        TextView textView, textView2;
        for(int rowNumber = 0; rowNumber < numOfDaysToShow; rowNumber++){
            tableRow = new TableRow(this);

            textView = new TextView(this);
            textView2 = new TextView(this);
            textView.setTextColor(mTextColor);
            textView.setGravity(Gravity.LEFT);
            textView.setPadding(0, 6, 6, 6);
            textView2.setGravity(Gravity.RIGHT);
            textView2.setPadding(0, 6, 0, 6);

            int day = cal.get(Calendar.DAY_OF_WEEK);
            DefaultSchedule schedule = new DefaultSchedule().find(this, DefaultSchedule.COL_PROVIDER, Account.getCurrentAccount().getID(), false);

            String startTime = "0";
            String endTime = "0";
            String weekDay = "";
            switch (day) {
                case Calendar.SUNDAY:
                    startTime = FormatValue.minToTimeString(schedule.getStartTimes(6));
                    endTime = FormatValue.minToTimeString(schedule.getEndTimes(6));
                    weekDay = "Sun";
                    break;
                case Calendar.MONDAY:
                    startTime = FormatValue.minToTimeString(schedule.getStartTimes(0));
                    endTime = FormatValue.minToTimeString(schedule.getEndTimes(0));
                    weekDay = "Mon";
                    break;
                case Calendar.TUESDAY:
                    startTime = FormatValue.minToTimeString(schedule.getStartTimes(1));
                    endTime = FormatValue.minToTimeString(schedule.getEndTimes(1));
                    weekDay = "Tue";
                    break;
                case Calendar.WEDNESDAY:
                    startTime = FormatValue.minToTimeString(schedule.getStartTimes(2));
                    endTime = FormatValue.minToTimeString(schedule.getEndTimes(2));
                    weekDay = "Wed";
                    break;
                case Calendar.THURSDAY:
                    startTime = FormatValue.minToTimeString(schedule.getStartTimes(3));
                    endTime = FormatValue.minToTimeString(schedule.getEndTimes(3));
                    weekDay = "Thu";
                    break;
                case Calendar.FRIDAY:
                    startTime = FormatValue.minToTimeString(schedule.getStartTimes(4));
                    endTime = FormatValue.minToTimeString(schedule.getEndTimes(4));
                    weekDay = "Fri";
                    break;
                case Calendar.SATURDAY:
                    startTime = FormatValue.minToTimeString(schedule.getStartTimes(5));
                    endTime = FormatValue.minToTimeString(schedule.getEndTimes(5));
                    weekDay = "Sat";
                    break;
            }
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            //textView2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            textView.setText(weekDay+" "+FormatValue.dateYMD(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH)));
            textView2.setText(startTime+" - "+endTime);
            System.out.println(textView2.getText().toString());
            tableRow.addView(textView);
            tableRow.addView(textView2);
            /*
            if(tableRow.getParent() != null){
                ((ViewGroup)tableRow.getParent()).removeView(tableRow);
            }
            */

            layout.addView(tableRow);
//            cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

    }

//    public void generateSchedule() {
//        // Check if the current user has set up his default availabilities
//        String existCheck = DefaultSchedule.COL_PROVIDER + " = " + Account.getCurrentAccount().getID();
//        if (Storable.exists(this, DefaultSchedule.TABLE_NAME, existCheck)) {
//            // TODO add the code that checks what has to be generated
//            // Initialize all the necessary stuff
//            ArrayList<Day> upcomingSchedule;
//            boolean exists;
//            if (CurrentUserSchedule.get() == null) { // The CurrentUserSchedule has not been loaded since the user logged in
//                upcomingSchedule = new ArrayList<>();
//                exists = false;
//            } else {
//                upcomingSchedule = CurrentUserSchedule.get();
//                exists = true;
//            }
//            Day day;
//            Calendar calendar = Calendar.getInstance();
//            Date date = new Date();
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            String dateString;
//            DailySchedule schedule;
//            Boolean isLoaded;
//            int dayOfWeekFieldIndex;
//            int sundayFieldIndex = (Storable.getFieldIndex(DefaultSchedule.COL_SUNDAYSTART, DefaultSchedule.COLUMNS) - 1); // Index of the SundayStartTime field.
//            // Retrieved like this instead of using a constant for maintainability in case some fields are added or removed.
//            for (int i = 0; i < numOfDaysToShow; i++) {
//                // Check if the schedule for the current day has to be generated to save time if not necessary
//                boolean toGenerate = false;
//                if (!exists) {
//                    toGenerate = true;
//                } else {
//                    if (!upcomingSchedule.get(i).isLoaded()) {
//                        toGenerate = true;
//                    }
//                }
//                if (toGenerate) {
//                    System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
//                    System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
//                    System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
//                    System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
//                    System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
//                    // Generate the date as a String
//                    date.setTime(calendar.getTimeInMillis());
//                    dateString = df.format(date);
//
//                    // Generate the DailySchedule from the data in the table DefaultSchedules and CustomSchedules
//                    // TODO create a DailySchedule using the DefaultSchedule and CustomSchedule from the database.
//                    //  1. Initialize a new DailySchedule by using the right fields from DefaultSchedules
//                    //  2. For each record in CustomSchedule that meet the right conditions, merge one by one with the DailySchedule.
//                    dayOfWeekFieldIndex = sundayFieldIndex + (calendar.get(Calendar.DAY_OF_WEEK) * 2) - 1;
//
//                    // TODO will have to be a range instead of =
//                    String effDateQuery = "SELECT " + DefaultSchedule.COLUMNS.get(dayOfWeekFieldIndex)[0] + ", "
//                            + DefaultSchedule.COLUMNS.get(dayOfWeekFieldIndex + 1)[0]
//                            + " FROM " + DefaultSchedule.TABLE_NAME
//                            + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE
//                            + " = (SELECT MAX(" + DefaultSchedule.COL_EFFECTIVEDATE
//                            + ") FROM " + DefaultSchedule.TABLE_NAME
//                            + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE + " <= \"" + dateString + "\")";
//                    System.out.println(effDateQuery);
//
//                    ArrayList<String[]> currentEffDate = Storable.select(this, effDateQuery, 2);
////            String where = "ProviderID = " + Account.getCurrentAccount().getID() + " AND EffectiveDate = \"" + dateString + "\"";
////            System.out.println("WHERE " + where);
////            switch (Calendar.DAY_OF_WEEK) {
////                case Calendar.SUNDAY:
////
////            }
////            ArrayList<String> record = Storable.selectAllInRow(this, DefaultSchedule.COLUMNS.size(), DefaultSchedule.TABLE_NAME, where);
//
//                    // Initialize a new DailySchedule using the data from the table
////            String startTime = Storable.selectFirst(this, DefaultSchedule.COLUMNS.get(dayOfWeekFieldIndex)[0], DefaultSchedule.TABLE_NAME, where);
////            String endTime = Storable.selectFirst(this, DefaultSchedule.COLUMNS.get(dayOfWeekFieldIndex + 1)[0], DefaultSchedule.TABLE_NAME, where);
//                    String startTime = currentEffDate.get(0)[0];
//                    String endTime = currentEffDate.get(0)[1];
//                    schedule = new DailySchedule(Integer.parseInt(startTime), Integer.parseInt(endTime), ScheduleState.AVAILABLE);
//
//                    // For each record in CustomSchedule that meet the right conditions, merge one by one with the DailySchedule.
//                    String query = "SELECT " + CustomSchedule.COL_START + ", " + CustomSchedule.COL_END + ", "
//                            + CustomSchedule.COL_AVAILABILITY + " FROM " + CustomSchedule.TABLE_NAME
//                            + " WHERE " + CustomSchedule.COL_DATE + " = \"" + dateString + "\" AND " + CustomSchedule.COL_PROVIDER
//                            + " = " + Account.getCurrentAccount().getID();
//                    System.out.println(query);
//
//                    ArrayList<String[]> customSchedules = Storable.select(this, query, 3);
//                    int timeSlotStart;
//                    int timeSlotEnd;
//                    ScheduleState state;
//                    for (String[] record : customSchedules) {
//                        timeSlotStart = Integer.parseInt(record[0]);
//                        timeSlotEnd = Integer.parseInt(record[1]);
//                        state = ScheduleState.valueOf(record[2]);
//                        TimeSlot timeSlot = new TimeSlot(timeSlotStart, timeSlotEnd, state);
//                        // TODO check if the problem is fixed
//                        schedule = schedule.merge(timeSlot); // Merge
//                    }
//
//                    // Set the flag to true to indicate that the DailySchedule has been generate since the last modification
//                    isLoaded = true;
//
//                    day = new Day(dateString, schedule, isLoaded);
//
//                    if (!exists) { // If the schedule is being generated from scratch, add the day to the schedule
//                        upcomingSchedule.add(day);
//                    } else { // else, update it
//                        upcomingSchedule.set(i, day);
//                    }
//                }
//                // Increment the day to repeat the process for the next day.
//                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
//            }
//
////        for (TableRow3Col<String, DailySchedule, Boolean> row : upcomingSchedule) {
////            System.out.println(row.getCol1() + " | " + row.getCol2() + " | " + row.getCol3());
////        }
//            //
//            CurrentUserSchedule.set(upcomingSchedule);
//
//            // TODO the DailySchedule has not updated properly
//            System.out.println(upcomingSchedule.size());
//            for (int i = 0; i < numOfDaysToShow; i++) {
//                System.out.println(CurrentUserSchedule.get().get(i).getDateString()
//                        + " : " + CurrentUserSchedule.get().get(i).getSchedule()
//                        + ", " + CurrentUserSchedule.get().get(i).isLoaded());
//            }
//        } else {
//            System.out.println("The current account has not setup a default schedule yet.");
//        }
//    }

    public void generateSchedule() {
        // Check if the current user has set up his default availabilities
        String existCheck = DefaultSchedule.COL_PROVIDER + " = " + Account.getCurrentAccount().getID();
        if (Storable.exists(this, DefaultSchedule.TABLE_NAME, existCheck)) {
            // TODO add the code that checks what has to be generated
            // Initialize all the necessary stuff
            ArrayList<Day> upcomingSchedule;
            boolean exists;
            if (CurrentUserSchedule.get() == null) { // The CurrentUserSchedule has not been loaded since the user logged in
                upcomingSchedule = new ArrayList<>();
                exists = false;
            } else {
                upcomingSchedule = CurrentUserSchedule.get();
                exists = true;
            }

            Day day;
            Calendar calendar = Calendar.getInstance();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = df.format(calendar.getTime());
            DailySchedule schedule;
            Boolean isLoaded;
            for (int i = 0; i < numOfDaysToShow; i++) {
                // Check if the schedule for the current day has to be generated to save time if not necessary
                boolean toGenerate = false;
                if (!exists) {
                    toGenerate = true;
                } else {
                    if (!upcomingSchedule.get(i).isLoaded()) {
                        toGenerate = true;
                    }
                }
                if (toGenerate) {
                    schedule = new DailySchedule().generate(this, Account.getCurrentAccount().getID(), dateString);
                    // Set the flag to true to indicate that the DailySchedule has been generate since the last modification
                    isLoaded = true;
                    day = new Day(dateString, schedule, isLoaded);

                    if (!exists) { // If the schedule is being generated from scratch, add the day to the schedule
                        upcomingSchedule.add(day);
                    } else { // else, update it
                        upcomingSchedule.set(i, day);
                    }
                }
                // Increment the day to repeat the process for the next day.
                dateString = FormatValue.incrementDate(dateString);
            }

//        for (TableRow3Col<String, DailySchedule, Boolean> row : upcomingSchedule) {
//            System.out.println(row.getCol1() + " | " + row.getCol2() + " | " + row.getCol3());
//        }
            //
            CurrentUserSchedule.set(upcomingSchedule);

            // TODO the DailySchedule has not updated properly
            System.out.println(upcomingSchedule.size());
            for (int i = 0; i < numOfDaysToShow; i++) {
                System.out.println(CurrentUserSchedule.get().get(i).getDateString()
                        + " : " + CurrentUserSchedule.get().get(i).getSchedule()
                        + ", " + CurrentUserSchedule.get().get(i).isLoaded());
            }
        } else {
            System.out.println("The current account has not setup a default schedule yet.");
        }
    }
}
