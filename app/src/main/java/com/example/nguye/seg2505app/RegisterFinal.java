package com.example.nguye.seg2505app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterFinal extends AppCompatActivity {

    String firstName, lastName;
    String email, cEmail;
    String pass, cPass;
    String phoneNumber;
    int streetNumber;
    String street, city, province, country, postalC, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_final);

        // Spinner creation
        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        ArrayAdapter<CharSequence> typeUserAdapter = ArrayAdapter.createFromResource(this, R.array.account_type_spinner, android.R.layout.simple_spinner_item);
        typeUserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeUserSpinner.setAdapter(typeUserAdapter);

    }



    // This function is used to store all the values that we need to register a new user

    public void storeInfo(){
        firstName = ((EditText) findViewById(R.id.reg_input_firstName)).getText().toString();
        lastName = ((EditText) findViewById(R.id.reg_input_lastName)).getText().toString();
        email = ((EditText) findViewById(R.id.reg_input_email)).getText().toString();
        cEmail = ((EditText) findViewById(R.id.reg_input_emailConfirm)).getText().toString();
        pass= ((EditText) findViewById(R.id.reg_input_password)).getText().toString();
        cPass= ((EditText) findViewById(R.id.reg_input_passwordConfirm)).getText().toString();
        streetNumber = Integer.parseInt(((EditText) findViewById(R.id.reg_input_streetNumber)).getText().toString());
        street = ((EditText) findViewById(R.id.reg_input_streetName)).getText().toString();
        city = ((EditText) findViewById(R.id.reg_input_city)).getText().toString();
        province = ((EditText) findViewById(R.id.reg_input_province)).getText().toString();
        country = ((EditText) findViewById(R.id.reg_input_country)).getText().toString();
        phoneNumber = ((EditText) findViewById(R.id.reg_input_phone)).getText().toString();
        postalC = ((EditText) findViewById(R.id.reg_input_postalCode)).getText().toString();
    }



    // This function is used when we click on the "Register" button. It verifies that every field is correctly filled.

    public void onClickRegister(View view){

        // Perform validation on all fields
        ViewGroup layout = findViewById(R.id.reg_layout_root);
//        Validation.validateAll(layout);

        // Confirm the email and password
        EditText email = findViewById(R.id.reg_input_email);
        EditText emailConf = findViewById(R.id.reg_input_emailConfirm);
        EditText password = findViewById(R.id.reg_input_password);
        EditText passwordConf = findViewById(R.id.reg_input_passwordConfirm);

        if (Validation.validateAll(layout)
                && Validation.availableEmail(email)
                && Validation.confirmField(email, emailConf, "Email")
                && Validation.confirmField(password, passwordConf, "Password")) {
            storeInfo();
            addUserToDatabase();
        }
    }



    // This function implements the new user to the Database

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
        finish();
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivityForResult(intent, 0);
    }






}
