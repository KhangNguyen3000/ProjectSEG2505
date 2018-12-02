package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.OfferedService;
import com.example.nguye.seg2505app.Storables.ServiceType;

import java.util.List;

public class AddNewService extends AppCompatActivity {

    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        MyDBHandler data = new MyDBHandler(this);

        List<String> services = data.getList("Name","ServiceTypes");
         showServiceList(services);

        final ListView serviceList = (ListView) findViewById(R.id.s_list);
//        serviceList.setClickable(true);
        serviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MyDBHandler data = new MyDBHandler(getApplicationContext());
                String selectedService = (String) parent.getItemAtPosition(position);
//                ServiceType serviceType = data.findServiceType(selectedService);
                ServiceType serviceType = new ServiceType().find(getApplicationContext(), ServiceType.COL_NAME, selectedService, true);

                // Show the modify options
                ListView serviceList = (ListView) findViewById(R.id.s_list);
                Button addButton = findViewById(R.id.b_add);
                Button cancelButton = findViewById(R.id.b_cancel);
                TextView name = findViewById(R.id.service_name);
                TextView maxRate = findViewById(R.id.max_rate);
                EditText myRate = findViewById(R.id.my_rate);
                TextView msg = findViewById(R.id.msg);
                TextView msg_rate = findViewById(R.id.txt_max_rate);
                addButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);
                name.setVisibility(View.VISIBLE);
                maxRate.setVisibility(View.VISIBLE);
                myRate.setVisibility(View.VISIBLE);
                msg_rate.setVisibility(View.VISIBLE);
                msg.setVisibility(View.GONE);
                serviceList.setVisibility(View.GONE);

                // Fills the fields with the current information of the selected service
                name.setText(serviceType.getName());
                maxRate.setText(Double.toString(serviceType.getMaxRate()));

                // Stores the information of the selected service type
                //service.setTypeID(serviceType.getID());
                //service.setProviderID(currentAccount.getID());
            }
        });
    }

    public void showServiceList(List<String> services){
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, services.toArray(new String[services.size()]));
        this.listView = (ListView) findViewById(R.id.s_list);
        listView.setAdapter(itemsAdapter);
    }

    public void onClickAdd(View view){

        TextView name = findViewById(R.id.service_name);
        String nameService = name.getText().toString();
        ServiceType serviceType = new ServiceType().find(getApplicationContext(), ServiceType.COL_NAME, nameService, true);
        int idService = serviceType.getID();
        Account currentAccount = Account.getCurrentAccount();
        int idProvider = currentAccount.getID();

        EditText myRate = findViewById(R.id.my_rate);
        String rate = myRate.getText().toString();
        TextView maxRate = findViewById((R.id.max_rate));
        String max_rate = maxRate.getText().toString();
        if (Double.parseDouble(rate)> Double.parseDouble(max_rate)){
            Toast error = Toast.makeText(getApplicationContext(), "Please enter a valid rate", Toast.LENGTH_LONG);
            error.show();
        }
        else {
            OfferedService service = new OfferedService();
            service.setHourlyRate(Double.parseDouble(rate));
            service.setProviderID(idProvider);
            service.setTypeID(idService);
            service.add(this);
            Toast toast = Toast.makeText(getApplicationContext(), "Service added !", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
    }

    public void onClickCancel(View view){

        ListView serviceList = (ListView) findViewById(R.id.s_list);
        Button addButton = findViewById(R.id.b_add);
        Button cancelButton = findViewById(R.id.b_cancel);
        TextView name = findViewById(R.id.service_name);
        TextView maxRate = findViewById(R.id.max_rate);
        EditText myRate = findViewById(R.id.my_rate);
        TextView msg = findViewById(R.id.msg);
        TextView msg_rate = findViewById(R.id.txt_max_rate);
        addButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
        name.setVisibility(View.GONE);
        maxRate.setVisibility(View.GONE);
        myRate.setVisibility(View.GONE);
        msg.setVisibility(View.VISIBLE);
        msg_rate.setVisibility(View.GONE);
        serviceList.setVisibility(View.VISIBLE);

        name.setText("");
        maxRate.setText("");
        myRate.setText("");
    }
}
