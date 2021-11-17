package com.example.tumblr4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Button;

import com.example.tumblr4u.adapters.loginPageAdapter;

public class authentication extends AppCompatActivity {

    Button loginButton;
    Button signupButton;
    ViewPager2 viewPager;
    loginPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        getSupportActionBar().hide();

        loginButton = (Button)findViewById(R.id.login_button);
        signupButton = (Button)findViewById(R.id.signup_button);
        viewPager = (ViewPager2)findViewById(R.id.login_viewPager);
        adapter = new loginPageAdapter(getSupportFragmentManager(), getLifecycle());

        viewPager.setAdapter(adapter);

    }
}