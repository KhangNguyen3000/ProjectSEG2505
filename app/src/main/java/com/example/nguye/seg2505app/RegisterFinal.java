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

    String firstName, lastName;
    String email, cEmail;
    String pass, cPass;
    String num, ap, street, city, province, country, postalC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_final);

        firstName = ((EditText) findViewById(R.id.reg_input_firstName)).getText().toString();
        lastName = ((EditText) findViewById(R.id.reg_input_lastName)).getText().toString();
        email = ((EditText) findViewById(R.id.reg_input_email)).getText().toString();
        cEmail = ((EditText) findViewById(R.id.reg_input_emailConfirm)).getText().toString();
        pass= ((EditText) findViewById(R.id.reg_input_password)).getText().toString();
        cPass= ((EditText) findViewById(R.id.reg_input_passwordConfirm)).getText().toString();
        ap = ((EditText) findViewById(R.id.reg_input_appartment)).getText().toString();
        street = ((EditText) findViewById(R.id.reg_input_streetName)).getText().toString();
        city = ((EditText) findViewById(R.id.reg_input_city)).getText().toString();
        province = ((EditText) findViewById(R.id.reg_input_province)).getText().toString();
        country = ((EditText) findViewById(R.id.reg_input_country)).getText().toString();
        postalC = ((EditText) findViewById(R.id.reg_input_postalCode)).getText().toString();

        //Spinner
        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        ArrayAdapter<CharSequence> typeUserAdapter = ArrayAdapter.createFromResource(this, R.array.account_types, android.R.layout.simple_spinner_item);
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
        account.setStreetNumber(Integer.parseInt(((EditText) findViewById(R.id.reg_input_streetNumber)).getText().toString()));
        account.setStreetName(street);
        account.setApartment(ap);
        account.setCity(city);
        account.setProvince(province);
        account.setCountry(country);
        account.setPostalCode(postalC);
        account.setPhoneNumber(Long.parseLong(((EditText) findViewById(R.id.reg_input_streetNumber)).getText().toString()));

        int accType = 0;
        if(((EditText) findViewById(R.id.reg_input_province)).getText().toString().equals("Client")) {
            accType = 3;
        }
        else if(((EditText) findViewById(R.id.reg_input_province)).getText().toString().equals("Provider")) {
            accType = 2;
        }
        else if(((EditText) findViewById(R.id.reg_input_province)).getText().toString().equals("Administrator")) {
            accType = 1;
        }
        account.setType(accType);
        dbHandler.addAccount(account);
        Toast toast = Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_LONG);
        toast.show();
    }

    public boolean checkEmail(){
        Toast toast;
        boolean answer = true;
        if(!email.contains("@")){
            toast = Toast.makeText(getApplicationContext(), "Invalid e-mail adress", Toast.LENGTH_LONG);
            toast.show();
            answer = false;
        }
        if(!email.equals(cEmail)){
            toast = Toast.makeText(getApplicationContext(), "The two e-mail adress are not the same", Toast.LENGTH_LONG);
            toast.show();
            answer = false;
            }
        return answer;
    }

    public boolean checkPass(){
        Toast toast;
        boolean answer = true;
        if(!pass.equals(cPass)){
            toast = Toast.makeText(getApplicationContext(), "The two passwords are not the same", Toast.LENGTH_LONG);
            toast.show();
            answer = false;
        }
        return answer;
    }

    public boolean checkAll(){
        return (checkEmail() && checkPass());
    }

    public void onClickRegister(View view){
        if (checkAll()){
            addUserToDatabase();
        }
    }



}
