package com.careitapp.care_it;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.careitapp.care_it.Contacts.AndroidContacts;

public class CareGivers extends AppCompatActivity {
    private Button button;
    public static final String EXTRA_CONTACT = "CONTACT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_givers);

        AndroidContacts contacts = (AndroidContacts) getIntent().getSerializableExtra(EXTRA_CONTACT);

        button = findViewById(R.id.addContactsBtn);
        button.setOnClickListener(v -> {
            openContactsActivity();
        });


    }

    public void openContactsActivity() {
        Intent intent = new Intent(this,ContactsActvity.class);
        startActivity(intent);
    }

}
