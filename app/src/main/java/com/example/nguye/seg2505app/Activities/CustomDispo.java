package com.example.nguye.seg2505app.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.ScheduleClasses.ScheduleState;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.CustomSchedule;
import com.example.nguye.seg2505app.Storables.Storable;
import com.example.nguye.seg2505app.Utilities.DateTimePicker;
import com.example.nguye.seg2505app.Utilities.FormatValue;

import java.util.ArrayList;
import java.util.List;


public class CustomDispo extends AppCompatActivity {
    EditText startTime;
    EditText endTime ;
    EditText date;
    CheckBox availBox;
    ScheduleState state;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_availability);

        startTime= findViewById(R.id.edit_timePick1);
        endTime = findViewById(R.id.edit_timePick2);
        date= findViewById(R.id.edit_datePick);
        availBox = findViewById(R.id.checkBox_available);

        Spinner dropdown = findViewById(R.id.duration_drop);
//create a list of items for the spinner.
        String[] days = new String[]{"1", "2", "2","3","4","5","6","7"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, days);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);


        //query is an sql style query to get elements only corresponding to the current account
        String query = "SELECT " + CustomSchedule.COL_DATE + ", " + CustomSchedule.COL_START
                + ", " + CustomSchedule.COL_END + ", " + CustomSchedule.COL_AVAILABILITY
                + " FROM " + CustomSchedule.TABLE_NAME
                + " WHERE " + CustomSchedule.COL_PROVIDER + " = " + Account.getCurrentAccount().getID();
        System.out.println(query);
        ArrayList<String[]> customSchedules = Storable.select(this, query, 4);


        //Make arrayList of Strings, each String contains the date start time end time and availability
        ArrayList<String> displayCustomSchedule = new ArrayList<>();
        for(String[] record: customSchedules){
            displayCustomSchedule.add(record[0]+FormatValue.minToTimeString(Integer.parseInt(record[1]))+FormatValue.minToTimeString(Integer.parseInt(record[2]))+record[3]);
        }

        DisplayCustom(displayCustomSchedule);
    }

    public void DisplayCustom( List<String> users){
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users.toArray(new String[users.size()]));
        ListView listView = findViewById(R.id._ListViewCustomDispo);
        listView.setAdapter(itemsAdapter);
    }


    public void showTimePicker(View view) { DateTimePicker.showTimePicker(view); }

    public void showDatePicker(View view){ DateTimePicker.showDatePicker(view);}

    public void OnClickConfirm(View view){
        if(availBox.isChecked()){
            state = ScheduleState.AVAILABLE;
        } else{state = ScheduleState.UNAVAILABLE;}

        CustomSchedule addition = new CustomSchedule(Account.getCurrentAccount().getID(),date.getText().toString(),FormatValue.timeStringToMin(startTime.getText().toString()),
                FormatValue.timeStringToMin(endTime.getText().toString()),state);
    }


}



