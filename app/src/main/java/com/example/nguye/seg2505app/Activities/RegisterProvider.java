package com.example.nguye.seg2505app.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.nguye.seg2505app.MyDBHandler;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Utilities.Validation;

public class RegisterProvider extends AppCompatActivity {
    MyDBHandler data;
    Account account;

    @Override
    protected void onCreate (Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_provider);
        this.data = new MyDBHandler(this);
        account = (Account) getIntent().getSerializableExtra("current_account");
        System.out.println(account.getFirstName());
    }


    public void onClickRegisterAsProvider(View view) {
        ViewGroup layout = findViewById(R.id.regp_llv_root);
        if (Validation.validateAll(layout)) {
            EditText nameOfCompany = findViewById(R.id.rp_noc);
            EditText generalDescription = findViewById(R.id.editText2);
            Switch licensed = findViewById(R.id.switch1);
            account.setCompanyName(nameOfCompany.getText().toString());
            account.setDescription(generalDescription.getText().toString());
            account.setRating(0);
            if (licensed.isChecked()) {
                account.setLicensed(1);
            } else {
                account.setLicensed(0);
            }
            account.add(this);
            Toast toast = Toast.makeText(getApplicationContext(), "Provider account created!", Toast.LENGTH_LONG);
            toast.show();
            setResult(9999, null);
            finish();

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG);
            toast.show();
        }
//        EditText nameOfCompany = findViewById(R.id.rp_noc);
//        EditText generalDescription = findViewById(R.id.editText2);
//        if (!(Validation.isEmpty(nameOfCompany)) && !(Validation.isEmpty(generalDescription))) {
//            addServiceToDatabase();
//        } else {
//            Toast toast = Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG);
//            toast.show();
//        }
    }

//    public void addServiceToDatabase() {
//        Account account = (Provider) getIntent().getSerializableExtra("current_account");
//        ((Provider) account).setCompanyName();
//        account.add(this);
//        System.out.println(account.getEmail());
//        Toast toast = Toast.makeText(getApplicationContext(), "Provider account created!", Toast.LENGTH_LONG);
//        toast.show();
//        setResult(9999, null);
//        finish();
//    }

    
//
//    public void addServiceToDatabase(){
//        Toast toast = Toast.makeText(getApplicationContext(), "Provider account created!", Toast.LENGTH_LONG);
//        toast.show();
//        finish();
//    }
}
