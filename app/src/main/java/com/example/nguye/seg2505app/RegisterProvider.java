package com.example.nguye.seg2505app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterProvider extends AppCompatActivity{
    MyDBHandler data;

    @Override
    protected void onCreate (Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_provider);
        this.data = new MyDBHandler(this);
    }

    public void onClickRegisterAsProvider(View view){
        EditText nameOfCompany = findViewById(R.id.rp_noc);
        EditText generalDescription = findViewById(R.id.editText2);
        if (!(Validation.isEmpty(nameOfCompany)) && !(Validation.isEmpty(generalDescription))){

            //NEED TO BE CODEDr

            addServiceToDatabase();
        }
    }
    public void addServiceToDatabase(){
        Toast toast = Toast.makeText(getApplicationContext(), "Provider account created!", Toast.LENGTH_LONG);
        toast.show();
        finish();
    }
}
