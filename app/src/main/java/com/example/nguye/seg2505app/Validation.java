package com.example.nguye.seg2505app;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation extends AppCompatActivity {


    public static boolean validateEmail(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }

        // If not empty, check if the input is valid
        // This regular expression should cover most valid email addresses, just don't go crazy.
        Toast errorMessage;
        String input = inputField.getText().toString().trim();


        boolean isValid = validEmailString(input) ;
        if (!isValid) {
            errorMessage = Toast.makeText(inputField.getContext(), "You must enter a valid email address.", Toast.LENGTH_LONG);
            errorMessage.show();
            return false;
        }

//        // Check if the email is already bound to an account
//        MyDBHandler dbHandler = new MyDBHandler(inputField.getContext());
//        Account currentAccount = CurrentAccount.getCurrentAccount();
//        String currentEmail = currentAccount.getEmail();
//        if((dbHandler.findAccount(input) != null) && (!currentEmail.equals(input))) {
//            errorMessage = Toast.makeText(inputField.getContext(), "An account already exists with this email address.", Toast.LENGTH_LONG);
//            errorMessage.show();
//            return false;
//        }

        return true;
    }

    public static boolean availableEmail(EditText inputField) {
        // Check if the email is already bound to an account
        Toast errorMessage;
        String input = inputField.getText().toString().trim();
        MyDBHandler dbHandler = new MyDBHandler(inputField.getContext());
        Account currentAccount = CurrentAccount.getCurrentAccount();
        String currentEmail = currentAccount.getEmail();
        if((dbHandler.findAccount(input) != null) && (!currentEmail.equals(input))) {
            errorMessage = Toast.makeText(inputField.getContext(), "An account already exists with this email address.", Toast.LENGTH_LONG);
            errorMessage.show();
            return false;
        }
        return true;
    }

//    public static boolean confirmEmail(EditText emailField, EditText emailConfirmField) {
//        String email = emailField.getText().toString();
//        String emailConfirm = emailConfirmField.getText().toString();
//
//        boolean isValid = email.equals(emailConfirm);
//        if (!isValid) {
//            Toast errorMessage = Toast.makeText(emailField.getContext(), "The email address does not match.", Toast.LENGTH_LONG);
//            errorMessage.show();
//        }
//        return isValid;
//    }

    //Method that verifies the Email String value validity (Returns boolean value true if valid)
    public static boolean validEmailString(String input){
        String regex = "^([a-zA-Z0-9\\.!#$%&'*+/=?^_`{|}~-]+)@([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.matches();
    }


    public static boolean validatePassword(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }

        // If not empty, check if the input is valid
        String input = inputField.getText().toString();
        if (input.length() < 6 || input.length() > 20) {
            Toast errorMessage = Toast.makeText(inputField.getContext(), "Please use a password between 6 and 20 characters.", Toast.LENGTH_LONG);
            errorMessage.show();
            return false;
        }
        return true;
    }

