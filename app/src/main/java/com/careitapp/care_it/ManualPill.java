package com.careitapp.care_it;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ManualPill extends AppCompatActivity {
    @BindView(R.id.pillname1)
    EditText pillName;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_pill);

        Button addPill = (Button) findViewById(R.id.pillButton);

        addPill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManualPill.this, PillBox.class));
                Toast.makeText(ManualPill.this, "Successfully added!", Toast.LENGTH_LONG).show();
                }
        });

        Button btnRand = (Button)findViewById(R.id.randButn);

        btnRand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManualPill.this, SendSMS.class));
            }
        });
    }

}
