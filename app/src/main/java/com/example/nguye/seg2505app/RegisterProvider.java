package com.example.nguye.seg2505app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.Validation;

public class RegisterProvider extends AppCompatActivity{
    MyDBHandler data;

    @Override
    protected void onCreate (Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_provider);
        this.data = new MyDBHandler(this);
    }


    public void onClickRegisterAsProvider(View view) {
        EditText nameOfCompany = findViewById(R.id.rp_noc);
        EditText generalDescription = findViewById(R.id.editText2);
        if (!(Validation.isEmpty(nameOfCompany)) && !(Validation.isEmpty(generalDescription))) {
            addServiceToDatabase();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void addServiceToDatabase() {
        Account account = (Account) getIntent().getSerializableExtra("current_account");
        account.add(this);
        System.out.println(account.getEmail());
        Toast toast = Toast.makeText(getApplicationContext(), "Provider account created!", Toast.LENGTH_LONG);
        toast.show();
        setResult(9999, null);
        finish();
    }

    
//
//    public void addServiceToDatabase(){
//        Toast toast = Toast.makeText(getApplicationContext(), "Provider account created!", Toast.LENGTH_LONG);
//        toast.show();
//        finish();
//    }
}
