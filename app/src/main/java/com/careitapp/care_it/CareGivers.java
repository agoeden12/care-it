package com.careitapp.care_it;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import com.careitapp.care_it.Contacts.AndroidContacts;
import com.careitapp.care_it.Contacts.CareGiversAdapter;
import com.careitapp.care_it.Contacts.ContactsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CareGivers extends AppCompatActivity {

    public static final String EXTRA_CONTACT = "CONTACT";
    private List<AndroidContacts> listOfContacts = new ArrayList<>();

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabaseReference;

    @BindView(R.id.caregiver_toolbar)
    Toolbar toolbar;
    @BindView(R.id.family_recycler)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_givers);


        ButterKnife.bind(this);
//        AndroidContacts contacts = (AndroidContacts) getIntent().getSerializableExtra(EXTRA_CONTACT);

        initializeFirebaseVariables();
        initializeRecyclerView();
        addDatabaseEventListeners();

        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    private void initializeRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CareGiversAdapter(listOfContacts, this));
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
                listOfContacts.clear();
                Iterable<DataSnapshot> result = dataSnapshot.child(mUser.getUid()).child("CareGivers").getChildren();
                for (DataSnapshot itemId : result) {
                    listOfContacts.add(setContactInformation(itemId));
                }
                recyclerView.setAdapter(new CareGiversAdapter(listOfContacts, getApplicationContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public AndroidContacts setContactInformation(DataSnapshot itemId) {
        try{
            return new AndroidContacts(Objects.requireNonNull(itemId.child("name").getValue()).toString(),
                    Objects.requireNonNull(itemId.child("phone").getValue()).toString());
        }catch (NullPointerException e){
            e.printStackTrace();
            return new AndroidContacts();
        }

    }

    @OnClick(R.id.addContactsBtn)
    public void openContactsActivity() {
        Intent intent = new Intent(this,ContactsActvity.class);
        startActivity(intent);
    }

}
