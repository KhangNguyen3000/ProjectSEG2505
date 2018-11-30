package com.example.nguye.seg2505app.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguye.seg2505app.Storables.DefaultSchedule;
import com.example.nguye.seg2505app.Storables.Storable;
import com.example.nguye.seg2505app.Utilities.FormatValue;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.DateTimePicker;
import com.example.nguye.seg2505app.Utilities.Validation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DefaultAvail extends AppCompatActivity {
    EditText effDate;
    EditText mondayStart;
    EditText mondayEnd;
    EditText tuesdayStart;
    EditText tuesdayEnd;
    EditText wednesdayStart;
    EditText wednesdayEnd;
    EditText thursdayStart;
    EditText thursdayEnd;
    EditText fridayStart;
    EditText fridayEnd;
    EditText saturdayStart;
    EditText saturdayEnd;
    EditText sundayStart;
    EditText sundayEnd;

    int foundDefaultScheduleID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_avail);

        effDate        = findViewById(R.id.dav_input_effDate);
        mondayStart    = findViewById(R.id.dav_input_mondayStart);
        mondayEnd      = findViewById(R.id.dav_input_mondayEnd);
        tuesdayStart   = findViewById(R.id.dav_input_tuesdayStart);
        tuesdayEnd     = findViewById(R.id.dav_input_tuesdayEnd);
        wednesdayStart = findViewById(R.id.dav_input_wednesdayStart);
        wednesdayEnd   = findViewById(R.id.dav_input_wednesdayEnd);
        thursdayStart  = findViewById(R.id.dav_input_thursdayStart);
        thursdayEnd    = findViewById(R.id.dav_input_thursdayEnd);
        fridayStart    = findViewById(R.id.dav_input_fridayStart);
        fridayEnd      = findViewById(R.id.dav_input_fridayEnd);
        saturdayStart  = findViewById(R.id.dav_input_saturdayStart);
        saturdayEnd    = findViewById(R.id.dav_input_saturdayEnd);
        sundayStart    = findViewById(R.id.dav_input_sundayStart);
        sundayEnd      = findViewById(R.id.dav_input_sundayEnd);

        // TODO remove the reference to this line in Schedule
