package com.example.nguye.seg2505app;

        import android.support.v7.app.AppCompatActivity;


public class TestDataBase extends AppCompatActivity{
    MyDBHandler dbHandler;
    public TestDataBase(){
        dbHandler = new MyDBHandler(this);
    }
    public  MyDBHandler getDatabase(){

        return dbHandler;
    }

    public void updateDatabase(){
        dbHandler = new MyDBHandler(this);
    }



}