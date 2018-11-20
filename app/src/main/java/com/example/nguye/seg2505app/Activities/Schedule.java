package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nguye.seg2505app.Activities.DefaultAvail;
import com.example.nguye.seg2505app.R;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }


    public void onClickDefaultScheduleButton (View view) {
        Intent intent = new Intent(getApplicationContext(), DefaultAvail.class);
        startActivity(intent);
    }
}
