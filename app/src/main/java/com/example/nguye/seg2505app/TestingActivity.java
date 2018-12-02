package com.example.nguye.seg2505app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TestingActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        // specify an adapter (see also next example)
//        ArrayList<String[]> dataset = new ArrayList<>();
//        dataset.add(new String[] {"140", "AVAILABLE"});
//        dataset.add(new String[] {"180", "BOOKED"});
//        dataset.add(new String[] {"200", "AVAILABLE"});
//        dataset.add(new String[] {"300", "UNAVAILABLE"});
//        dataset.add(new String[] {"400", "AVAILABLE"});
//        dataset.add(new String[] {"500", "BOOKED"});
//        dataset.add(new String[] {"600", "AVAILABLE"});
//        dataset.add(new String[] {"700", "UNAVAILABLE"});
//        mAdapter = new ScheduleAdapter(this, dataset);
//        mRecyclerView.setAdapter(mAdapter);
    }
}
