package com.careitapp.care_it;

import android.content.Intent;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_frame)
    ConstraintLayout splashFrame;

    @BindView(R.id.app_logo_splash)
    ImageView splashImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        startRunnable();
    }

    private void startRunnable(){
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce_animation);
            splashImage.startAnimation(bounce);

            if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }
            else
                setFragment(new LoginFragment());
        }, 1100);
    }

    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(splashFrame.getId(), fragment).commitNow();
    }
}
