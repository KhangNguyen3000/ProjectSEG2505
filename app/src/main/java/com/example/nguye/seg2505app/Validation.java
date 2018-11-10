package com.example.nguye.seg2505app;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validation extends AppCompatActivity {

    public static boolean validateEmail(EditText emailField, EditText emailConfirmField) {
        return true;
    }

    public static boolean validateName(EditText inputField) {
        String input = inputField.getText().toString();
        String regex = "^[a-zA-Z]+((['. -][a-zA-Z ])?[a-zA-Z]*)*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        boolean isValid = m.matches();
        if (!isValid) {
            Toast errorMessage = Toast.makeText(inputField.getContext(), "Invalid input in the field " + inputField.getHint(), Toast.LENGTH_LONG);
            errorMessage.show();
        }
        return isValid;
    }

    public static boolean validatePostalCode(EditText postalCodeField) {
        String postalCode = postalCodeField.getText().toString();
        String pattern = "^[a-zA-Z]{1}\\d{1}[a-zA-Z]{1}\\d{1}[a-zA-Z]{1}\\d{1}$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(postalCode);

        return m.matches();
    }

    public static boolean confirmPassword(EditText passwordField, EditText passwordConfirmField) {
        String password = passwordField.getText().toString();
        String passwordConfirm = passwordConfirmField.getText().toString();

        return (password.equals(passwordConfirm));
    }


}
