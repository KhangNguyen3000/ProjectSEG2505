package com.example.nguye.seg2505app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_final);

        //Spinner
        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        ArrayAdapter<String> typeUserAdapter = new ArrayAdapter<String>(RegisterFinal.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.account_type_spinner));
        typeUserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeUserSpinner.setAdapter(typeUserAdapter);
    }
}
