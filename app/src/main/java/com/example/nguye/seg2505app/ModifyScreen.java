package com.example.nguye.seg2505app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyScreen extends AppCompatActivity{


    String firstName, lastName;
    String email, cEmail;
    String pass, cPass;
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

        dbHandler.modifyAccount(currentAccount);
        Toast toast = Toast.makeText(getApplicationContext(), "Account modified!", Toast.LENGTH_LONG);
        toast.show();
    }

    public boolean checkEmail(){
        MyDBHandler dbHandler = new MyDBHandler(this);
        boolean answer = true;
        if(dbHandler.findAccount(email) != null){
            Toast account1 = Toast.makeText(getApplicationContext(), "Account already existing with this email adress", Toast.LENGTH_LONG);
            account1.show();
            answer = false;
        }
        if(email == null){
            Toast em = Toast.makeText(getApplicationContext(), "Please fill every field", Toast.LENGTH_LONG);
            em.show();
            answer = false;
        }
        if(!(email.contains("@"))){
            Toast email1 = Toast.makeText(getApplicationContext(), "Invalid e-mail adress", Toast.LENGTH_LONG);
            email1.show();
            answer = false;
        }
        return answer;
    }

    public boolean checkPass(){
        boolean answer = true;
        if (pass.length() < 6 || pass.length() > 20){
            Toast password = Toast.makeText(this, "Please use a 6-20 length password", Toast.LENGTH_LONG);
            password.show();
            answer = false;
        }
        return answer;
    }

    public boolean checkAllStrings(){

        boolean answer = true;
        String numbers[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for(int i = 0; i<10; i++){
            if (firstName.contains(numbers[i])){
                answer = false;
                Toast fName = Toast.makeText(getApplicationContext(), "Inavlid first Name", Toast.LENGTH_LONG);
                fName.show();
            }
            if (lastName.contains(numbers[i])){
                answer = false;
                Toast lName = Toast.makeText(getApplicationContext(), "Inavlid last Name", Toast.LENGTH_LONG);
                lName.show();
            }
            if (street.contains(numbers[i])){
                answer = false;
                Toast streetToast = Toast.makeText(getApplicationContext(), "Inavlid street name", Toast.LENGTH_LONG);
                streetToast.show();
            }
            if (city.contains(numbers[i])){
                answer = false;
                Toast cityToast = Toast.makeText(getApplicationContext(), "Inavlid city name", Toast.LENGTH_LONG);
                cityToast.show();
            }
            if (country.contains(numbers[i])){
                answer = false;
                Toast countryToast = Toast.makeText(getApplicationContext(), "Inavlid country name", Toast.LENGTH_LONG);
                countryToast.show();
            }
        }
        return answer;
    }

    public boolean nonEmpty(){
        boolean answer = true;
        if (firstName.matches("") || lastName.matches("") ||  email.matches("")
                ||  pass.matches("") ||  street.matches("")  ||  city.matches("")
                ||  province.matches("") ||  country.matches("") ||  postalC.matches("")){
            Toast empty = Toast.makeText(getApplicationContext(), "Please fill every field", Toast.LENGTH_LONG);
            empty.show();
            answer = false;
        }
        return answer;
    }

    public boolean checkPostalCode(){
//        if(postalC.matches("\\c{1}\\d{1}\\c{1}\\d{1}\\c{1}\\d{1}")){
//            return true;
//        }
//        Toast postal = Toast.makeText(getApplicationContext(), "Please enter a valid Postal Code", Toast.LENGTH_LONG);
//        postal.show();
//        return false;

        return true;
    }


    public boolean checkAll(){
        return(nonEmpty() && checkAllStrings() && checkPostalCode() && checkPass() && checkEmail()
                && checkPostalCode());
    }

    public void clickModify(View view){
        firstName = ((EditText) findViewById(R.id.mod_input_firstName)).getText().toString();
        lastName = ((EditText) findViewById(R.id.mod_input_lastName)).getText().toString();
        email = ((EditText) findViewById(R.id.mod_input_email)).getText().toString();
        pass= ((EditText) findViewById(R.id.mod_input_password)).getText().toString();
        street = ((EditText) findViewById(R.id.mod_input_streetName)).getText().toString();
        city = ((EditText) findViewById(R.id.mod_input_city)).getText().toString();
        province = ((EditText) findViewById(R.id.mod_input_province)).getText().toString();
        country = ((EditText) findViewById(R.id.mod_input_country)).getText().toString();
        postalC = ((EditText) findViewById(R.id.mod_input_postalCode)).getText().toString();
        System.out.println(firstName);
        if (checkAll()){
            modifyCurrentUser();
        }
    }


}
