package com.example.nguye.seg2505app;

import android.content.Intent;
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
}
