package com.careitapp.care_it;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.careitapp.care_it.Contacts.AndroidContacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsActvity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<AndroidContacts> arrayListContacts = new ArrayList<>();
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mListView = findViewById(R.id.list_view_contacts);
        showContacts();

    }
    /**
     * Show the contacts in the ListView.
     */
    private void showContacts() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);

        } else {
            List<AndroidContacts> contacts = get_Contacts();
            ArrayAdapter<AndroidContacts> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
            mListView.setAdapter(adapter);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we cannot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public List<AndroidContacts> get_Contacts() {

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {

                AndroidContacts android_contact = new AndroidContacts();
                String contact_id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contact_display_name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                android_contact.setContactName(contact_display_name);


                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {

                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , null
                            , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                            , new String[]{contact_id}
                            , null);

                    while (phoneCursor.moveToNext()) {
                        String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        android_contact.setPhoneNumber(number);

                    }
                    phoneCursor.close();
                }

                arrayListContacts.add(android_contact);


            }
        }
        return arrayListContacts;
    }

}

