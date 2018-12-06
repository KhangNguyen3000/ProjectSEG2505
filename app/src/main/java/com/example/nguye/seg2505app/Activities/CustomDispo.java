package com.example.nguye.seg2505app.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.ScheduleClasses.DailySchedule;
import com.example.nguye.seg2505app.ScheduleClasses.ScheduleState;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.CustomSchedule;
import com.example.nguye.seg2505app.Storables.Storable;
import com.example.nguye.seg2505app.Utilities.DateTimePicker;
import com.example.nguye.seg2505app.Utilities.FormatValue;
import com.example.nguye.seg2505app.Utilities.Validation;

import java.util.ArrayList;
import java.util.List;


public class CustomDispo extends AppCompatActivity {
    EditText startTime;
    EditText endTime ;
    EditText date;
    EditText duration;
    ViewGroup layout;
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
        duration = findViewById(R.id.edit_durationPick);
        layout  = findViewById(R.id.custom_avail_layout);


        // create a list of items for the spinner.

        DisplayCustom(getCustomSchedules());
    }

    public ArrayList<String> getCustomSchedules (){

        //query is an sql style query to get elements only corresponding to the current account
        String query = "SELECT " + CustomSchedule.COL_DATE + ", " + CustomSchedule.COL_START
                + ", " + CustomSchedule.COL_END + ", " + CustomSchedule.COL_AVAILABILITY
                + " FROM " + CustomSchedule.TABLE_NAME
                + " WHERE " + CustomSchedule.COL_PROVIDER + " = " + Account.getCurrentAccount().getID();

        ArrayList<String[]> customSchedules = Storable.select(this, query, 4);

        ArrayList<String> displayCustomSchedule = new ArrayList<>();

        //Make arrayList of Strings, each String contains the date start time end time and availability
        for(String[] record: customSchedules){
            displayCustomSchedule.add(record[0]+"    " +FormatValue.minToTimeString(Integer.parseInt(record[1]))+"    "+FormatValue.minToTimeString(Integer.parseInt(record[2]))+"    "+record[3]);
        }
        return displayCustomSchedule;
    }


    public void DisplayCustom( List<String> users){
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users.toArray(new String[users.size()]));
        ListView listView = findViewById(R.id._ListViewCustomDispo);
        listView.setAdapter(itemsAdapter);
    }


    public void showTimePicker(View view) { DateTimePicker.showTimePicker(view); }

    public void showDatePicker(View view){ DateTimePicker.showDatePicker(view);}

    public void OnClickConfirm(View view){
        // TODO when adding a custom dispo, make sure the provider is not booked between the
        //      specified times using the DailySchedule.isBookedBetween() function
        if(availBox.isChecked()){
            state = ScheduleState.AVAILABLE;
        } else{state = ScheduleState.UNAVAILABLE;}

        if(Integer.parseInt(duration.getText().toString())>= 1){

            if(Validation.validateAll(layout)&&Validation.validateTimes(startTime,endTime)){
                int provider = Account.getCurrentAccount().getID();
                String setDate = date.getText().toString();
                int setStartTime = FormatValue.timeStringToMin(startTime.getText().toString());
                int setEndTime = FormatValue.timeStringToMin(endTime.getText().toString());

                //DailySchedule schedule = new DailySchedule().generate(this, provider, setDate);
                //if(schedule.isBookedBetween(setStartTime,setEndTime)){}
               // else {
                    addCustomDispo(provider, setDate, setStartTime, setEndTime);
               // }
                for(int i=1; i<Integer.parseInt(duration.getText().toString()); i++){
                    setDate = FormatValue.incrementDate(setDate);
                   // schedule = new DailySchedule().generate(this, provider, setDate);
                  //  if(schedule.isBookedBetween(setStartTime,setEndTime)){}
                  //  else {
                        addCustomDispo(provider, setDate, setStartTime, setEndTime);
                  //  }
                } } }

                DisplayCustom(getCustomSchedules());
    }

    public void addCustomDispo(int provider,String setDate,int setStartTime,int setEndTime){
        CustomSchedule addition = new CustomSchedule(provider,setDate,setStartTime,setEndTime,state);
        addition.add(this);
    }


}



