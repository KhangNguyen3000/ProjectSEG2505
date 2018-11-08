package com.example.nguye.seg2505app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Checking extends AppCompatActivity {

    String firstName, lastName, email, cEmail,  pass, cPass, street, city, province, country, postalC;
    int lenPhoneN;
    MyDBHandler dbHandler;
    Context context;


    public Checking(String fN, String lN, String eM, String cEM, String pW, String cPW, String street, String city, String province, String country, String postalC, int len,
                    MyDBHandler dbHandler, Context context){
        firstName = fN;
        lastName = lN;
        email = eM;
        cEmail = cEM;
        pass = pW;
        cPass =cPW;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalC = postalC;
        lenPhoneN = len;
        this.dbHandler =dbHandler;
        this.context = context;
    }

    public Checking(String fN, String lN, String eM, String pW, String street, String city, String province, String country, String postalC, int len,
                    MyDBHandler dbHandler, Context context){
        firstName = fN;
        lastName = lN;
        email = eM;
        cEmail = eM;
        pass = pW;
        cPass = pW;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalC = postalC;
        lenPhoneN = len;
        this.dbHandler =dbHandler;
        this.context = context;
    }

    public boolean checkEmail(){
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
                Toast fName = Toast.makeText(getApplicationContext(), "Invalid first Name", Toast.LENGTH_LONG);
                fName.show();
            }
            if (lastName.contains(numbers[i])){
                answer = false;
                Toast lName = Toast.makeText(getApplicationContext(), "Invalid last Name", Toast.LENGTH_LONG);
                lName.show();
            }
            if (street.contains(numbers[i])){
                answer = false;
                Toast streetToast = Toast.makeText(getApplicationContext(), "Invalid street name", Toast.LENGTH_LONG);
                streetToast.show();
            }
            if (city.contains(numbers[i])){
                answer = false;
                Toast cityToast = Toast.makeText(getApplicationContext(), "Invalid city name", Toast.LENGTH_LONG);
                cityToast.show();
            }
            if (country.contains(numbers[i])){
                answer = false;
                Toast countryToast = Toast.makeText(getApplicationContext(), "Invalid country name", Toast.LENGTH_LONG);
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



    public boolean checkLen(int l){
        if (l == 10){
            return true;
        }
        Toast phone= Toast.makeText(getApplicationContext(), "Please enter a valid number phone", Toast.LENGTH_LONG);
        phone.show();
        return false;
    }

    public boolean checkAll(){
        return(checkLen(lenPhoneN) && nonEmpty() &&  checkAllStrings() && checkPass() && checkEmail());
    }
}
