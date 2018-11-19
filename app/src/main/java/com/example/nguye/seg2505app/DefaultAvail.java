package com.example.nguye.seg2505app;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

    public void onClickSaveButton(View view) {
        // Code to save availabilities to the database
        //....



        Toast toast = Toast.makeText(this, "Need to write the code to save to the database!!!", Toast.LENGTH_LONG);
        toast.show();
        finish();
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
