package com.careitapp.care_it;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class splash_activity extends AppCompatActivity {

    @BindView(R.id.splash_frame)
    ConstraintLayout splashFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        ButterKnife.bind(this);

        startRunnable();
    }

    private Boolean isUserLoggedIn(){
        if (FirebaseAuth.getInstance().getCurrentUser() != null)
            return true;
        else
            return false;
    }

    private void startRunnable(){
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if(isUserLoggedIn())
                startActivity(new Intent(this, MainActivity.class));
            else
                setFragment(new LoginFragment());
        }, 2500);
    }

    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(splashFrame.getId(), fragment).commitNow();
    }
}
