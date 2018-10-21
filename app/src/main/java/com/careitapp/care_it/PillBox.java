package com.careitapp.care_it;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class PillBox extends AppCompatActivity {

    private RecyclerView pillRecyclerView;
    private RecyclerView.Adapter pillRecyclerAdapter;
    private RecyclerView.LayoutManager pillRecyclerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_box);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        pillRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        pillRecyclerManager = new LinearLayoutManager(this);
        pillRecyclerView.setLayoutManager(pillRecyclerManager);

        // specify an adapter (see also next example)
        String[] testerDataset = {"this","is","a","test"};
        pillRecyclerAdapter = new MyAdapter(testerDataset);
        pillRecyclerView.setAdapter(pillRecyclerAdapter);


    }
}
