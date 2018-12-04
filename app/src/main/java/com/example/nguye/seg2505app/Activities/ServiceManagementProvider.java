package com.example.nguye.seg2505app.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.OfferedService;
import com.example.nguye.seg2505app.Storables.ServiceType;
import com.example.nguye.seg2505app.Storables.Storable;
import com.example.nguye.seg2505app.Utilities.Validation;

import java.util.ArrayList;
import java.util.List;

public class ServiceManagementProvider extends AppCompatActivity {

    ListView listView;
    private OfferedService updatedOfferedService;
    int maxRate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_management_provider);

        Account currentAccount = Account.getCurrentAccount();

        ArrayList<OfferedService> servicesL = OfferedService.findAll(this, OfferedService.COL_PROVIDER, currentAccount.getID(), false);

        ArrayList<String> servicesName = getNameFromServices(servicesL, this);

        final ListView serviceList = (ListView) findViewById(R.id._ListViewMyServices);

        showServiceList(servicesName);

        serviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Account currentAccount = Account.getCurrentAccount();
                ArrayList<OfferedService> servicesL = OfferedService.findAll(getApplicationContext(), OfferedService.COL_PROVIDER, currentAccount.getID(), false);
                ArrayList<String> servicesName = getNameFromServices(servicesL, getApplicationContext());

                String selectedService = (String) parent.getItemAtPosition(position);
