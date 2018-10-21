package com.careitapp.care_it;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManualPill extends AppCompatActivity {
    @BindView(R.id.pillname1)
    EditText pillName;
    @BindView(R.id.dayTimesText)
    EditText timesPerSession;
    @BindView(R.id.pillTimesText)
    EditText timesPerDay;
    @BindView(R.id.pillCount)
    EditText amountOfPills;

    private Pill newPill;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_pill);

        ButterKnife.bind(this);
        initializeFirebaseVariables();

        if(getIntent() != null){
            timesPerSession.setText(getIntent().getStringExtra("perSession"));
            timesPerDay.setText(getIntent().getStringExtra("perDay"));
            amountOfPills.setText(getIntent().getStringExtra("totalPills"));
        }
    }

    private void initializeFirebaseVariables() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mUser = mFirebaseAuth.getCurrentUser();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void setPillInfo(){
        if (!TextUtils.isEmpty(pillName.getText().toString().trim()) &&
                !TextUtils.isEmpty(timesPerSession.getText().toString().trim()) &&
                !TextUtils.isEmpty(timesPerDay.getText().toString().trim()) &&
                !TextUtils.isEmpty(amountOfPills.getText().toString().trim())){
           newPill = new Pill(
                   pillName.getText().toString().trim(),
                   timesPerSession.getText().toString().trim(),
                   timesPerDay.getText().toString().trim(),
                   amountOfPills.getText().toString().trim());
        } else {
            Toast.makeText(this, "Make sure no fields are empty", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.pillButton)
    public void addPill(){
        setPillInfo();
        if (newPill != null){
            mDatabaseReference.child(mUser.getUid()).child("pills").push().setValue(newPill)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(this, HomeActivity.class));
                        finish();
                    });
        }
    }
    @OnClick(R.id.btnSendSMS)
    public void openVision(){
        startActivity(new Intent(this, Vision.class));
    }
}
