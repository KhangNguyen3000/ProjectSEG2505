package com.example.nguye.seg2505app;

import android.support.v7.app.AppCompatActivity;

import com.example.nguye.seg2505app.ScheduleClasses.DailySchedule;
import com.example.nguye.seg2505app.ScheduleClasses.ScheduleState;
import com.example.nguye.seg2505app.ScheduleClasses.TimeSlot;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.DefaultSchedule;
import com.example.nguye.seg2505app.Storables.Storable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class PunctualTest {
    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        System.out.println(calendar.getTimeInMillis());
        DateFormat df = new SimpleDateFormat("yy-MM-dd");
        System.out.println(df.format(date));

        for (int i = 0; i < 10; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
            date.setTime(calendar.getTimeInMillis());
            System.out.println(df.format(date));
        }

        String dateString = "2018-11-24";
        int sundayFieldIndex = (Storable.getFieldIndex(DefaultSchedule.COL_SUNDAYSTART, DefaultSchedule.COLUMNS) - 1);
        int dayOfWeekFieldIndex = sundayFieldIndex + (Calendar.DAY_OF_WEEK * 2) - 1;
        String effDateQuery = "SELECT " + DefaultSchedule.COLUMNS.get(dayOfWeekFieldIndex)[0] + ", "
                + DefaultSchedule.COLUMNS.get(dayOfWeekFieldIndex + 1)[0]
                + " FROM " + DefaultSchedule.TABLE_NAME
                + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE
                + " = (SELECT MAX(" + DefaultSchedule.COL_EFFECTIVEDATE
                + ") FROM " + DefaultSchedule.TABLE_NAME
                + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE + " <= \"" + dateString + "\")";
        System.out.println(effDateQuery);

        DailySchedule schedule = new DailySchedule(852, 1232, ScheduleState.AVAILABLE);
        TimeSlot ts = new TimeSlot(521, 1123, ScheduleState.UNAVAILABLE);
        schedule = schedule.merge(ts);
        System.out.println(schedule);
    }
}
