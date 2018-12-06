package com.example.nguye.seg2505app.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.Validation;

public class ModifyCompany extends AppCompatActivity {
    MyDBHandler data;
    EditText companyName;
    EditText description;
    Switch aSwitch;
    Account currentAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_provider);
        this.data = new MyDBHandler(this);
        currentAccount = Account.getCurrentAccount();
        companyName = findViewById(R.id.name_of_company);
        companyName.setText(currentAccount.getCompanyName());
        description = findViewById(R.id.description );
        description.setText(Account.getCurrentAccount().getDescription());
        aSwitch = findViewById(R.id.switch1);
        if (currentAccount.getLicensed() == 1) {
            aSwitch.setChecked(true);
        } else {
            aSwitch.setChecked(false);
        }
    }

    public void onClickModify(View view) {
        if (!(Validation.isEmpty(companyName)) && !(Validation.isEmpty(description))) {
           currentAccount.setDescription(description.getText().toString());
           currentAccount.setCompanyName(companyName.getText().toString());
           if (aSwitch.isChecked()) {
               currentAccount.setLicensed(1);
           } else {
               currentAccount.setLicensed(0);
           }
           currentAccount.update(this);
           finish();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG);
            toast.show();
        }
    }



}
