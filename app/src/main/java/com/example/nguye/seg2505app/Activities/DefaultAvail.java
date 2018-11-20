package com.example.nguye.seg2505app.Activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguye.seg2505app.Storables.DefaultSchedule;
import com.example.nguye.seg2505app.Utilities.FormatInput;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.DateTimePicker;
import com.example.nguye.seg2505app.Utilities.Validation;

public class DefaultAvail extends AppCompatActivity {

    // All checkboxes should be checked initially

    // Unchecked checkboxes:
    //  Make the fields unclickable
    //  Change the horizontal layout's color to grey
    //  Set both startTime and endTimes to 00:00
    //      (this prevent validation errors and provides values to store in the database)

    // Checked checkboxes
    //  Make the fields clickable
    //  Change the horizontal layout's color back to default
    //  CLEAR the startTime and endTime fields

//    CheckBox cbMondeay, cbTuesday, cbWednesday, cbThursday,
//    cbFriday, cbSaturday, cbSunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_avail);

//        cbMondeay = (CheckBox) findViewById(R.id.checkBox_monday);
//        cbTuesday = (CheckBox) findViewById(R.id.checkBox_tuesday);
//        cbWednesday = (CheckBox) findViewById(R.id.checkBox_wednesday);
//        cbThursday = (CheckBox) findViewById(R.id.checkBox_thursday);
//        cbFriday = (CheckBox) findViewById(R.id.checkBox_friday);
//        cbSaturday = (CheckBox) findViewById(R.id.checkBox_saturday);
//        cbSunday = (CheckBox) findViewById(R.id.checkBox_sunday);
//
//        // *** MAYBE THERE IS AN ATTRIBUTE THAT ALLOWS TO INITIALLY CHECK THE CHECKBOXES
//        // EVEN IF NOT, MAYBE THIS CODE IS WRONG
//        // IF I DON'T NEED THOSE VARIABLES ELSEWHERE, REMOVE THE DECLARATION AND DO EVERYTHING IN ONE
//        //  BLOCK INSTEAD OF 3
//        cbMondeay.setChecked(true);
//        cbTuesday.setChecked(true);
//        cbWednesday.setChecked(true);
//        cbThursday.setChecked(true);
//        cbFriday.setChecked(true);
//        cbSaturday.setChecked(true);
//        cbSunday.setChecked(true);
    }

    public void showTimePicker(View view) {
        DateTimePicker.showTimePicker(view);
    }

    public void showDatePicker(View view) {
        DateTimePicker.showDatePicker(view);
    }

    public void onClickCheckBox(View view) {
        ViewGroup layout = (ViewGroup) view.getParent();
        if (((CheckBox) view).isChecked()) {
            // Set the layout's background color to default to show that it has been enabled
//            layout.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));
            layout.setBackgroundColor(Color.rgb(250,250,250));
            // Enable the fields and clear their time
            EditText startTime = (EditText) layout.getChildAt(2);
            EditText endTime = (EditText) layout.getChildAt(3);
            startTime.setEnabled(true);
            startTime.setText("");
            endTime.setEnabled(true);
            endTime.setText("");
        } else {
            // Set the layout's background color to grey to show that it has been disabled
            layout.setBackgroundColor(Color.rgb(200,200,200));

            // Disable the fields and set their time to 00:00
            EditText startTime = (EditText) layout.getChildAt(2);
            EditText endTime = (EditText) layout.getChildAt(3);
            startTime.setEnabled(false);
            startTime.setText("00:00");
            endTime.setEnabled(false);
            endTime.setText("00:00");

            // Make the horizontal layout unclickable
            //  maybe I'll have to make the children unclickable individually
            // Set both startTime and endTimes to 00:00
            // Set the color to default
        }

        // DO NOT FORGET TO BIND THE EVENT TO THE CHECKBOXES

        // Unchecked checkboxes:
        //  Make the fields unclickable
        //  Change the horizontal layout's color to grey

        //      (this prevent validation errors and provides values to store in the database)

        // Checked checkboxes
        //  Make the fields clickable
        //  Change the horizontal layout's color back to default
        //  CLEAR the startTime and endTime fields
    }

    public void onClickSaveButton(View view) {
        ViewGroup layout = findViewById(R.id.dav_layout_root);
        if (Validation.validateAll(layout)) {
            // Create a DefaultSchedule object with the values entered in the fields
            DefaultSchedule schedule = new DefaultSchedule(
                    Account.getCurrentAccount().getID(),
                    ((EditText) findViewById(R.id.dav_input_effDate)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_mondayStart)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_mondayEnd)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_tuesdayStart)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_tuesdayEnd)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_wednesdayStart)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_wednesdayEnd)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_thursdayStart)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_thursdayEnd)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_fridayStart)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_fridayEnd)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_saturdayStart)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_saturdayEnd)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_sundayStart)).getText().toString(),
                    ((EditText) findViewById(R.id.dav_input_sundayEnd)).getText().toString()
            );
            schedule.add(this); // Add the DefaultSchedule to the database
            // Display a message
            Toast toast = Toast.makeText(this, "Your default schedule has been saved.", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
    }

    public void onClickCancelButton(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Cancel");
        builder.setMessage("Are you sure that you want to cancel? Your changes will not be saved.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
