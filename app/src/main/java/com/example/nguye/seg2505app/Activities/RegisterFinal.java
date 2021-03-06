package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.Validation;

public class RegisterFinal extends AppCompatActivity {

    public static int PROVIDER_CREATED = 9999;

    String firstName, lastName;
    String email, cEmail;
    String pass, cPass;
    String phoneNumber;
    int streetNumber;
    String street, city, province, country, postalC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_final);

        // Spinner (drop-down) creation
        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        ArrayAdapter<CharSequence> typeUserAdapter = ArrayAdapter.createFromResource(this, R.array.account_type_spinner, android.R.layout.simple_spinner_item);
        typeUserAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeUserSpinner.setAdapter(typeUserAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Finish the activity and go back to the login screen if the account has been deleted from the MyProfile screen.
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == PROVIDER_CREATED) {
            finish();
        }
    }

    // This function is used to store all the values that we need to register a new user
    public void storeInfo(){
        firstName = ((EditText) findViewById(R.id.reg_input_firstName)).getText().toString();
        lastName = ((EditText) findViewById(R.id.reg_input_lastName)).getText().toString();
        email = ((EditText) findViewById(R.id.reg_input_email)).getText().toString();
        cEmail = ((EditText) findViewById(R.id.reg_input_emailConfirm)).getText().toString();
        pass= ((EditText) findViewById(R.id.reg_input_password)).getText().toString();
        cPass= ((EditText) findViewById(R.id.reg_input_passwordConfirm)).getText().toString();
        streetNumber = Integer.parseInt(((EditText) findViewById(R.id.reg_input_streetNumber)).getText().toString());
        street = ((EditText) findViewById(R.id.reg_input_streetName)).getText().toString();
        city = ((EditText) findViewById(R.id.reg_input_city)).getText().toString();
        province = ((EditText) findViewById(R.id.reg_input_province)).getText().toString();
        country = ((EditText) findViewById(R.id.reg_input_country)).getText().toString();
        phoneNumber = ((EditText) findViewById(R.id.reg_input_phone)).getText().toString();
        postalC = ((EditText) findViewById(R.id.reg_input_postalCode)).getText().toString();
    }

    /**
     * This function is used when we click on the "Register" button. It verifies that every field is correctly filled.
     * @param view
     */
    public void onClickRegister(View view){

        ViewGroup layout = findViewById(R.id.reg_layout_root);
        EditText email = findViewById(R.id.reg_input_email);
        EditText emailConf = findViewById(R.id.reg_input_emailConfirm);
        EditText password = findViewById(R.id.reg_input_password);
        EditText passwordConf = findViewById(R.id.reg_input_passwordConfirm);
        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        String typeOption = typeUserSpinner.getSelectedItem().toString();

        if (Validation.validateAll(layout) // Perform validation on all fields
                && Validation.availableEmail(email) // Check if the email is already taken
                && Validation.confirmField(email, emailConf, "Email") // Confirm the email
                && Validation.confirmField(password, passwordConf, "Password")
                && Validation.validateDropDown(typeUserSpinner, typeOption)) { // Confirm the password
            storeInfo();
            addUserToDatabase();
        }
    }

    /**
     * This function adds the new user to the Database
     */
    public void addUserToDatabase(){
//        MyDBHandler dbHandler = new MyDBHandler(this);
        Account account = new Account();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.setPassword(Hashing.hash(pass));
        account.setStreetNumber(streetNumber);
        account.setStreetName(street);
        account.setCity(city);
        account.setProvince(province);
        account.setCountry(country);
        account.setPostalCode(postalC);
        account.setPhoneNumber(phoneNumber);

        int accType = 0;
        Spinner typeUserSpinner = (Spinner) findViewById(R.id.reg_dd_accountType);
        String typeOption = typeUserSpinner.getSelectedItem().toString();

        if (typeOption.equals("Client")) {
            accType = 3;
            account.setType(accType);
            account.add(this);
            Toast toast = Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_LONG);
            toast.show();
            finish();
        }
        else if (typeOption.equals("Provider")) {
            accType = 2;
            account.setType(accType);
            Intent intent = new Intent(getApplicationContext(), RegisterProvider.class);
            intent.putExtra("current_account", account);
            startActivityForResult(intent, PROVIDER_CREATED);
        }
    }
}
