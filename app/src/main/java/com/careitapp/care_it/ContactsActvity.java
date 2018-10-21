package com.careitapp.care_it;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.careitapp.care_it.Contacts.ContactsAdapter;
import com.careitapp.care_it.Contacts.AndroidContacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsActvity extends AppCompatActivity {

    private RecyclerView contactRecycler;
    private List<AndroidContacts> listOfContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        contactRecycler = findViewById(R.id.contact_recycler);

        getContacts();
    }

    public void getContacts() {
        listOfContacts.clear();
        listOfContacts.add(new AndroidContacts("Sammy Whitt", "(678)358-0275"));
        listOfContacts.add(new AndroidContacts("Sandy Xie", "(678)628-2904"));
        listOfContacts.add(new AndroidContacts("Sumire Benali", "(434)455-8498"));
        contactRecycler.setLayoutManager(new LinearLayoutManager(this));
        contactRecycler.setAdapter(new ContactsAdapter(listOfContacts, this));
    }
}

