package com.example.nguye.seg2505app.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.Storables.Rating;

public class RateScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_screen);
    }

    public void onClickPost(View view) {
        int id_provider = (Integer) getIntent().getSerializableExtra("id_provider");
        Rating rating = new Rating();
    }

}
