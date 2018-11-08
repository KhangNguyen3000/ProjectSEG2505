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
    MyDBHandler a;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.a = new MyDBHandler(this);


        Account currentAccount = CurrentAccount.getCurrentAccount();


        TextView tvFullName = (TextView)findViewById(R.id.textView2);
        TextView tvAccountType = (TextView)findViewById(R.id.textView);

        String accType = AccountType(currentAccount);
        tvFullName.setText("Welcome " + currentAccount.getFirstName() + " " + currentAccount.getLastName());
        tvAccountType.setText("You are now logged in as " + accType);
    }

    //Display The list of Users;
    public void UserList( List<String> users){
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users.toArray(new String[users.size()]));
        ListView listView = (ListView) findViewById(R.id._ListViewUsers);
        listView.setAdapter(itemsAdapter);
    }

    public String AccountType (Account currentAccount) {

        String accType;
        if(currentAccount.getType() == 1) {
            accType = "Administrator";
            List<String> users = a.getUsers();
            UserList(users);
        } else if (currentAccount.getType() == 2) {
            accType = "Provider";
        } else {
            accType = Integer.toString(currentAccount.getType());
        }
        return accType;
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


