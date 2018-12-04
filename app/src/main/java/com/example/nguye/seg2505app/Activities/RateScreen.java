package com.example.nguye.seg2505app.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Account;
import com.example.nguye.seg2505app.Storables.Rating;

public class RateScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_screen);
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

        Rating rating = new Rating(id_provider, id_customer, description, ratingNumber);
        rating.add(this);
    }

}
