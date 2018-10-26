package com.example.nguye.seg2505app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Account currentAccount = CurrentAccount.getCurrentAccount();
        TextView tvFullName = (TextView)findViewById(R.id.mp_txt_fullNameDisp);
        TextView tvAddress = (TextView)findViewById(R.id.mp_txt_addressDisp);
        TextView tvPhoneNumber = (TextView)findViewById(R.id.mp_txt_phoneNumberDisp);
        TextView tvEmail = (TextView)findViewById(R.id.mp_txt_emailDisp);
        TextView tvPassword = (TextView)findViewById(R.id.mp_txt_passwordDisp);

        tvFullName.setText(currentAccount.getFirstName() + " " + currentAccount.getLastName());
        tvAddress.setText(currentAccount.getStreetNumber() + " " + currentAccount.getStreetName()
            + ", " + currentAccount.getCity() + ", " + currentAccount.getProvince()
            + ", " + currentAccount.getCountry() + " " + currentAccount.getPostalCode());
        tvPhoneNumber.setText(Long.toString(currentAccount.getPhoneNumber()));
        tvEmail.setText(currentAccount.getEmail());
        tvPassword.setText(currentAccount.getPassword());
    }

    public void onClickModifyButton(View view){
        Intent intent = new Intent(getApplicationContext(), ModifyScreen.class);
        startActivityForResult(intent, 0);
    }

    public void onClickDelete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Warning!");
        builder.setMessage("You are about to delete this account permanently. Do you wish to continue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDBHandler dbHandler = new MyDBHandler(getApplicationContext());
                String currentEmail = CurrentAccount.getCurrentAccount().getEmail();
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
