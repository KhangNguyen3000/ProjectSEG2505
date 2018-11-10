package com.example.nguye.seg2505app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ServiceManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_management);
        ListView lv;
        lv = (ListView) findViewById(R.id.sma_list_serviceList);
        List<String> sma_list_serviceList = new ArrayList<String>();

        //rempli arraylist avec tous les elements du database

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_service_management,sma_list_serviceList);
    lv.setAdapter(arrayAdapter);
    }
    public void onClickList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_add_service, null);
        EditText serviceName = (EditText) mView.findViewById(R.id.nameUpdate);
        EditText price = (EditText) mView.findViewById(R.id.priceUpdate);
        builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //update on database
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