//                ServiceType serviceType = data.findServiceType(selectedService);
                OfferedService actualService = servicesL.get(servicesName.indexOf(selectedService));
                updatedOfferedService = servicesL.get(servicesName.indexOf(selectedService));

                // Show the modify options
                Button addButton = findViewById(R.id.add_button);
                Button updateButton = findViewById(R.id.update_button);
                Button deleteButton = findViewById(R.id.delete_button);
                Button cancelButton = findViewById(R.id.cancel_button);
                TextView name = findViewById(R.id.text_name);
                EditText price = findViewById(R.id.text_price);
                addButton.setVisibility(View.GONE);
                name.setVisibility(View.VISIBLE);
                price.setVisibility(View.VISIBLE);
                updateButton.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);

                // Fills the fields with the current information of the selected service
                int myID = actualService.getTypeID();

                String query = "SELECT " + ServiceType.TABLE_NAME + "." + ServiceType.COL_MAXRATE
                        + " FROM (" + ServiceType.TABLE_NAME
                        + " INNER JOIN " + OfferedService.TABLE_NAME
                        + " ON " + ServiceType.TABLE_NAME + "." + ServiceType.COL_ID
                        + " = " + OfferedService.TABLE_NAME + "." + OfferedService.COL_PROVIDER + ") WHERE " + OfferedService.TABLE_NAME + "." + OfferedService.COL_ID + " = " + myID;

                ArrayList<String[]> array= Storable.select(getApplicationContext(), query, 1);

                maxRate = Integer.parseInt(array.get(0)[0]);

                name.setText(selectedService);
                double actualPrice = actualService.getHourlyRate();
                price.setText(Double.toString((actualPrice)));
            }
        });

    }

    public void onResume(){
        super.onResume();

        Account currentAccount = Account.getCurrentAccount();

        ArrayList<OfferedService> servicesL = OfferedService.findAll(this, OfferedService.COL_PROVIDER, currentAccount.getID(), false);

        ArrayList<String> servicesName = getNameFromServices(servicesL, getApplicationContext());

        final ListView serviceList = (ListView) findViewById(R.id._ListViewMyServices);

        showServiceList(servicesName);
    }

    static ArrayList<String> getNameFromServices(ArrayList<OfferedService> myList, Context context){
        ArrayList<String> finalList = new ArrayList<String>();
        int type = 0;
        for(int i = 0; i < myList.size(); i++){
            type = myList.get(i).getTypeID();
            finalList.add(Storable.search(context , ServiceType.TABLE_NAME, "Name", "ID", type, false));
        }
        return finalList;
    }

    public void showServiceList(List<String> services){
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, services.toArray(new String[services.size()]));
        this.listView = (ListView) findViewById(R.id._ListViewMyServices);
        listView.setAdapter(itemsAdapter);
    }

    public void onClickAddService(View view) {
        Intent intent = new Intent(getApplicationContext(), AddNewService.class);
        startActivity(intent);
    }


    public void onClickUpdate(View view) {
        ViewGroup layout = findViewById(R.id.smp_layout_root);
        if (Validation.validateAll(layout)) {
            // Get the inputs and update the object updatedServiceType
            double newServiceRate = Double.parseDouble(((EditText) findViewById(R.id.text_price)).getText().toString());

            if(newServiceRate > maxRate){
                Toast error = Toast.makeText(getApplicationContext(), "Please enter a valid rate: " + maxRate, Toast.LENGTH_LONG);
                error.show();
            }
            else {
                updatedOfferedService.setHourlyRate(newServiceRate);

                //            data.updateServiceType(updatedServiceType);
                updatedOfferedService.update(this);

                // Hide the modify options and clear the fields
                Button addButton = findViewById(R.id.add_button);
                Button updateButton = findViewById(R.id.update_button);
                Button deleteButton = findViewById(R.id.delete_button);
                Button cancelButton = findViewById(R.id.cancel_button);
                TextView name = findViewById(R.id.text_name);
                EditText price = findViewById(R.id.text_price);

                addButton.setVisibility(View.VISIBLE);
                updateButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
                cancelButton.setVisibility(View.INVISIBLE);
                name.setText("");
                price.setText("");
                name.setVisibility(View.GONE);
                price.setVisibility(View.GONE);

                //Display the success message
                Toast toast = Toast.makeText(getApplicationContext(), "Service updtated!", Toast.LENGTH_LONG);
                toast.show();

                //List update
                Account currentAccount = Account.getCurrentAccount();
                ArrayList<OfferedService> servicesL = OfferedService.findAll(getApplicationContext(), OfferedService.COL_PROVIDER, currentAccount.getID(), false);
                ArrayList<String> servicesName = getNameFromServices(servicesL, getApplicationContext());
                showServiceList(servicesName);
            }
        }
    }

    public void onClickDelete(View view) {
        // Create the dialog box to confirm with the user that he really wants to delete the serviceType
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete service");
        builder.setMessage("Are you sure that you want to delete this service?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                data.deleteServiceType(updatedServiceType);
                updatedOfferedService.delete(getApplicationContext());
                //Display the success message
                Toast toast = Toast.makeText(getApplicationContext(), "Service deleted!", Toast.LENGTH_LONG);
                toast.show();

                // Hide the modify options and clear the fields
                Button addButton = findViewById(R.id.add_button);
                Button updateButton = findViewById(R.id.update_button);
                Button deleteButton = findViewById(R.id.delete_button);
                Button cancelButton = findViewById(R.id.cancel_button);
                TextView name = findViewById(R.id.text_name);
                EditText price = findViewById(R.id.text_price);

                addButton.setVisibility(View.VISIBLE);
                updateButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.INVISIBLE);
                cancelButton.setVisibility(View.INVISIBLE);
                name.setText("");
                price.setText("");
                name.setVisibility(View.GONE);
                price.setVisibility(View.GONE);

                // Update the list view
                Account currentAccount = Account.getCurrentAccount();
                ArrayList<OfferedService> servicesL = OfferedService.findAll(getApplicationContext(), OfferedService.COL_PROVIDER, currentAccount.getID(), false);
                ArrayList<String> servicesName = getNameFromServices(servicesL, getApplicationContext());
                showServiceList(servicesName);
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
        Button addButton = findViewById(R.id.add_button);
        Button updateButton = findViewById(R.id.update_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button cancelButton = findViewById(R.id.cancel_button);
        TextView name = findViewById(R.id.text_name);
        EditText price = findViewById(R.id.text_price);

        addButton.setVisibility(View.VISIBLE);
        updateButton.setVisibility(View.INVISIBLE);
        deleteButton.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
        name.setText("");
        price.setText("");
        name.setVisibility(View.GONE);
        price.setVisibility(View.GONE);
    }


}


