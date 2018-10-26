package com.example.nguye.seg2505app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
    }

    public void onClickModify(View view){
        Intent intent = new Intent(getApplicationContext(), ModifyScreen.class);
        startActivityForResult(intent, 0);
    }

    public void onClickDelete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setCancelable(true);
        builder.setTitle("Title");
        builder.setMessage("You are about to delete this account permanently. Do you wish to continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDBHandler dbHandler = new MyDBHandler(this);
                String currentEmail = CurrentAccount.account.getEmail();
                dbHandler.deleteAccount(currentEmail);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