//        Account account = (Account) getIntent().getSerializableExtra("schedule");


        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(today);
        ((EditText) findViewById(R.id.dav_input_effDate)).setText(dateString);
        // Get the record with the highest date that is smaller or equal to the selected date,
        //  and fill the fields with those values.
        foundDefaultScheduleID = restoreFields(dateString);
    }

    // TODO open it at the time that is already selected
    public void showTimePicker(View view) {
        DateTimePicker.showTimePicker(view);
    }

    public void showDatePicker(View view) {
        // Show a date picker and put the selected in the field.
        // From https://stackoverflow.com/questions/17901946/timepicker-dialog-from-clicking-edittext
        // and https://developer.android.com/reference/java/util/Calendar
        // and https://developer.android.com/reference/android/app/DatePickerDialog

        final View v = view;

        Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1; // Months go from 0 to 11
                String fullDate = year + "-" + month + "-" + day;
                fullDate = FormatValue.dateYMD(fullDate);
                ((EditText) v).setText(fullDate);
                foundDefaultScheduleID = restoreFields(fullDate);
            }
        }, cYear, cMonth, cDay);
        datePicker.show();
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

    // TODO set the flag isLoaded to false IF there is a change
    public void onClickSaveButton(View view) {
        // TODO validate the date and time
        //  For the date, make sure the user didn't select a date in the past
        //  For times, make sure that endTime >= startTime
        ViewGroup layout = findViewById(R.id.dav_layout_root);
        if (Validation.validateAll(layout)) {
            // Create a DefaultSchedule object with the values entered in the fields
            DefaultSchedule schedule = new DefaultSchedule(
                    Account.getCurrentAccount().getID(), effDate.getText().toString(),
                    FormatValue.timeStringToMin(mondayStart.getText().toString()), FormatValue.timeStringToMin(mondayEnd.getText().toString()),
                    FormatValue.timeStringToMin(tuesdayStart.getText().toString()), FormatValue.timeStringToMin(tuesdayEnd.getText().toString()),
                    FormatValue.timeStringToMin(wednesdayStart.getText().toString()), FormatValue.timeStringToMin(wednesdayEnd.getText().toString()),
                    FormatValue.timeStringToMin(thursdayStart.getText().toString()), FormatValue.timeStringToMin(thursdayEnd.getText().toString()),
                    FormatValue.timeStringToMin(fridayStart.getText().toString()), FormatValue.timeStringToMin(fridayEnd.getText().toString()),
                    FormatValue.timeStringToMin(saturdayStart.getText().toString()), FormatValue.timeStringToMin(saturdayEnd.getText().toString()),
                    FormatValue.timeStringToMin(sundayStart.getText().toString()), FormatValue.timeStringToMin(sundayEnd.getText().toString())
            );

            // Overwrite the record if the user already has a DefaultSchedule starting on the specified date
            // A provider can set multiple default schedules, but with different effective dates
            if (foundDefaultScheduleID == -1) { // If no DefaultSchedule was set...
                schedule.add(this); // ... add the DefaultSchedule to the database
                System.out.println("Default schedule added.");
            } else { // If a DefaultSchedule was set...
                // ... check if the selected date corresponds to the date saved in the record
                if (Storable.findFieldByID(this, DefaultSchedule.TABLE_NAME,
                        DefaultSchedule.COL_EFFECTIVEDATE, foundDefaultScheduleID)
                        .equals(effDate.getText().toString())) { // If so, update it.
                    schedule.setID(foundDefaultScheduleID);
                    schedule.update(this); // ...update the record
                    System.out.println("Default schedule updated.");
                } else { // If not, create a new record
                    schedule.add(this); // ...add the DefaultSchedule to the database
                    System.out.println("Default schedule added.");
                }
            }

            // Display a message
            Toast toast = Toast.makeText(this, "Your default schedule has been saved for the specified day.", Toast.LENGTH_LONG);
            toast.show();
            finish();


//            // Overwrite the record if the user already has a DefaultSchedule for the specified date
//            // A provider can set multiple default schedules, but with different effective dates
//            String query = "SELECT " + DefaultSchedule.COL_ID + " FROM " + DefaultSchedule.TABLE_NAME
//                    + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE + " = \""
//                    + ((EditText) findViewById(R.id.dav_input_effDate)).getText().toString() + "\""
//                    + " AND " + DefaultSchedule.COL_PROVIDER + " = " + Account.getCurrentAccount().getID();
//            ArrayList<String[]> records = Storable.select(this, query, 1);
//            if (records.size() < 1) { // If no record has been found...
//                schedule.add(this); // ...add the DefaultSchedule to the database
//                System.out.println("Default schedule added.");
//            } else { // If there was already a DefaultSchedule specified for that day by that provider...
//                schedule.setID(Integer.parseInt(records.get(0)[0]));
//                schedule.update(this); // ...update the record
//                System.out.println("Default schedule updated.");
//            }
//            // Display a message
//            Toast toast = Toast.makeText(this, "Your default schedule has been saved for the specified day.", Toast.LENGTH_LONG);
//            toast.show();
//            finish();
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

    /**
     * Get the record in DefaultSchedules with the highest date that is smaller than or equal to
     *  the selected date (and that belongs to the current provider), and fill the fields with those values.
     * @param dateString The selected date
     * @return The ID of the retrieved DefaultSchedule to easily overwrite it later
     */
    public int restoreFields(String dateString) {
        // Get the highest date that is smaller than or equal to the selected date (and that
        //  belongs to the current provider)
//        String maxDateQuery = "SELECT MAX(" + DefaultSchedule.COL_EFFECTIVEDATE
//                + ") FROM " + DefaultSchedule.TABLE_NAME
//                + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE + " <= \"" + dateString + "\"";
//        System.out.println(maxDateQuery);
//        String maxDate = Storable.select(this, maxDateQuery, 1).get(0)[0];
//
//        // Get that record
//        String effDateQuery = "SELECT * FROM " + DefaultSchedule.TABLE_NAME
//                + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE + " = \"" + maxDate + "\"";
//        System.out.println(effDateQuery);

        // Get the record with highest date that is smaller than or equal to the selected date
        //  (and that belongs to the current provider)
        String effDateQuery = "SELECT * FROM " + DefaultSchedule.TABLE_NAME
                + " WHERE " + DefaultSchedule.COL_PROVIDER + " = " + Account.getCurrentAccount().getID()
                + " AND" + DefaultSchedule.COL_EFFECTIVEDATE
                + " = (SELECT MAX(" + DefaultSchedule.COL_EFFECTIVEDATE
                + ") FROM " + DefaultSchedule.TABLE_NAME
                + " WHERE " + DefaultSchedule.COL_EFFECTIVEDATE + " <= \"" + dateString + "\")";
        System.out.println(effDateQuery);

        // The ArrayList should contain only 1 record
        ArrayList<DefaultSchedule> defSchedules = DefaultSchedule.findByQuery(this, effDateQuery);
        ArrayList<String[]> defSchedule = Storable.select(this, effDateQuery, DefaultSchedule.COLUMNS.size());
        if (defSchedules.size() > 0) {
//            effDate.setText(defSchedules.get(0).getEffectiveDate());
            // TODO check all the boxes that have 0:00 and uncheck the boxes that don't
            sundayStart.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[3])));
            sundayEnd.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[4])));
            mondayStart.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[5])));
            mondayEnd.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[6])));
            tuesdayStart.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[7])));
            tuesdayEnd.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[8])));
            wednesdayStart.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[9])));
            wednesdayEnd.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[10])));
            thursdayStart.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[11])));
            thursdayEnd.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[12])));
            fridayStart.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[13])));
            fridayEnd.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[14])));
            saturdayStart.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[15])));
            saturdayEnd.setText(FormatValue.minToTimeString(Integer.parseInt(defSchedule.get(0)[16])));


