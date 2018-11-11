package com.example.nguye.seg2505app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ServiceManagement extends AppCompatActivity {
//    MyDBHandler data;
    ListView listView;
    private ServiceType updatedServiceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_management);
        MyDBHandler data = new MyDBHandler(this);

        List<String> services = data.getList("Name","ServiceTypes");
        showServiceList(services);

        ListView serviceList = (ListView) findViewById(R.id._ListViewServices);
//        serviceList.setClickable(true);
        serviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyDBHandler data = new MyDBHandler(getApplicationContext());
                String selectedService = (String) parent.getItemAtPosition(position);
                ServiceType serviceType = data.findServiceType(selectedService);

                // Show the modify options
                Button addButton = findViewById(R.id.addB);
                Button updateButton = findViewById(R.id.updateB);
                Button deleteButton = findViewById(R.id.deleteB);
                Button cancelButton = findViewById(R.id.cancelB);
                addButton.setVisibility(View.GONE);
                updateButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(view.VISIBLE);

                // Fills the fields with the current information of the selected service
                EditText serviceName = findViewById(R.id.serviceName);
                EditText serviceMaxRate = findViewById(R.id.serviceMaxRate);
                serviceName.setText(serviceType.getName());
                serviceMaxRate.setText(Double.toString(serviceType.getRate()));


                // Stores the information of the selected service type
                updatedServiceType = data.findServiceType(serviceType.getName());
                System.out.println(updatedServiceType.getID());
                System.out.println(updatedServiceType.getName());
            }
        });
    }



// This updates the List element on the activity_service_management screen
    public void showServiceList(List<String> services){
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, services.toArray(new String[services.size()]));
        this.listView = (ListView) findViewById(R.id._ListViewServices);
        listView.setAdapter(itemsAdapter);
    }


    public void onClickAddService(View view) {
        ViewGroup layout = findViewById(R.id.sma_layout_root);
        if (Validation.validateAll(layout)) {
            // Add the service to the database
            String serviceName = ((EditText) findViewById(R.id.serviceName)).getText().toString().trim();
            double serviceRate = Double.parseDouble(((EditText) findViewById(R.id.serviceMaxRate)).getText().toString());
            MyDBHandler data = new MyDBHandler(getApplicationContext());
            ServiceType serviceType = new ServiceType(serviceName, serviceRate);
            data.addServiceType(serviceType);

            // Display a success message
            Toast toast = Toast.makeText(getApplicationContext(), "Service added!", Toast.LENGTH_LONG);
            toast.show();

            // Update the list view
            List<String> services = data.getList("Name", "ServiceTypes");
            showServiceList(services);
        }
    }
//
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

    public void onClickUpdate(View view) {
        ViewGroup layout = findViewById(R.id.sma_layout_root);
        if (Validation.validateAll(layout)) {
            // Get the inputs and update the object updatedServiceType
            String newServiceName = ((EditText) findViewById(R.id.serviceName)).getText().toString().trim();
            double newServiceRate = Double.parseDouble(((EditText) findViewById(R.id.serviceMaxRate)).getText().toString());
            MyDBHandler data = new MyDBHandler(getApplicationContext());
            updatedServiceType.setName(newServiceName);
            updatedServiceType.setRate(newServiceRate);

            data.updateServiceType(updatedServiceType);

            // Hide the modify options and clear the fields
            Button addButton = findViewById(R.id.addB);
            Button updateButton = findViewById(R.id.updateB);
            Button deleteButton = findViewById(R.id.deleteB);
            Button cancelButton = findViewById(R.id.cancelB);
            EditText serviceName = findViewById(R.id.serviceName);
            EditText serviceMaxRate = findViewById(R.id.serviceMaxRate);

            addButton.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
            cancelButton.setVisibility(View.GONE);
            serviceName.setText("");
            serviceMaxRate.setText("");

            //Display the success message
            Toast toast = Toast.makeText(getApplicationContext(), "Service updtated!", Toast.LENGTH_LONG);
            toast.show();

            // Update the list view
            List<String> services = data.getList("Name","ServiceTypes");
            showServiceList(services);
        }
    }

    public void onClickDelete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete service");
        builder.setMessage("Are you sure that you want to delete this service?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDBHandler data = new MyDBHandler(getApplicationContext());
                data.deleteServiceType(updatedServiceType);

                //Display the success message
                Toast toast = Toast.makeText(getApplicationContext(), "Service deleted!", Toast.LENGTH_LONG);
                toast.show();

                // Hide the modify options and clear the fields
                Button addButton = findViewById(R.id.addB);
                Button updateButton = findViewById(R.id.updateB);
                Button deleteButton = findViewById(R.id.deleteB);
                Button cancelButton = findViewById(R.id.cancelB);
                EditText serviceName = findViewById(R.id.serviceName);
                EditText serviceMaxRate = findViewById(R.id.serviceMaxRate);

                addButton.setVisibility(View.VISIBLE);
                updateButton.setVisibility(View.GONE);
                deleteButton.setVisibility(View.GONE);
                cancelButton.setVisibility(View.GONE);
                serviceName.setText("");
                serviceMaxRate.setText("");

                // Update the list view
                List<String> services = data.getList("Name","ServiceTypes");
                showServiceList(services);
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

    public void onClickCancelButton(View view) {
        Button addButton = findViewById(R.id.addB);
        Button updateButton = findViewById(R.id.updateB);
        Button deleteButton = findViewById(R.id.deleteB);
        Button cancelButton = findViewById(R.id.cancelB);

        //Hide the 3 buttons and show the add one
        addButton.setVisibility(View.VISIBLE);
        updateButton.setVisibility(View.GONE);
        deleteButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);

        //Fill the fields empty
        EditText serviceName = findViewById(R.id.serviceName);
        EditText serviceMaxRate = findViewById(R.id.serviceMaxRate);
        serviceName.setText("");
        serviceMaxRate.setText("");

    }
}
