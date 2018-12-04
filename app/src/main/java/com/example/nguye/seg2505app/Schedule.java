package com.example.nguye.seg2505app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nguye.seg2505app.Activities.ScheduleManagement;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
    }

    public void onClickManage(View view){
        Intent k =new Intent(this, ScheduleManagement.class);
        startActivity(k);
    }

}
