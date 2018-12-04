package com.example.nguye.seg2505app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        recyclerView = findViewById(R.id.provider_list);
        recyclerView.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLayoutManager);

        Bundle extras = getIntent().getExtras();
        providerID = extras.getIntArray("providers");
        Account account;
        ArrayList<Account> providers = new ArrayList<Account>();
        for (int i = 0; i < providerID.length; i++){
            account = new Account().find(this, Account.COL_ID, providerID[i], false);
            providers.add(account);
        }
        rvAdapter = new Provider_list_adapter(this, providers);
        rvAdapter.setClickListener(new Provider_list_adapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position){
                TextView tvEmail = view.findViewById(R.id.sr_txt_email);
                String email = tvEmail.getText().toString();
                Account provider = new Account().find(view.getContext(), Account.COL_EMAIL, email, true);
                Intent provider_id = new Intent(getApplicationContext(), GetProviderInfos.class);
                provider_id.putExtra("ID_provider", provider.getID());
                startActivity(provider_id);
            }
        });
        recyclerView.setAdapter(rvAdapter);
    }

    public void onClickNewSearch(View view) {
        finish();
    }





}
