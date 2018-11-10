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
    MyDBHandler data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_management);
        this.data = new MyDBHandler(this);

        List<String> services = data.getList("Email","Accounts");
        showServiceList(services);

    }



// This updates the List element on the activity_service_management screen
    public void showServiceList(List<String> services){
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, services.toArray(new String[services.size()]));
        ListView listView = (ListView) findViewById(R.id._ListViewServices);
        listView.setAdapter(itemsAdapter);
    }


    public void onClickAddService(View view){
       String  serviceName = ((EditText) findViewById(R.id.serviceName)).getText().toString();
       double serviceRate = Double.parseDouble(((EditText) findViewById(R.id.servicePrice)).getText().toString());
       data.addServiceType(serviceName,serviceRate);

//     public void onClickList(View view) {
//         AlertDialog.Builder builder = new AlertDialog.Builder(this);
//         View mView = getLayoutInflater().inflate(R.layout.dialog_add_service, null);
//         EditText serviceName = (EditText) mView.findViewById(R.id.nameUpdate);
//         EditText price = (EditText) mView.findViewById(R.id.priceUpdate);
//         builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
//             @Override
//             public void onClick(DialogInterface dialog, int which) {
//                 //update on database
//             }
//         });
//         builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//             @Override
//             public void onClick(DialogInterface dialog, int which) {
//                 // do nothing
//             }
//         });

//         AlertDialog dialog = builder.create();
//         dialog.show();

//     }

}
