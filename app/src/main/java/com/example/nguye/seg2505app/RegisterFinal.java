package com.example.nguye.seg2505app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterFinal extends AppCompatActivity {

    EditText firstName, lastName;
    EditText email, cEmail;
    EditText pass, cPass;
    EditText num, ap, street, city, province, country, postalC;

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
        //End Spinner

        firstName = (EditText) findViewById(R.id.reg_input_firstName);
        lastName = (EditText) findViewById(R.id.reg_input_lastName);
        email = (EditText) findViewById(R.id.reg_input_email);
        cEmail = (EditText) findViewById(R.id.reg_input_emailConfirm);
        pass= (EditText) findViewById(R.id.reg_input_password);
        cPass= (EditText) findViewById(R.id.reg_input_passwordConfirm);
        num = (EditText) findViewById(R.id.reg_input_streetNumber);
        ap = (EditText) findViewById(R.id.reg_input_appartment);
        street = (EditText) findViewById(R.id.reg_input_streetName);
        city = (EditText) findViewById(R.id.reg_input_city);
        province = (EditText) findViewById(R.id.reg_input_province);
        country = (EditText) findViewById(R.id.reg_input_country);
        postalC = (EditText) findViewById(R.id.reg_input_postalCode);
    }

    public void onClickRegister(View view){
        if (checkAll()){
            addUserToDatabase();
        }
        else{
            //Dire à l'utilisateur qu'il n'a pas bien rempli le formulaire
        }
    }


}
