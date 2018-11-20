package com.example.nguye.seg2505app.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.nguye.seg2505app.R;

public class CustomDispo extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_availability);

        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] days = new String[]{"1", "2", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }


}



