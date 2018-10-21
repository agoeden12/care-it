package com.careitapp.care_it;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.careitapp.care_it.Contacts.Adapter_for_contacts;
import com.careitapp.care_it.Contacts.AndroidContacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsActvity extends AppCompatActivity {

    private RecyclerView mListView;
    private List<AndroidContacts> listOfContacts = new ArrayList<>();
    private Adapter_for_contacts adapter;
    private LinearLayoutManager mLayoutManager;
    //private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mListView = findViewById(R.id.contact_recycler);
        mListView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mListView.setLayoutManager(mLayoutManager);
        //check if you can show contacts

        adapter = new Adapter_for_contacts(listOfContacts);
        mListView.setAdapter(adapter);
        getContacts();
    }

    public void getContacts() {
        listOfContacts.clear();
        AndroidContacts contact = new AndroidContacts("Sammy Whitt", "(678)358-0275");
        listOfContacts.add(contact);

        AndroidContacts contact1 = new AndroidContacts("Sandy Xie", "(678)628-2904");
        listOfContacts.add(contact1);

        AndroidContacts contact2 = new AndroidContacts("Sumire Benali", "(434)455-8498");
        listOfContacts.add(contact2);

    }
}

