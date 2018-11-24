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
import com.example.nguye.seg2505app.Utilities.FormatValue;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.DateTimePicker;
import com.example.nguye.seg2505app.Utilities.Validation;

import java.util.Date;

public class DefaultAvail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_avail);

//        Account account = (Account) getIntent().getSerializableExtra("schedule");
//        Date today = new Date();
//        ((EditText) findViewById(R.id.dav_input_effDate)).setText(today.toString());
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
        }
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
            if (!schedule.update(this)) {
                schedule.add(this); // Add the DefaultSchedule to the database
            }
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
