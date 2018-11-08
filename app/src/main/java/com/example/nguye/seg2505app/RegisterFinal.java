package com.example.nguye.seg2505app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterFinal extends AppCompatActivity {

    String firstName, lastName;
    String email, cEmail;
    String pass, cPass;
    long phoneNumber;
    int streetNumber;
    String street, city, province, country, postalC, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_final);

        //Spinner
        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        ArrayAdapter<CharSequence> typeUserAdapter = ArrayAdapter.createFromResource(this, R.array.account_type_spinner, android.R.layout.simple_spinner_item);
        typeUserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeUserSpinner.setAdapter(typeUserAdapter);

    }

    public void addUserToDatabase(){
        MyDBHandler dbHandler = new MyDBHandler(this);
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.setPassword(pass);
        account.setStreetNumber(streetNumber);
        account.setStreetName(street);

        account.setCity(city);
        account.setProvince(province);
        account.setCountry(country);
        account.setPostalCode(postalC);
        account.setPhoneNumber(phoneNumber);

        int accType = 0;
        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        String typeOption = typeUserSpinner.getSelectedItem().toString();

        if (typeOption.equals("Client")) {
            accType = 3;
        }
        else if (typeOption.equals("Provider")) {
            accType = 2;
        }
        account.setType(accType);
        dbHandler.addAccount(account);
        Toast toast = Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }


    public void onClickRegister(View view){
        int lenght = 0;
        MyDBHandler dbHandler = new MyDBHandler(this);
        Context context = getApplicationContext();
        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        firstName = ((EditText) findViewById(R.id.reg_input_firstName)).getText().toString();
        lastName = ((EditText) findViewById(R.id.reg_input_lastName)).getText().toString();
        email = ((EditText) findViewById(R.id.reg_input_email)).getText().toString();
        cEmail = ((EditText) findViewById(R.id.reg_input_emailConfirm)).getText().toString();
        pass= ((EditText) findViewById(R.id.reg_input_password)).getText().toString();
        cPass= ((EditText) findViewById(R.id.reg_input_passwordConfirm)).getText().toString();
        street = ((EditText) findViewById(R.id.reg_input_streetName)).getText().toString();
        city = ((EditText) findViewById(R.id.reg_input_city)).getText().toString();
        province = ((EditText) findViewById(R.id.reg_input_province)).getText().toString();
        country = ((EditText) findViewById(R.id.reg_input_country)).getText().toString();
        postalC = ((EditText) findViewById(R.id.reg_input_postalCode)).getText().toString();
        lenght = ((EditText) findViewById(R.id.reg_input_phone)).getText().length();
        Checking check = new Checking(firstName, lastName, email, cEmail, pass, cPass, street, city, province, country, postalC, lenght, dbHandler, context);
        if (check.checkAll()){
            addUserToDatabase();
        }
    }



}
