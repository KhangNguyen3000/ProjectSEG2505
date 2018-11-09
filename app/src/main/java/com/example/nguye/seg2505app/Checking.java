package com.example.nguye.seg2505app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Checking extends AppCompatActivity {

    String action;

    String firstName, lastName, email, cEmail,  pass, cPass, street, city, province, country, postalC;
    int lenPhoneN;
    MyDBHandler dbHandler;
    Context context;




    // First constructor, used when a user registers

    public Checking(String fN, String lN, String eM, String cEM, String pW, String cPW, String street, String city, String province, String country, String postalC, int len,
                    MyDBHandler dbHandler, Context context){
        this.action = "register";
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
        this.dbHandler = dbHandler;
        this.context = context;
    }



    // Second constructor, used when a user wants to modify his informations
    // email verification and password verification aren't required

    public Checking(String fN, String lN, String eM, String pW, String street, String city, String province, String country, String postalC, int len,
                    MyDBHandler dbHandler, Context context){
        this.action = "modify";
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



    // This function is used to check if the email set in the field is correct

    public boolean checkEmail(){
        boolean answer = true;
        if(dbHandler.findAccount(email) != null && this.action.equals("register")){
            // Only enter in this block if you are registering

            Toast account1 = Toast.makeText(context, "Account already existing with this email adress", Toast.LENGTH_LONG);
            account1.show();
            answer = false;
        }
        if(!(email.contains("@"))){
            // Check if the email contains an "@" symbol

            Toast email1 = Toast.makeText(context, "Invalid e-mail adress", Toast.LENGTH_LONG);
            email1.show();
            answer = false;
        }
        if(!email.equals(cEmail)){
            // Check if the email and the email verification are equals

            Toast email2 = Toast.makeText(context, "The two e-mail adress are not the same", Toast.LENGTH_LONG);
            email2.show();
            answer = false;
        }
        return answer;
    }


    // This function is used to check if the password set in the field is correct

    public boolean checkPass(){
        boolean answer = true;
        if (pass.length() < 6 || pass.length() > 20){
            // Check if the password has a definite size

            Toast password = Toast.makeText(this, "Please use a 6-20 length password", Toast.LENGTH_LONG);
            password.show();
            answer = false;
        }
        if(!pass.equals(cPass)){
            // Check if the password and the password verification are equals

            Toast pass1 = Toast.makeText(context, "The two passwords are not the same", Toast.LENGTH_LONG);
            pass1.show();
            answer = false;
        }
        return answer;
    }



    // This function is used to verify that none of the strings contain numbers

    public boolean checkAllStrings(){

        boolean answer = true;
        String numbers[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for(int i = 0; i<10; i++){
            if (firstName.contains(numbers[i])){
                answer = false;
                Toast fName = Toast.makeText(context, "Invalid first Name", Toast.LENGTH_LONG);
                fName.show();
            }
            if (lastName.contains(numbers[i])){
                answer = false;
                Toast lName = Toast.makeText(context, "Invalid last Name", Toast.LENGTH_LONG);
                lName.show();
            }
            if (street.contains(numbers[i])){
                answer = false;
                Toast streetToast = Toast.makeText(context, "Invalid street name", Toast.LENGTH_LONG);
                streetToast.show();
            }
            if (city.contains(numbers[i])){
                answer = false;
                Toast cityToast = Toast.makeText(context, "Invalid city name", Toast.LENGTH_LONG);
                cityToast.show();
            }
            if (country.contains(numbers[i])){
                answer = false;
                Toast countryToast = Toast.makeText(context, "Invalid country name", Toast.LENGTH_LONG);
                countryToast.show();
            }
        }
        return answer;
    }



    // this function is used to verify that none of the fields is empty

    public boolean nonEmpty(){
        boolean answer = true;
        if (firstName.matches("") || lastName.matches("") ||  email.matches("") ||  cEmail.matches("")
                ||  pass.matches("")  ||  cPass.matches("")  ||  street.matches("")  ||  city.matches("")
                ||  province.matches("") ||  country.matches("") ||  postalC.matches("")){
            Toast empty = Toast.makeText(context, "Please fill every field", Toast.LENGTH_LONG);
            empty.show();
            answer = false;
        }
        return answer;
    }




    // This function is used to check if the phone number is correct. It checks if it's a 10 long number

    public boolean checkLen(int l){
        if (l == 10){
            return true;
        }
        Toast phone= Toast.makeText(context, "Please enter a valid number phone", Toast.LENGTH_LONG);
        phone.show();
        return false;
    }



    // checkAll() calls all the previous functions and return true if all the functions returned true, false if not

    public boolean checkAll(){
        return(nonEmpty() && checkLen(lenPhoneN) &&  checkAllStrings() && checkPass() && checkEmail());
    }
}
