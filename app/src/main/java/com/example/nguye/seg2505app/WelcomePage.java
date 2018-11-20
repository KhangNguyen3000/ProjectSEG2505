package com.example.nguye.seg2505app;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class WelcomePage extends AppCompatActivity{

    public static int ACCOUNT_DELETED = 9999;
    MyDBHandler data;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.data = new MyDBHandler(this);

        Account currentAccount = Account.getCurrentAccount();

        TextView tvFullName = (TextView)findViewById(R.id.textView2);
        TextView tvAccountType = (TextView)findViewById(R.id.textView);

        String accType = AccountType(currentAccount);
        tvFullName.setText("Welcome " + currentAccount.getFirstName() + " " + currentAccount.getLastName());
        tvAccountType.setText("You are now logged in as " + accType);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Finish the activity and go back to the login screen if the account has been deleted from the MyProfile screen.
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ACCOUNT_DELETED) {
            finish();
        }
    }

    /**
     * Display The list of Users
     * @param users
     */
    public void UserList( List<String> users){
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users.toArray(new String[users.size()]));
        ListView listView = (ListView) findViewById(R.id._ListViewUsers);
        listView.setAdapter(itemsAdapter);
    }

    /**
     * Find the account type and display or not the components that should be visible to the current account
     * @param currentAccount
     * @return
     */
    public String AccountType (Account currentAccount) {
        String accType;
        if(currentAccount.getType() == 1) {
            accType = "Administrator";
            List<String> users = data.getList("Email","Accounts");
            UserList(users); //display the list of users
            Button serviceManagement = findViewById(R.id.wel_manageservice_button);
            serviceManagement.setVisibility(View.VISIBLE); //display the button "Manage Service"
            return accType;
        } else if (currentAccount.getType() == 2) {
            accType = "Provider";
            Button serviceManagement = findViewById(R.id.wel_manageservice_button);
            serviceManagement.setVisibility(View.VISIBLE); //display the button "Manage Service"
        } else {
            accType ="Client";
        }
        return accType;
    }

    /**
     * Launch the ServiceManagement activity
     * @param view
     */
    public void onClickManageServiceButton(View view) {
        Account currentAccount = CurrentAccount.getCurrentAccount();
        if(currentAccount.getType() == 1) {
            Intent intent = new Intent(getApplicationContext(), ServiceManagement.class);
            startActivity(intent);
        }
        if(currentAccount.getType() == 2) {
            Intent intent = new Intent(getApplicationContext(), ServiceManagementProvider.class);
            startActivity(intent);
        }
    }

    /**
     * Launch the MyProfile activity
     * @param view
     */
    public void onClickMyProfileButton(View view){ //go to the activity_my_profile
        Intent intent = new Intent(getApplicationContext(), MyProfile.class);
        startActivityForResult(intent, ACCOUNT_DELETED);
    }

    /**
     * Go back to the login screen after confirmation
     * @param view
     */
    public void onClickLogOutButton(View view){ //go ot the activity_main
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Logging out");
        builder.setMessage("Are you sure that you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


