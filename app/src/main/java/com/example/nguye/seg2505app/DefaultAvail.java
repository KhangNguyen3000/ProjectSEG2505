package com.example.nguye.seg2505app;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

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

    CheckBox cbMondeay, cbTuesday, cbWednesday, cbThursday,
    cbFriday, cbSaturday, cbSunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_avail);

        cbMondeay = (CheckBox) findViewById(R.id.checkBox_monday);
        cbTuesday = (CheckBox) findViewById(R.id.checkBox_tuesday);
        cbWednesday = (CheckBox) findViewById(R.id.checkBox_wednesday);
        cbThursday = (CheckBox) findViewById(R.id.checkBox_thursday);
        cbFriday = (CheckBox) findViewById(R.id.checkBox_friday);
        cbSaturday = (CheckBox) findViewById(R.id.checkBox_saturday);
        cbSunday = (CheckBox) findViewById(R.id.checkBox_sunday);

        // *** MAYBE THERE IS AN ATTRIBUTE THAT ALLOWS TO INITIALLY CHECK THE CHECKBOXES
        // EVEN IF NOT, MAYBE THIS CODE IS WRONG
        // IF I DON'T NEED THOSE VARIABLES ELSEWHERE, REMOVE THE DECLARATION AND DO EVERYTHING IN ONE
        //  BLOCK INSTEAD OF 3
        cbMondeay.setChecked(true);
        cbTuesday.setChecked(true);
        cbWednesday.setChecked(true);
        cbThursday.setChecked(true);
        cbFriday.setChecked(true);
        cbSaturday.setChecked(true);
        cbSunday.setChecked(true);
    }

    public void showTimePicker(View view) {
        DateTimePicker.showTimePicker(view);
    }

    public void showDatePicker(View view) {
        DateTimePicker.showDatePicker(view);
    }

    public void onClickCheckBox(View view) {
        if (((CheckBox) view).isChecked()) {


        } else {
            ViewGroup layout = (ViewGroup) view.getParent();
            layout.setBackgroundColor(0); // set the layout's color to grey
            layout.setClickable(false); // make the horizontal layout unclickable


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
                    FormatInput.dateToLong(((EditText) findViewById(R.id.dav_input_effDate)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_mondayStart)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_mondayEnd)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_tuesdayStart)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_tuesdayEnd)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_wednesdayStart)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_wednesdayEnd)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_thursdayStart)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_thursdayEnd)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_fridayStart)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_fridayEnd)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_saturdayStart)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_saturdayEnd)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_sundayStart)).getText().toString()),
                    FormatInput.timeToLong(((EditText) findViewById(R.id.dav_input_sundayEnd)).getText().toString())
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
