package com.example.nguye.seg2505app.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update the account information
        Account currentAccount = Account.getCurrentAccount();
        TextView tvFullName = (TextView)findViewById(R.id.mp_txt_fullNameDisp);
        TextView tvAddress = (TextView)findViewById(R.id.mp_txt_addressDisp);
        TextView tvPhoneNumber = (TextView)findViewById(R.id.mp_txt_phoneNumberDisp);
        TextView tvEmail = (TextView)findViewById(R.id.mp_txt_emailDisp);
        TextView tvPassword = (TextView)findViewById(R.id.mp_txt_passwordDisp);
        tvFullName.setText(currentAccount.getFirstName() + " " + currentAccount.getLastName());
        tvAddress.setText(currentAccount.getStreetNumber() + " " + currentAccount.getStreetName()
                + ", " + currentAccount.getCity() + ", " + currentAccount.getProvince()
                + ", " + currentAccount.getCountry() + " " + currentAccount.getPostalCode());
        tvPhoneNumber.setText(currentAccount.getPhoneNumber());
        tvEmail.setText(currentAccount.getEmail());
        tvPassword.setText(currentAccount.getPassword());
    }

    /**
     * Open the ModifyScreen activity
     * @param view
     */
    public void onClickModifyButton(View view){
        Intent intent = new Intent(getApplicationContext(), ModifyScreen.class);
        startActivity(intent);
    }

    /**
     * Asks the user if he really want to delete the account.
     * Do so if yes, nothing if no.
     * @param view
     */
    public void onClickDelete(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (Account.getCurrentAccount().getType() != 1) {
            // Create the alert dialog
            builder.setCancelable(true);
            builder.setTitle("Warning!");
            builder.setMessage("You are about to delete this account permanently. Do you wish to continue?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //                MyDBHandler dbHandler = new MyDBHandler(getApplicationContext());
                    //                String currentEmail = CurrentAccount.getCurrentAccount().getEmail();
                    //                dbHandler.deleteAccount(currentEmail);
                    Account.getCurrentAccount().delete(getApplicationContext());
                    // The result code 9999 indicates that the account has been deleted
                    setResult(9999, null);
                    finish();

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            });
        } else {
            builder.setTitle("Nope");
            builder.setMessage("You cannot delete the administrator account.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
