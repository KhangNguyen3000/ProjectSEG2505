package com.example.nguye.seg2505app;

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
    String street, city, province, country, postalC;

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
        MyDBHandler dbHandler = new MyDBHandler(this);
        boolean answer = true;
        if(dbHandler.findAccount(email) != null){
            Toast account1 = Toast.makeText(getApplicationContext(), "Account already existing with this email adress", Toast.LENGTH_LONG);
            account1.show();
            answer = false;
        }
        if(email == null || cEmail == null){
            Toast em = Toast.makeText(getApplicationContext(), "Please fill every field", Toast.LENGTH_LONG);
            em.show();
            answer = false;
        }
        if(!(email.contains("@"))){
            Toast email1 = Toast.makeText(getApplicationContext(), "Invalid e-mail adress", Toast.LENGTH_LONG);
            email1.show();
            answer = false;
        }
        if(!email.equals(cEmail)){
            Toast email2 = Toast.makeText(getApplicationContext(), "The two e-mail adress are not the same", Toast.LENGTH_LONG);
            email2.show();
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
        if(!pass.equals(cPass)){
            Toast pass1 = Toast.makeText(getApplicationContext(), "The two passwords are not the same", Toast.LENGTH_LONG);
            pass1.show();
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
        if (firstName.matches("") || lastName.matches("") ||  email.matches("") ||  cEmail.matches("")
                ||  pass.matches("")  ||  cPass.matches("")  ||  street.matches("")  ||  city.matches("")
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

    public void onClickRegister(View view){
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
        System.out.println(firstName);
        if (checkAll()){
            addUserToDatabase();
        }
    }



}
