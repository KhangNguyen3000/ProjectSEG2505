package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.nguye.seg2505app.Activities.DefaultAvail;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.DefaultSchedule;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Schedule extends AppCompatActivity {

    private int mTextColor, mBorderColor;
    private int mTextViewBorderWidth, mTableBorderWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTextColor = 0xff111111;
        mBorderColor = 0xAA444444;
        mTextViewBorderWidth = 1;
        mTableBorderWidth = mTextViewBorderWidth * 2;
        TableLayout layout = (TableLayout) findViewById(R.id.layout);
        setupTable(layout);
        setContentView(R.layout.activity_schedule);
    }


    public void onClickDefaultScheduleButton (View view) {
        Intent intent = new Intent(getApplicationContext(), DefaultAvail.class);
        startActivity(intent);
    }


    private void setupTable(TableLayout layout){
        GregorianCalendar gc = new GregorianCalendar();
        TableRow tableRow;
        TextView textView;
        for(int rowNumber = 0; rowNumber < 14; rowNumber++){
            tableRow = new TableRow(getBaseContext());

            textView = new TextView(getBaseContext());
            textView.setTextColor(mTextColor);
            textView.setText(""+gc.getTime());
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(0, 6, 0, 6);
            tableRow.addView(textView);

            int day = gc.get(Calendar.DAY_OF_WEEK);
            DefaultSchedule schedule = new DefaultSchedule().find(this, "ID", Account.getCurrentAccount().getID(), false);
            String startTime = "0";
            String endTime = "0";
            switch (day) {
                case Calendar.SUNDAY:
                    startTime = schedule.getStartTimes(6);
                    endTime = schedule.getEndTimes(6);
                    break;
                case Calendar.MONDAY:
                    startTime = schedule.getStartTimes(0);
                    endTime = schedule.getEndTimes(0);
                    break;
                case Calendar.TUESDAY:
                    startTime = schedule.getStartTimes(1);
                    endTime = schedule.getEndTimes(1);
                    break;
                case Calendar.WEDNESDAY:
                    startTime = schedule.getStartTimes(2);
                    endTime = schedule.getEndTimes(2);
                    break;
                case Calendar.THURSDAY:
                    startTime = schedule.getStartTimes(3);
                    endTime = schedule.getEndTimes(3);
                    break;
                case Calendar.FRIDAY:
                    startTime = schedule.getStartTimes(4);
                    endTime = schedule.getEndTimes(4);
                    break;
                case Calendar.SATURDAY:
                    startTime = schedule.getStartTimes(5);
                    endTime = schedule.getEndTimes(5);
                    break;
            }
            textView.setText(startTime+" - "+endTime);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(0, 6, 0, 6);
            tableRow.addView(textView);
            /*
            if(tableRow.getParent() != null){
                ((ViewGroup)tableRow.getParent()).removeView(tableRow);
            }
            */
            layout.removeAllViews();
            layout.addView(tableRow);
            gc.add(Calendar.DATE, 1);

        }

    }
}
