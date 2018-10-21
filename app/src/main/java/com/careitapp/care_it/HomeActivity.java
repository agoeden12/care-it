package com.careitapp.care_it;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.widget.ListView;

import com.careitapp.care_it.Contacts.AndroidContacts;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Calendar;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    AndroidContacts contacts = new AndroidContacts("name","number");
    @BindView(R.id.home_toolbar)
    Toolbar homeToolbar;
    @BindView(R.id.home_drawer)
    DrawerLayout homeDrawer;
    @BindView(R.id.home_navigation)
    NavigationView homeNavigation;
    @BindView(R.id.home_tabs)
    TabLayout weekDayTabs;
    @BindView(R.id.home_frame)
    ViewPager weekDayPager;

    private Calendar calendar = Calendar.getInstance();
    private int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        createToolbar();
        createTabs();

        if (getIntent().getBooleanExtra("notif", false)){

            FirebaseMessaging fm = FirebaseMessaging.getInstance();
            fm.send(new RemoteMessage.Builder("updates")
                    .setMessageId("0")
                    .addData("my_message", "Good job for adding pills!")
                    .addData("Topic", "updates")
                    .setTtl(10)
                    .setMessageType("updates")
                    .build());
        }

        FirebaseMessaging.getInstance().subscribeToTopic("updates");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    private void createToolbar(){
        homeToolbar.setNavigationIcon(R.drawable.ic_menu_white_24dp);
        homeToolbar.setNavigationOnClickListener(view -> homeDrawer.openDrawer(GravityCompat.START));
        homeToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        homeToolbar.setTitle(R.string.app_name);
        homeNavigation.inflateMenu(R.menu.navigation_menu);
        homeNavigation.setNavigationItemSelectedListener(menuItem ->{

            switch (menuItem.getItemId()){
                case (R.id.notification_item):{
                    startActivity(new Intent(this, Notifications.class));
                    homeDrawer.closeDrawer(GravityCompat.START);
                    return true;
                }

                case (R.id.family_item): {
                    Intent intent = new Intent(this, CareGivers.class);
                    intent.putExtra(CareGivers.EXTRA_CONTACT, contacts);
                    startActivity(intent);
                    homeDrawer.closeDrawer(GravityCompat.START);
                    return true;
                }
                default:
                    return true;
            }
        });
    }

    private void createTabs(){
        weekDayPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        weekDayTabs.setupWithViewPager(weekDayPager);
        Objects.requireNonNull(weekDayTabs.getTabAt(dayOfTheWeek-1)).select();
    }

    @OnClick(R.id.add_pill_fab)
    public void addPillManual(){
        startActivity(new Intent(HomeActivity.this, ManualPill.class));
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return setBundle(position);
        }

        private Fragment setBundle(int position){
            Fragment fragment = new HomeFragment();
            Bundle dayBundle = new Bundle();
            dayBundle.putInt("Position", position);
            fragment.setArguments(dayBundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case(0):
                    return "S";
                case(1):
                    return "M";
                case(2):
                    return "T";
                case(3):
                    return "W";
                case(4):
                    return "T";
                case(5):
                    return "F";
                case(6):
                    return "S";
                default:
                    return getResources().getString(R.string.app_name);
            }
        }
    }

}
