package com.example.nguye.seg2505app.Utilities;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.ServiceType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores static validation methods that can be reused on any input field
 */
public class Validation extends AppCompatActivity {

    /**
     * Validate an email input field
     * @param inputField, the instance of the EditText
     * @return true if valid
     */
    public static boolean validateEmail(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }

        // If not empty, check if the input is valid
        Toast errorMessage;
        String input = inputField.getText().toString().trim();

        boolean isValid = validEmailString(input) ;
        if (!isValid) {
            errorMessage = Toast.makeText(inputField.getContext(), "You must enter a valid email address.", Toast.LENGTH_LONG);
            errorMessage.show();
            return false;
        }
        return true;
    }

    /**
     * Method that verifies the Email String value validity (Returns boolean value true if valid)
     * Used for testing purpose
     * @param input
     * @return
     */
    public static boolean validEmailString(String input){
        // This regular expression should cover most valid email addresses, just don't go crazy.
        String regex = "^([a-zA-Z0-9\\.!#$%&'*+/=?^_`{|}~-]+)@([a-zA-Z0-9]+\\.[a-zA-Z0-9]+)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        return m.matches();
    }


    // TODO replace this method and availableServiceType() with a more general method that checks if the value already exists
    /**
     *  Check if the email is already used
     * @param inputField, the instance of the EditText
     * @return true if the email is already used
     */
    public static boolean availableEmail(EditText inputField) {
        // Check if the email is already bound to an account
        Toast errorMessage;
        String input = inputField.getText().toString().trim(); // Extract the string from the input field
        MyDBHandler dbHandler = new MyDBHandler(inputField.getContext()); // Instantiate MyDBHandler
        Account currentAccount = Account.getCurrentAccount(); // This is used to avoid an error when keeping the same email in the ModifyScreen
        String currentEmail = currentAccount.getEmail();
//        if((dbHandler.findAccount(input) != null) && (!currentEmail.equals(input))) {
        if((new Account().find(inputField.getContext(), Account.COL_EMAIL, input, true) != null) && (!currentEmail.equals(input))) {
            errorMessage = Toast.makeText(inputField.getContext(), "An account already exists with this email address.", Toast.LENGTH_LONG);
            errorMessage.show();
            return false;
        }
        return true;
    }

    /**
     *  Check if the serviceType already exists
     * @param inputField, the instance of the EditText
     * @param currentServiceType,
     * @return true if the serviceType already exists
     */
    public static boolean availableServiceType(EditText inputField, String currentServiceType) {
        // Check if the serviceType already exists
        Toast errorMessage;
        String input = inputField.getText().toString().trim(); // Extract the string from the input field
        MyDBHandler dbHandler = new MyDBHandler(inputField.getContext()); // Instantiate MyDBHandler
//        Account currentAccount = CurrentAccount.getCurrentAccount(); // This is used to avoid an error when keeping the same email in the ModifyScreen
//        String currentEmail = currentAccount.getEmail();
//        if((dbHandler.findServiceType(input) != null) && (!currentServiceType.equals(input))) {
        if((new ServiceType().find(inputField.getContext(), ServiceType.COL_NAME, input, true) != null) && (!currentServiceType.equals(input))) {
            errorMessage = Toast.makeText(inputField.getContext(), "This service type already exists.", Toast.LENGTH_LONG);
            errorMessage.show();
            return false;
        }
        return true;
    }

    /**
     * Validate a password input field
     * @param inputField, the instance of the EditText
     * @return true if valid
     */
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

    /**
     * Validate any proper noun (ex. city, first name, last name, street, etc.)
     * @param inputField, the instance of the EditText
     * @return true if valid
     */
    public static boolean validateName(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }

        // If not empty, check if the input is valid
        String input = inputField.getText().toString().trim();
        // This regular expression limits the special characters that can be used
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

    /**
     * Validate a postal code input field
     * @param inputField, the instance of the EditText
     * @return true if valid
     */
    public static boolean validatePostalCode(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }

        // If not empty, check if the input is valid
        String input = inputField.getText().toString().trim();
        // Check if the input is in the format A1A1A1
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

    /**
     * Validate a phone number input field
     * @param inputField, the instance of the EditText
     * @return true if valid
     */
    public static boolean validatePhoneNumber(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }

        // If not empty, check if the input is valid
        String input = inputField.getText().toString().trim();
        // Check if the phone number contains 10 digits
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
     * Validate a date input field by making sure that the date is not in the past
     * @param inputField, the instance of the EditText
     * @return true if valid
     */
    public static boolean validateDate(EditText inputField) {
        // First, check if the field is empty
        if (isEmpty(inputField)) {
            return false;
        }
        // If not empty, check if the input is valid
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(date);
        String input = inputField.getText().toString().trim();

        boolean isValid = FormatValue.dateToLong(input) >= FormatValue.dateToLong(today);
        if (!isValid) {
            Toast errorMessage = Toast.makeText(inputField.getContext(), "You can't select a date in the past.", Toast.LENGTH_LONG);
            errorMessage.show();
        }
        return isValid;
    }

    /**
     * Checks if the blank default option of the drop-down list is still selected
     * @param dropDown The Spinner object that contains the options
     * @param selectedOption The selected option
     * @return True if the selected option is not equal to ""
     */
    public static boolean validateDropDown(Spinner dropDown, String selectedOption) {
        boolean isValid = !selectedOption.equals("");
        if (!isValid) {
            Toast errorMessage = Toast.makeText(dropDown.getContext(), "You must select an option from the drop-down list.", Toast.LENGTH_LONG);
            errorMessage.show();
        }
        return isValid;
    }

    /**
     * Check if the two fields correspond
     * @param inputField, the instance of the first EditText
     * @param inputConfirmField, the instance of the second EditText
     * @param inputDescription, input type used for the error message.
     * @return true if both fields match
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


    // TODO add validation for time that come in pair.
    // startTime <= endTime

    /**
     * Checks if the input field is empty.
     * @param inputField,  the instance of the EditText
     * @return true if the EditText field is empty
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
     * Recursively gets all the EditText descendants of the specified layout.
     * @param layout, the layout containing the fields to validate
     * @return a List of all the EditText descendants of the layout
     */
    public static ArrayList<EditText> getAllEditTexts(ViewGroup layout) {

        ArrayList<EditText> subEditTextList = new ArrayList<EditText>(); // Initialize a list

        int childCount = layout.getChildCount(); // get the number of children of the layout
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) { // for each child...
                View child = layout.getChildAt(i);
                if (child instanceof EditText) { // ...if the child is an instance of EditText,
                    subEditTextList.add((EditText) child); // add it to the list
                } else if (child instanceof ViewGroup) { // ...if the child is a ViewGroup,
                    subEditTextList.addAll(getAllEditTexts((ViewGroup) child)); // call the method recursively on that ViewGroup
                }
            }
        }

        ArrayList<EditText> fullEditTextList = new ArrayList<EditText>();
        fullEditTextList.addAll(subEditTextList); // Add all elements from the sub-list to the full list
        return fullEditTextList; // return the full list
    }

    /**
     * Goes through all EditText fields in the specified layout and validates them.
     * Confirmation validation (ex. for email and password) must be done separately.
     * To be validated with more than default validation, each field must have the appropriate input type assigned to it.
     * @param layout, the layout containing the fields to validate
     * @return false as soon as any validation fails, returns true if all validations are successful
     */
    public static boolean validateAll(ViewGroup layout) {
        // Puts all the EditText components in a list using a recursive function
        // This allows to get more than just the direct descendants of the layout
        ArrayList<EditText> editTextList = getAllEditTexts(layout);

        // Validate each field in the list, applying a different validation based on its type
        for (EditText field : editTextList) {
            // See https://developer.android.com/reference/android/text/InputType for input types
            // For some reason, many inputType values differed by 1
            //  (ex. 33 for textEmailAddress even though it says 32 on the website).
            int inputType = field.getInputType();
//            System.out.println(inputType); // Used to find the actual inputType values.
            System.out.print(inputType);
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
                    // TODO add the case for date
                    // Cannot select a past date
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
}