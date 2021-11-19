package com.example.tumblr4u.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.tumblr4u.Adapters.loginPageAdapter;
import com.example.tumblr4u.R;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

public class Authentication extends AppCompatActivity {

    Button loginButton;
    Button signupButton;
    Button loginWithGoogleButton;
    Button signupWithGoogleButton;
    Button loginWithEmailButton;
    Button signupWithEmailButton;
    ViewPager2 viewPager;
    loginPageAdapter adapter;
    SpringDotsIndicator mDotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        loginButton = (Button)findViewById(R.id.login_button);
        signupButton = (Button)findViewById(R.id.signup_button);

        loginWithEmailButton = (Button)findViewById(R.id.login_with_email_button);
        loginWithGoogleButton = (Button)findViewById(R.id.login_with_google_button);

        signupWithEmailButton = (Button)findViewById(R.id.signup_with_email_button);
        signupWithGoogleButton = (Button)findViewById(R.id.signup_with_google_button);

        LinearLayout signupButtons = (LinearLayout)findViewById(R.id.signup_buttons);
        LinearLayout loginButtons = (LinearLayout)findViewById(R.id.login_buttons);
        LinearLayout authenticationButtons = (LinearLayout)findViewById(R.id.authentication_buttons);

        signupButtons.setVisibility(View.GONE);
        loginButtons.setVisibility(View.GONE);

        mDotsIndicator = (SpringDotsIndicator)findViewById(R.id.authentication_dots_indicator);
        viewPager = (ViewPager2)findViewById(R.id.login_viewPager);
        adapter = new loginPageAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);
        mDotsIndicator.setViewPager2(viewPager);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupButtons.setVisibility(View.VISIBLE);
                authenticationButtons.setVisibility(View.GONE);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButtons.setVisibility(View.VISIBLE);
                authenticationButtons.setVisibility(View.GONE);
            }
        });

        signupWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(getApplicationContext(), SignupWithEmail.class);
                startActivity(signupActivity);
            }
        });

        loginWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(getApplicationContext(), LoginWithEmail.class);
                startActivity(signupActivity);
            }
        });



    }
}