package com.example.nguye.seg2505app.Activities;

import android.content.DialogInterface;
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

import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.ServiceType;
import com.example.nguye.seg2505app.Utilities.Validation;

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
        serviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedService = (String) parent.getItemAtPosition(position);
                ServiceType serviceType = new ServiceType().find(getApplicationContext(), ServiceType.COL_NAME, selectedService,true);

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
                serviceMaxRate.setText(Double.toString(serviceType.getMaxRate()));

                // Stores the information of the selected service type
                updatedServiceType = new ServiceType().find(getApplicationContext(), ServiceType.COL_NAME, serviceType.getName(),true);
                System.out.println(updatedServiceType.getID());
                System.out.println(updatedServiceType.getName());
            }
        });
    }


    /**
     * This updates the List element on the activity_service_management screen
     * @param services
     */
    public void showServiceList(List<String> services){
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, services.toArray(new String[services.size()]));
        this.listView = (ListView) findViewById(R.id._ListViewServices);
        listView.setAdapter(itemsAdapter);
    }

    /**
     * Called when clicking on the Add button
     * Add the service to the table ServiceTypes
     * @param view
     */
    public void onClickAddService(View view) {
        ViewGroup layout = findViewById(R.id.sma_layout_root);
        EditText serviceName = findViewById(R.id.serviceName);
        if (Validation.validateAll(layout)
                && Validation.availableServiceType(serviceName, "")) {
            // Add the service to the database
            String newServiceName = ((EditText) findViewById(R.id.serviceName)).getText().toString().trim();
            double newServiceRate = Double.parseDouble(((EditText) findViewById(R.id.serviceMaxRate)).getText().toString());

            ServiceType serviceType = new ServiceType(newServiceName, newServiceRate);
//            data.addServiceType(serviceType);
            serviceType.add(this);

            // Display a success message
            Toast toast = Toast.makeText(getApplicationContext(), "Service added!", Toast.LENGTH_LONG);
            toast.show();

            // Update the list view
            MyDBHandler data = new MyDBHandler(getApplicationContext());
            List<String> services = data.getList("Name", "ServiceTypes");
            showServiceList(services);
        }
    }

    /**
     * Called when clicking on the update button
     * Update the service in the table ServiceTypes
     * @param view
     */
    public void onClickUpdate(View view) {
        ViewGroup layout = findViewById(R.id.sma_layout_root);
        EditText serviceName = findViewById(R.id.serviceName);
        if (Validation.validateAll(layout)
                && Validation.availableServiceType(serviceName, updatedServiceType.getName())) {
            // Get the inputs and update the object updatedServiceType
            String newServiceName = ((EditText) findViewById(R.id.serviceName)).getText().toString().trim();
            double newServiceRate = Double.parseDouble(((EditText) findViewById(R.id.serviceMaxRate)).getText().toString());

            updatedServiceType.setName(newServiceName);
            updatedServiceType.setMaxRate(newServiceRate);
            updatedServiceType.update(this);
            // Hide the modify options and clear the fields
            Button addButton = findViewById(R.id.addB);
            Button updateButton = findViewById(R.id.updateB);
            Button deleteButton = findViewById(R.id.deleteB);
            Button cancelButton = findViewById(R.id.cancelB);
//            EditText serviceName = findViewById(R.id.serviceName);
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
            MyDBHandler data = new MyDBHandler(getApplicationContext());
            List<String> services = data.getList("Name","ServiceTypes");
            showServiceList(services);
        }
    }

    /**
     * Called when clicking on the delete button
     * Delete the service from the table ServiceTypes
     * @param view
     */
    public void onClickDelete(View view) {
        // Create the dialog box to confirm with the user that he really wants to delete the serviceType
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete service");
        builder.setMessage("Are you sure that you want to delete this service?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                updatedServiceType.delete(getApplicationContext());
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
                MyDBHandler data = new MyDBHandler(getApplicationContext());
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

    /**
     * Called when clicking on the cancel button
     * Hides the Update, Delete and Cancel buttons and show the Add button.
     * @param view
     */
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
