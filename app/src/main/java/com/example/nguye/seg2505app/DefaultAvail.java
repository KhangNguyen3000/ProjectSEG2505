package com.example.nguye.seg2505app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DefaultAvail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_avail);
    }

    public void showTimePicker(View view) {
        DateTimePicker.showTimePicker(view);
    }

    public void showDatePicker(View view) {
        DateTimePicker.showDatePicker(view);
    }
}
