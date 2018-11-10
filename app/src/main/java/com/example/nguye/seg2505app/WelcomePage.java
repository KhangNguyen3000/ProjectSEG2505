package com.example.nguye.seg2505app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
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

    public String AccountType (Account currentAccount) { //select the account type and display or not the buttons that needs the current account

        String accType;
        if(currentAccount.getType() == 1) {
            accType = "Administrator";
            List<String> users = a.getList("Email","Accounts");
            UserList(users); //display the list of users
            Button serviceManagement = findViewById(R.id.wel_manageservice_button);
            serviceManagement.setVisibility(View.VISIBLE); //display the button "Manage Service"
            return accType;
        } else if (currentAccount.getType() == 2) {
            accType = "Provider";
        } else {
            accType ="Client";
        }
        return accType;
    }


    public void onClickManageServiceButton(View view){ //go to the activity_service_management
        Intent intent = new Intent(getApplicationContext(), ServiceManagement.class);
        startActivityForResult(intent, 0);
    }

    public void onClickMyProfileButton(View view){ //go to the activity_my_profile
        Intent intent = new Intent(getApplicationContext(), MyProfile.class);
        startActivityForResult(intent, 0);
    }
    public void onClickLogOutButton(View view){ //go ot the activity_main
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }
}


