package com.careitapp.care_it;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

public class PillBox extends AppCompatActivity {

    private RecyclerView pillRecyclerView;
    private RecyclerView.Adapter pillRecyclerAdapter;
    private RecyclerView.LayoutManager pillRecyclerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_box);



    }
}
