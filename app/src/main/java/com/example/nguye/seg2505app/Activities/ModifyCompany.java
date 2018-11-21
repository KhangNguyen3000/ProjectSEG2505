package com.example.nguye.seg2505app.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.Validation;

public class ModifyCompany extends AppCompatActivity {
    MyDBHandler data;
    TextView CompanyName;
    TextView Description;
    Account currentAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_provider);
        this.data = new MyDBHandler(this);
        currentAccount = Account.getCurrentAccount();
         CompanyName= (TextView)findViewById(R.id.name_of_company);
        CompanyName.setText(currentAccount.getCompanyName());
        Description = (TextView)findViewById(R.id.description );
        Description.setText(Account.getCurrentAccount().getDescription());
    }

    public void onClickModify(View view) {
        EditText nameOfCompany = findViewById(R.id.name_of_company);
        EditText generalDescription = findViewById(R.id.description);
        if (!(Validation.isEmpty(nameOfCompany)) && !(Validation.isEmpty(generalDescription))) {
           currentAccount.setDescription(generalDescription.toString());
           currentAccount.setCompanyName(nameOfCompany.toString());
           currentAccount.update(this);


        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG);
            toast.show();
        }
    }



}