//    public static boolean confirmPassword(EditText passwordField, EditText passwordConfirmField) {
//        String password = passwordField.getText().toString();
//        String passwordConfirm = passwordConfirmField.getText().toString();
//
//        boolean isValid = password.equals(passwordConfirm);
//        if (!isValid) {
//            Toast errorMessage = Toast.makeText(passwordField.getContext(), "The password does not match.", Toast.LENGTH_LONG);
//            errorMessage.show();
//        }
//        return isValid;
//    }


    /**
     * Validate any proper noun (ex. city, first name, last name, street, etc.)
     * @param inputField
     * @return
     */
    public static boolean validateName(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }

        // If not empty, check if the input is valid
        String input = inputField.getText().toString().trim();
        String regex = "^[a-zA-Z]+((['. -][a-zA-Z ])?[a-zA-Z]*)*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        boolean isValid = m.matches();
        if (!isValid) {
            Toast errorMessage = Toast.makeText(inputField.getContext(), "Invalid input in the '" + inputField.getContentDescription() + "' field.", Toast.LENGTH_LONG);
            errorMessage.show();
        }
        return isValid;
    }

    public static boolean validatePostalCode(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }

        // If not empty, check if the input is valid
        String input = inputField.getText().toString().trim();
        String regex = "^[a-zA-Z]{1}\\d{1}[a-zA-Z]{1}\\d{1}[a-zA-Z]{1}\\d{1}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        boolean isValid = m.matches();
        if (!isValid) {
            Toast errorMessage = Toast.makeText(inputField.getContext(), "You must enter a valid postal code.", Toast.LENGTH_LONG);
            errorMessage.show();
        }
        return isValid;
    }

    public static boolean validatePhoneNumber(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }

        // If not empty, check if the input is valid
        String input = inputField.getText().toString().trim();
        String regex = "^\\d{10}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        boolean isValid = m.matches();
        if (!isValid) {
            Toast errorMessage = Toast.makeText(inputField.getContext(), "Your phone number should contain 10 digits without any symboles.", Toast.LENGTH_LONG);
            errorMessage.show();
        }
        return isValid;
    }

    /**
     *
     * @param inputField
     * @param inputConfirmField
     * @param inputDescription
     * @return
     */
    public static boolean confirmField(EditText inputField, EditText inputConfirmField, String inputDescription) {
        String input = inputField.getText().toString();
        String inputConfirm = inputConfirmField.getText().toString();

        boolean isValid = input.equals(inputConfirm);
        if (!isValid) {
            Toast errorMessage = Toast.makeText(inputField.getContext(), "The " + inputDescription + " does not match.", Toast.LENGTH_LONG);
            errorMessage.show();
        }
        return isValid;
    }

    /**
     * Checks if the input field is empty.
     * @param inputField
     * @return
     */
    public static boolean isEmpty(EditText inputField) {
        if (inputField.getText().toString().trim().length() == 0) {
            Toast errorMessage = Toast.makeText(inputField.getContext(), "You must fill the '" + inputField.getContentDescription() + "' field.", Toast.LENGTH_LONG);
            errorMessage.show();
            return true;
        }
        return false;
    }

    /**
     * Goes through all EditText fields in the specified layout and validates them.
     * Confirmation validation (ex. for email and password) must be done separately.
     * To be validated, each field must have the appropriate input type assigned to it.
     * @param layout
     * @return false as soon as any validation fails, returns true if all validations are successful
     */
    public static boolean validateAll(ViewGroup layout) {
        // Puts all the EditText components in a list
        // Inspired from https://stackoverflow.com/questions/7790487/method-to-get-all-edittexts-in-a-view
        ArrayList<EditText> editTextList = getAllEditTexts(layout);
//        for (int i = 0; i < layout.getChildCount(); i++) {
//            if (layout.getChildAt(i) instanceof EditText) {
//                editTextList.add((EditText) layout.getChildAt(i)); // Adds all EditText components in a list
//            }
//        }
        // Validate each field in the list, applying a different validation based on its type
        for (EditText field : editTextList) {
            // See https://developer.android.com/reference/android/text/InputType for input types
            // For some reason, many inputType values differed by 1
            //  (ex. 33 for textEmailAddress even though it says 32 on the website).
            int inputType = field.getInputType();
//            System.out.println(inputType); // Used to find the actual inputType values.
            switch(inputType) {
                case 33: // inputType == textEmailAddress
                    if(!validateEmail(field)) {
                        return false;
                    }
                    break;
                case 129: // inputType == textPassword
                    if(!validatePassword(field)) {
                        return false;
                    }
                    break;
                case 97: // inputType == textPersonName
                    if(!validateName(field)) {
                        return false;
                    }
                    break;
                case 113: // inputType == textPostalAddress
                    if(!validatePostalCode(field)) {
                        return false;
                    }
                    break;
                case 3: // inputType == phone
                    if(!validatePhoneNumber(field)) {
                        return false;
                    }
                    break;
                case 131073: // inputType == none
                    // Don't do anything and apply custom validation outside of this function.
                    // Keep this input type for validation of inputs that don't belong to another category
                    break;
                default: // some generic validation
                    if (isEmpty(field)) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    /**
     * Recursively gets all the EditText descendants of the specified layout.
     * @param layout
     * @return
     */
    public static ArrayList<EditText> getAllEditTexts(ViewGroup layout) {

        ArrayList<EditText> subEditTextList = new ArrayList<EditText>();

        int childCount = layout.getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View child = layout.getChildAt(i);
                if (child instanceof EditText) {
                    subEditTextList.add((EditText) child);
                } else if (child instanceof ViewGroup) {
                    subEditTextList.addAll(getAllEditTexts((ViewGroup) child));
                }
            }
        }

        ArrayList<EditText> fullEditTextList = new ArrayList<EditText>();
        fullEditTextList.addAll(subEditTextList);

        return fullEditTextList;
    }

}