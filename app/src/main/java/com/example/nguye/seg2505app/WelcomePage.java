package com.example.nguye.seg2505app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class WelcomePage extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        MyDBHandler a = new MyDBHandler(this);

        List<String> users = a.getUsers();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users.toArray(new String[users.size()]));
        //android.R.id.text1
        ListView listView = (ListView) findViewById(R.id._ListViewUsers);
        listView.setAdapter(itemsAdapter);

        Account currentAccount = CurrentAccount.getCurrentAccount();

        TextView tvFullName = (TextView)findViewById(R.id.textView2);
        TextView tvAccountType = (TextView)findViewById(R.id.textView);

        tvFullName.setText("Welcome " + currentAccount.getFirstName() + " " + currentAccount.getLastName());

        String accType;
        if(currentAccount.getType() == 1) {
            accType = "Administrator";
        } else if (currentAccount.getType() == 2) {
            accType = "Provider";
        } else {
            accType = "Client";
        }
        tvAccountType.setText("You are now logged in as " + accType);
    }




    public void onClickMyProfileButton(View view){
        Intent intent = new Intent(getApplicationContext(), MyProfile.class);
        startActivityForResult(intent, 0);
    }
    public void onClickLogOutButton(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }
}
