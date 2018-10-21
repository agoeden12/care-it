package com.careitapp.care_it;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Button;

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

public class HomeFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    long startTime;
    String phoneNo = "6783580275";
    String message = "Your loved one hasn't taken their medication yet! Would you like to call?";
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

        Button secretButt = rootView.findViewById(R.id.secretBtn);
        secretButt.setOnClickListener(view -> startTime = System.currentTimeMillis());

        long time = System.currentTimeMillis();
        Long difference = new Long(time - startTime);
        Long base = new Long(480000000);
        int comparison = difference.compareTo(base);

        if (comparison > 0) {
            sendSMSMessage();
        }

        return rootView;
    }

    protected void sendSMSMessage() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.SEND_SMS)) {

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, message, null, null);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

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
                try {
                    pills.clear();
                    Iterable<DataSnapshot> result = dataSnapshot.child(mUser.getUid()).child("pills").getChildren();
                    for (DataSnapshot itemId : result) {
                        pills.add(setPillInformation(itemId));
                    }
                    recyclerView.setAdapter(new PillAdapter(pills, getActivity()));
                } catch (SecurityException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Please Login or Sign Up", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private Pill setPillInformation(DataSnapshot itemId) {
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