//            mondayStart.setText(defSchedules.get(0).getStartTimes(0));
//            mondayEnd.setText(defSchedules.get(0).getEndTimes(0));
//            tuesdayStart.setText(defSchedules.get(0).getStartTimes(1));
//            tuesdayEnd.setText(defSchedules.get(0).getEndTimes(1));
//            wednesdayStart.setText(defSchedules.get(0).getStartTimes(2));
//            wednesdayEnd.setText(defSchedules.get(0).getEndTimes(2));
//            thursdayStart.setText(defSchedules.get(0).getStartTimes(3));
//            thursdayEnd.setText(defSchedules.get(0).getEndTimes(3));
//            fridayStart.setText(defSchedules.get(0).getStartTimes(4));
//            fridayEnd.setText(defSchedules.get(0).getEndTimes(4));
//            saturdayStart.setText(defSchedules.get(0).getStartTimes(5));
//            saturdayEnd.setText(defSchedules.get(0).getEndTimes(5));
//            sundayStart.setText(defSchedules.get(0).getStartTimes(6));
//            sundayEnd.setText(defSchedules.get(0).getEndTimes(6));
            return Integer.parseInt(defSchedule.get(0)[0]); // return the ID
        } else { // If the user does not have a default schedule for that day
            // TODO uncheck all the boxes
            mondayStart.setText("");
            mondayEnd.setText("");
            tuesdayStart.setText("");
            tuesdayEnd.setText("");
            wednesdayStart.setText("");
            wednesdayEnd.setText("");
            thursdayStart.setText("");
            thursdayEnd.setText("");
            fridayStart.setText("");
            fridayEnd.setText("");
            saturdayStart.setText("");
            saturdayEnd.setText("");
            sundayStart.setText("");
            sundayEnd.setText("");
            return -1;
        }
//        ArrayList<String[]> defSchedules = Storable.select(this, effDateQuery, 17);
//
//        // The long code between the brackets [] is used to get a dynamic reference to the field index,
//        //  rather than a constant that would have to be changed if the fields move around.
//        effDate.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_EFFECTIVEDATE, DefaultSchedule.COLUMNS)]);
//        mondayStart.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_MONDAYSTART, DefaultSchedule.COLUMNS)]);
//        mondayEnd.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_MONDAYEND, DefaultSchedule.COLUMNS)]);
//        tuesdayStart.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_TUESDAYSTART, DefaultSchedule.COLUMNS)]);
//        tuesdayEnd.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_TUESDAYEND, DefaultSchedule.COLUMNS)]);
//        WednesdayStart.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_WEDNESDAYSTART, DefaultSchedule.COLUMNS)]);
//        WednesdayEnd.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_WEDNESDAYEND, DefaultSchedule.COLUMNS)]);
//        ThursdayStart.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_THURSDAYSTART, DefaultSchedule.COLUMNS)]);
//        ThursdayEnd.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_THURSDAYEND, DefaultSchedule.COLUMNS)]);
//        FridayStart.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_FRIDAYSTART, DefaultSchedule.COLUMNS)]);
//        FridayEnd.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_FRIDAYEND, DefaultSchedule.COLUMNS)]);
//        SaturdayStart.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_SATURDAYSTART, DefaultSchedule.COLUMNS)]);
//        SaturdayEnd.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_SATURDAYEND, DefaultSchedule.COLUMNS)]);
//        SundayStart.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_SUNDAYSTART, DefaultSchedule.COLUMNS)]);
//        SundayEnd.setText(defSchedules.get(0)[Storable.getFieldIndex(DefaultSchedule.COL_SUNDAYEND, DefaultSchedule.COLUMNS)]);

    }
}
