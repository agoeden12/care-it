package com.careitapp.care_it;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabaseReference;

    private ArrayList<Pill> pills = new ArrayList<>();

    @BindView(R.id.pill_recycle)
    RecyclerView recyclerView;

    public HomeFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        initializeRecyclerView();
        initializeFirebaseVariables();
        addDatabaseEventListeners();

        return rootView;
    }

    private void initializeRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(new PillAdapter(pills, getActivity()));
    }

    private void initializeFirebaseVariables() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUser = mFirebaseAuth.getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void addDatabaseEventListeners() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pills.clear();
                Iterable<DataSnapshot> result = dataSnapshot.child(mUser.getUid()).getChildren();
                for (DataSnapshot itemId : result) {
                    pills.add(setPillInformation(itemId));
                }
                recyclerView.setAdapter(new PillAdapter(pills, getActivity()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private Pill setPillInformation(DataSnapshot itemId){
        try {
            return new Pill(
                    Objects.requireNonNull(itemId.child("pillName").getValue()).toString(),
                    Objects.requireNonNull(itemId.child("pillsPerSession").getValue()).toString(),
                    Objects.requireNonNull(itemId.child("sessionsPerDay").getValue()).toString(),
                    Objects.requireNonNull(itemId.child("totalPills").getValue()).toString());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return new Pill();
        }
    }

}
