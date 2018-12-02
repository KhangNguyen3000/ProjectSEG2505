package com.example.nguye.seg2505app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.nguye.seg2505app.ScheduleClasses.DailySchedule;
import com.example.nguye.seg2505app.ScheduleClasses.TimeNode;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Booking extends AppCompatActivity implements ScheduleAdapter.ItemClickListener {
// From https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
//  and https://developer.android.com/guide/topics/ui/layout/recyclerview#java

    private int providerID;
    private String selectedDate;
    private CalendarView calendarView;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.daily_schedule);
        recyclerView.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

        // TODO receive the provider ID from the previous activity (Provider's page) with an EXTRA
//        providerID = ... (NOT Account.getCurrentAccount().getID, this would return the client's ID)

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

        // TODO show modal window when a timeSlot is selected.
    }

    public void showDailySchedule() {
        DailySchedule dailySchedule = new DailySchedule().generate(this, providerID, selectedDate);
        ArrayList<TimeNode> arrayDailySchedule = dailySchedule.toArrayList();
        rvAdapter = new ScheduleAdapter(this, arrayDailySchedule);
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        TextView tvTimeSlotStart = view.findViewById(R.id.timeSlotStart);
        TextView tvTimeSlotEnd = view.findViewById(R.id.timeSlotEnd);
        TextView tvAvail = view.findViewById(R.id.availability);
        // do something with those

        // TODO prompt a modal window for appointement
        // only allow to click on AVAILABLE slots
    }
}
