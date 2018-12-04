package com.example.nguye.seg2505app.Activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.Rating;

public class RateScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_screen);
        Account provider = new Account().find(this, Account.COL_ID, (Integer) getIntent().getSerializableExtra("id_provider"), false);
        TextView ratedName = (TextView) findViewById(R.id.ratedName);
        ratedName.setText(provider.getFirstName()+" "+provider.getLastName());
        TextView ratedAddress = (TextView) findViewById(R.id.ratedAddress);
        ratedAddress.setText(provider.getStreetNumber()+" "+provider.getStreetName());
        TextView ratedArea = (TextView) findViewById(R.id.cityProvinceArea);
        ratedArea.setText(provider.getCity()+", "+provider.getProvince()+", "+provider.getPostalCode());
        TextView providerName = (TextView) findViewById(R.id.companyName);
        providerName.setText(provider.getCompanyName());
    }

    public void onClickRate(View view){
        RatingBar rate = (RatingBar) view;
    }

    public void onClickPost(View view) {
        int id_provider = (Integer) getIntent().getSerializableExtra("id_provider");
        int id_customer = Account.getCurrentAccount().getID();
        EditText commentaire = (EditText) findViewById(R.id.commentaire);
        String description = commentaire.getText().toString();
        float ratingNumber = ((RatingBar) findViewById(R.id.ratingBar2)).getRating();
        if(ratingNumber > 0 && ratingNumber < 5) {
            Rating rating = new Rating(id_provider, id_customer, description, ratingNumber);
            rating.add(this);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Rating needed");
            builder.setMessage("please add a rating before posting");
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
    }

}
