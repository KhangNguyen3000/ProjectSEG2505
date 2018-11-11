package com.example.nguye.seg2505app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyDBHandler dbHandler = new MyDBHandler(this);
        if(!(dbHandler.existsType(1))){
            dbHandler.createAdmin();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Called when the signup button is clicked.
     * Opens the RegisterFinal activity.
     * @param view
     */
    public void onClickSignupButton(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterFinal.class);
        startActivity(intent);
    }

    /**
     * Called when the login button is clicked.
     * Opens the WelcomeScreen activity.
     * @param view
     */
    public void onClickLoginButton(View view) {
        // Basic validation on the fields
        ViewGroup layout = findViewById(R.id.main_layout_root);
        if (Validation.validateAll(layout)) {

            MyDBHandler dbHandler = new MyDBHandler(this);
            String email = (((EditText) findViewById(R.id.emailText)).getText().toString());
            String password = (((EditText) findViewById(R.id.passwordText)).getText().toString());
            String dbPassword;
            // Search for the account with the provided email
            Account account = dbHandler.findAccount(email);
            if (account != null) {
                // if the account is found, check if the password is correct
                dbPassword = account.getPassword();
                if (dbPassword.equals(password)) {
                    // store the account information in CurrentAccount
                    CurrentAccount.setCurrentAccount(account);
                    Toast toast = Toast.makeText(getApplicationContext(), "Logging in!", Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Wrong password...", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "User does not exist...", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}


