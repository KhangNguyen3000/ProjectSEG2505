package com.example.nguye.seg2505app.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.Validation;

public class ModifyScreen extends AppCompatActivity{


    String firstName, lastName;
    String email;
    String pass;
    String phoneNumber;
    int streetNumber;
    String street, city, province, country, postalC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);

        // Set the content of each field to the values of the currentAccount
        Account currentAccount = Account.getCurrentAccount();

        TextView tvFirstName = (TextView)findViewById(R.id.mod_input_firstName);
        tvFirstName.setText(currentAccount.getFirstName());

        TextView tvLastName = (TextView)findViewById(R.id.mod_input_lastName);
        tvLastName.setText(currentAccount.getLastName());

        TextView tvEmail = (TextView)findViewById(R.id.mod_input_email);
        tvEmail.setText(currentAccount.getEmail());

        TextView tvPassWord = (TextView)findViewById(R.id.mod_input_password);

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

        TextView tvPhoneNumber = (TextView)findViewById(R.id.mod_input_phone);
        tvPhoneNumber.setText(currentAccount.getPhoneNumber());
    }

    /**
     * Update the account information in the database
     */
    public void updateUserInDatabase(){
        // set the attributes of the currentAccount with the values entered in the fields
        Account currentAccount = Account.getCurrentAccount();
        currentAccount.setFirstName(firstName);
        currentAccount.setLastName(lastName);
        currentAccount.setEmail(email);
        if(!(pass.equals(""))) {
            currentAccount.setPassword(Hashing.hash(pass));
        }
        currentAccount.setStreetNumber(streetNumber);
        currentAccount.setStreetName(street);
        currentAccount.setCity(city);
        currentAccount.setProvince(province);
        currentAccount.setCountry(country);
        currentAccount.setPostalCode(postalC);
        currentAccount.setPhoneNumber(phoneNumber);
        currentAccount.setID(Account.getCurrentAccount().getID());


        // Update the database
        currentAccount.update(this);
        Toast toast = Toast.makeText(getApplicationContext(), "Account modified!", Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Store the informations entered in the fields in variables
     */
    public void storeInfo(){
        firstName = ((EditText) findViewById(R.id.mod_input_firstName)).getText().toString();
        lastName = ((EditText) findViewById(R.id.mod_input_lastName)).getText().toString();
        email = ((EditText) findViewById(R.id.mod_input_email)).getText().toString();
        pass= ((EditText) findViewById(R.id.mod_input_password)).getText().toString();
        streetNumber = Integer.parseInt(((EditText) findViewById(R.id.mod_input_streetNumber)).getText().toString());
        street = ((EditText) findViewById(R.id.mod_input_streetName)).getText().toString();
        city = ((EditText) findViewById(R.id.mod_input_city)).getText().toString();
        province = ((EditText) findViewById(R.id.mod_input_province)).getText().toString();
        country = ((EditText) findViewById(R.id.mod_input_country)).getText().toString();
        phoneNumber = ((EditText) findViewById(R.id.mod_input_phone)).getText().toString();
        postalC = ((EditText) findViewById(R.id.mod_input_postalCode)).getText().toString();
    }

    /**
     * Called when clicking on the modify button.
     * Validate every field and update the database if the validation is successful.
     * @param view
     */
    public void onClickModify(View view){
        ViewGroup layout = findViewById(R.id.mod_layout_root);
        EditText email = findViewById(R.id.mod_input_email);
        if (Validation.validateAll(layout)
                && Validation.availableEmail(email)) {
            storeInfo();
            updateUserInDatabase();
            finish();
        }
    }
}
