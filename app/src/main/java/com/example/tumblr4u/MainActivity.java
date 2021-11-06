package com.example.tumblr4u;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tumblr4u.R;
import com.example.tumblr4u.fragments.ActivityFragment;
import com.example.tumblr4u.fragments.HomeFragment;
import com.example.tumblr4u.fragments.ProfileFragment;
import com.example.tumblr4u.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navView;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ActivityFragment activityFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBottomNavView();
    }
    /**
     * this function initialize BottomNavigationView
     */
    private void initBottomNavView() {
        navView = findViewById(R.id.nav_view);

        // menu should be considered as top level destinations.
        NavHostFragment navHostFragment = NavHostFragment.create(R.navigation.mobile_navigation);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.nav_host_fragment_activity_main, navHostFragment)
                .setPrimaryNavigationFragment(navHostFragment)
                .commit();
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        activityFragment = new ActivityFragment();
        profileFragment = new ProfileFragment();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_activity_main, homeFragment, "home")
                                .addToBackStack("home")
                                .commit();
                        return true;
                    case R.id.navigation_search:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_activity_main, searchFragment, "search")
                                .addToBackStack("search")
                                .commit();
                        return true;
                    case R.id.navigation_activity:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_activity_main, activityFragment, "activity")
                                .addToBackStack("activity")
                                .commit();
                        return true;
                    case R.id.navigation_profile:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment_activity_main, profileFragment, "profile")
                                .addToBackStack("profile")
                                .commit();
                        return true;
                }
                return false;
            }
        });


    }
}
