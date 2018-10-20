package com.careitapp.care_it;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ManualPill extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_pill);

        Spinner monthDrop = findViewById(R.id.pill_month);
        String[] month = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November","December"};
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, month);
        monthDrop.setAdapter(monthAdapter);

        Spinner dayDrop = findViewById(R.id.pill_day);
        String[] day = new String[31];
        for (int i=0; i<day.length; i++) {
            int dayNum = i+1;
            day[i] = new Integer(dayNum).toString();
        }
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, day);
        dayDrop.setAdapter(dayAdapter);

        Spinner yearDrop = findViewById(R.id.pill_year);
        String[] year = new String[21];
        for (int j=0; j<year.length; j++) {
            int yearNum = 2018+j;
            year[j] = new Integer(yearNum).toString();
        }
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, year);
        yearDrop.setAdapter(yearAdapter);

        Spinner monthDrop2 = findViewById(R.id.pill_month2);
        String[] month2 = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November","December"};
        ArrayAdapter<String> month2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, month2);
        monthDrop2.setAdapter(month2Adapter);

        Spinner dayDrop2 = findViewById(R.id.pill_day2);
        String[] day2 = new String[31];
        for (int i=0; i<day2.length; i++) {
            int day2Num = i+1;
            day2[i] = new Integer(day2Num).toString();
        }
        ArrayAdapter<String> day2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, day2);
        dayDrop2.setAdapter(day2Adapter);

        Spinner year2Drop = findViewById(R.id.pill_year2);
        String[] year2 = new String[21];
        for (int j=0; j<year2.length; j++) {
            int year2Num = 2018+j;
            year2[j] = new Integer(year2Num).toString();
        }
        ArrayAdapter<String> year2Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, year2);
        year2Drop.setAdapter(year2Adapter);

        Button addPill = (Button) findViewById(R.id.pillButton);

        addPill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ManualPill.this, "Success!", Toast.LENGTH_LONG).show();
                }
        });
    }

}
