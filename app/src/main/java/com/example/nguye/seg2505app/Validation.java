package com.example.nguye.seg2505app;

import android.widget.EditText;

public class Validation {

    public static boolean validateEmail(EditText emailField, EditText emailConfirmationField) {
        return true;
    }

    public static boolean validateName(EditText nameField) {
        String name = nameField.getText().toString();
        return true;
    }

    public static boolean validatePostalCode(EditText postalCodeField) {
        return true;
    }

}
