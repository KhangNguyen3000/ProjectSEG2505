package com.example.nguye.seg2505app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyScreen extends AppCompatActivity{


    String firstName, lastName;
    String email;
    String pass;
    long phoneNumber;
    int streetNumber;
    String street, city, province, country, postalC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);

        Account currentAccount = CurrentAccount.getCurrentAccount();

        TextView tvFirstName = (TextView)findViewById(R.id.mod_input_firstName);
        tvFirstName.setText(currentAccount.getFirstName());

        TextView tvLastName = (TextView)findViewById(R.id.mod_input_lastName);
        tvLastName.setText(currentAccount.getLastName());

        TextView tvEmail = (TextView)findViewById(R.id.mod_input_email);
        tvEmail.setText(currentAccount.getEmail());

        TextView tvPassWord = (TextView)findViewById(R.id.mod_input_password);
        tvPassWord.setText(currentAccount.getPassword());

        TextView tvStreetN = (TextView)findViewById(R.id.mod_input_streetNumber);
        tvStreetN.setText(Integer.toString(currentAccount.getStreetNumber()));

        TextView tvStreet = (TextView)findViewById(R.id.mod_input_streetName);
        tvStreet.setText(currentAccount.getStreetName());

        TextView tvCity = (TextView)findViewById(R.id.mod_input_city);
        tvCity.setText((currentAccount.getStreetName()));

        TextView tvProvince = (TextView)findViewById(R.id.mod_input_province);
        tvProvince.setText(currentAccount.getProvince());

        TextView tvCountry = (TextView)findViewById(R.id.mod_input_country);
        tvCountry.setText((currentAccount.getCountry()));

        TextView tvPostal = (TextView)findViewById(R.id.mod_input_postalCode);
        tvPostal.setText(currentAccount.getPostalCode());

        TextView tvPhoneNumber = (TextView)findViewById(R.id.mod_input_phone);
        tvPhoneNumber.setText(Long.toString(currentAccount.getPhoneNumber()));
    }


    public void modifyCurrentUser(){
        MyDBHandler dbHandler = new MyDBHandler(this);
        Account currentAccount = CurrentAccount.getCurrentAccount();
        currentAccount.setFirstName(firstName);
        currentAccount.setLastName(lastName);
        currentAccount.setEmail(email);
        currentAccount.setPassword(pass);
        currentAccount.setStreetNumber(streetNumber);
        currentAccount.setStreetName(street);
        currentAccount.setCity(city);
        currentAccount.setProvince(province);
        currentAccount.setCountry(country);
        currentAccount.setPostalCode(postalC);
        currentAccount.setPhoneNumber(phoneNumber);
        currentAccount.setId(CurrentAccount.getCurrentAccount().getId());
        dbHandler.modifyAccount(currentAccount);
        Toast toast = Toast.makeText(getApplicationContext(), "Account modified!", Toast.LENGTH_LONG);
        toast.show();

    }



    public void clickModify(View view){
        int len = 0;
        MyDBHandler dbHandler = new MyDBHandler(this);
        Context context = getApplicationContext();
        firstName = ((EditText) findViewById(R.id.mod_input_firstName)).getText().toString();
        lastName = ((EditText) findViewById(R.id.mod_input_lastName)).getText().toString();
        email = ((EditText) findViewById(R.id.mod_input_email)).getText().toString();
        pass= ((EditText) findViewById(R.id.mod_input_password)).getText().toString();
        streetNumber = Integer.parseInt(((EditText) findViewById(R.id.mod_input_streetNumber)).getText().toString());
        street = ((EditText) findViewById(R.id.mod_input_streetName)).getText().toString();
        city = ((EditText) findViewById(R.id.mod_input_city)).getText().toString();
        province = ((EditText) findViewById(R.id.mod_input_province)).getText().toString();
        country = ((EditText) findViewById(R.id.mod_input_country)).getText().toString();
        phoneNumber = Long.parseLong(((EditText) findViewById(R.id.mod_input_phone)).getText().toString());
        postalC = ((EditText) findViewById(R.id.mod_input_postalCode)).getText().toString();
        len = ((EditText) findViewById(R.id.mod_input_phone)).getText().length();
        Checking check = new Checking(firstName, lastName, email, pass, street, city, province, country, postalC, len, dbHandler, context);
        if (check.checkAll()){
            modifyCurrentUser();
        }
    }


}
