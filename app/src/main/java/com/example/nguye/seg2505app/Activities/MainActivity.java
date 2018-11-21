package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguye.seg2505app.Activities.RegisterFinal;
import com.example.nguye.seg2505app.Activities.WelcomePage;
import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.RegisterProvider;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.Validation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        MyDBHandler dbHandler = new MyDBHandler(this);
        if(!(dbHandler.existsType(1))){
            dbHandler.createAdmin(this);
        }
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
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
//        ViewGroup layout = findViewById(R.id.main_layout_root);
        ViewGroup layout = findViewById(R.id.content_main);
        if (Validation.validateAll(layout)) {

            MyDBHandler dbHandler = new MyDBHandler(this);
            String email = (((EditText) findViewById(R.id.emailText)).getText().toString());
            String password = (((EditText) findViewById(R.id.passwordText)).getText().toString());
            String dbPassword;
            // Search for the account with the provided email
//            Account account = dbHandler.findAccount(email);
            Account account = new Account().find(this, Account.COL_EMAIL, email, true);
            if (account != null) {
                // if the account is found, check if the password is correct
                dbPassword = account.getPassword();
                System.out.println(dbPassword);
                if (dbPassword.equals(password)) {
                    // store the account information in CurrentAccount
                    Account.setCurrentAccount(account);
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


