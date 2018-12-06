package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CalendarView;

import com.example.nguye.seg2505app.Activities.ScheduleManagement;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.ScheduleAdapter;
import com.example.nguye.seg2505app.ScheduleClasses.DailySchedule;
import com.example.nguye.seg2505app.ScheduleClasses.TimeNode;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Schedule extends AppCompatActivity {
// From https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
//  and https://developer.android.com/guide/topics/ui/layout/recyclerview#java

    private String selectedDate;
    private CalendarView calendarView;

    private RecyclerView recyclerView;
    private ScheduleAdapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.daily_schedule);
        recyclerView.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

        // Set the minimum date to today
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dfMinDate = new SimpleDateFormat("MM/dd/yyyy");
        selectedDate = dfMinDate.format(calendar.getTime());

        // Store today's date in selectedDate
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        selectedDate = df.format(calendar.getTime());

        // When a day is selected, store that value in selectedDate
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Make sure the format is "yyyy-MM-dd"
                selectedDate = FormatValue.dateYMD(year + "-" + month + "-" + dayOfMonth);
                // Generate the corresponding dailySchedule
                showDailySchedule();
            }
        });
    }

    public void showDailySchedule() {
        DailySchedule dailySchedule = new DailySchedule().generate(this, Account.getCurrentAccount().getID(), selectedDate);
        if (dailySchedule.getSize() > 0) {
            ArrayList<TimeNode> arrayDailySchedule = dailySchedule.toArrayList();
            rvAdapter = new ScheduleAdapter(this, arrayDailySchedule);
            recyclerView.setAdapter(rvAdapter);
        }
    }

    public void onClickManage(View view){
        Intent intent = new Intent(this, ScheduleManagement.class);
        startActivity(intent);
    }

}
