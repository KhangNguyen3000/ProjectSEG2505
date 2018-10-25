package com.example.nguye.seg2505app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
//        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
//        ArrayAdapter<String> typeUserAdapter = new ArrayAdapter<String>(RegisterFinal.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.account_type_spinner));
//        typeUserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        typeUserSpinner.setAdapter(typeUserAdapter);

        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        ArrayAdapter<CharSequence> typeUserAdapter = ArrayAdapter.createFromResource(this, R.array.account_types, android.R.layout.simple_spinner_item);
        typeUserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeUserSpinner.setAdapter(typeUserAdapter);

    }

    public void onClickRegisterButton(View view){
        MyDBHandler dbHandler = new MyDBHandler(this);
        Account account = new Account();
        account.setFirstName(((EditText) findViewById(R.id.reg_input_firstName)).getText().toString());
        account.setLastName(((EditText) findViewById(R.id.reg_input_lastName)).getText().toString());
        account.setEmail(((EditText) findViewById(R.id.reg_input_email)).getText().toString());
        account.setPassword(((EditText) findViewById(R.id.reg_input_password)).getText().toString());
        account.setStreetNumber(Integer.parseInt(((EditText) findViewById(R.id.reg_input_streetNumber)).getText().toString()));
        account.setStreetName(((EditText) findViewById(R.id.reg_input_streetName)).getText().toString());
        account.setApartment(((EditText) findViewById(R.id.reg_input_apartment)).getText().toString());
        account.setCity(((EditText) findViewById(R.id.reg_input_city)).getText().toString());
        account.setProvince(((EditText) findViewById(R.id.reg_input_province)).getText().toString());
        account.setCountry(((EditText) findViewById(R.id.reg_input_country)).getText().toString());
        account.setPostalCode(((EditText) findViewById(R.id.reg_input_postalCode)).getText().toString());
        account.setPhoneNumber(Long.parseLong(((EditText) findViewById(R.id.reg_input_streetNumber)).getText().toString()));

        int accType;
        if(((EditText) findViewById(R.id.reg_input_province)).getText().toString().equals("Administrator")) {
            accType = 1;
        } else if(((EditText) findViewById(R.id.reg_input_province)).getText().toString().equals("Provider")) {
            accType = 2;
        } else {
            accType = 3;
        }
        account.setType(accType);
        dbHandler.addAccount(account);
        Toast toast = Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_LONG);
        toast.show();

    }


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
