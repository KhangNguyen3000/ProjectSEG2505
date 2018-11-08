package com.example.nguye.seg2505app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//        final MyDBHandler dbHandler = new MyDBHandler(this);
//        final Button button = findViewById(R.id.loginButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                String email = (((EditText) findViewById(R.id.emailText)).getText().toString());
//                String password = (((EditText) findViewById(R.id.passwordText)).getText().toString());
//                Account account = dbHandler.findAccount(email);
//                if(account != null) {
//                    Toast toast = Toast.makeText(getApplicationContext(), "User found!", Toast.LENGTH_LONG);
//                    toast.show();
//                } else {
//                    Toast toast = Toast.makeText(getApplicationContext(), "User does not exist...", Toast.LENGTH_LONG);
//                    toast.show();
//                }
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickSignupButton(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterFinal.class);
        startActivityForResult(intent, 0);
    }

    public void onClickLoginButton(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this);
        String email = (((EditText) findViewById(R.id.emailText)).getText().toString());
        String password = (((EditText) findViewById(R.id.passwordText)).getText().toString());
        Account account = dbHandler.findAccount(email);
        String dbPassword;
        if(account != null) {
            dbPassword = account.getPassword();
            if(dbPassword.equals(password)) {
                CurrentAccount.setCurrentAccount(account);
                Toast toast = Toast.makeText(getApplicationContext(), "Logging in!", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                startActivityForResult(intent, 0);
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


