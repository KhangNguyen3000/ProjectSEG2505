package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguye.seg2505app.Provider_list_adapter;
import com.example.nguye.seg2505app.R;
import com.example.nguye.seg2505app.ScheduleAdapter;
import com.example.nguye.seg2505app.Storables.Account;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Provider_list_adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    private int providerID[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        recyclerView = findViewById(R.id.daily_schedule);
        recyclerView.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

        Intent intent = getIntent();
        providerID = intent.getIntArrayExtra("providers");
        Account account;
        ArrayList<Account> providers = new ArrayList<Account>();
        for (int i = 0; i < providerID.length; i++){
            account = new Account().find(this, Account.COL_ID, providerID[i], false);
            providers.add(account);
        }
        rvAdapter = new Provider_list_adapter(this, providers);
        recyclerView.setAdapter(rvAdapter);
    }



}
