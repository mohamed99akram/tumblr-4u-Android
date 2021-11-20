package com.example.tumblr4u.View;
/**
* First page in the app (authentication page) that contains the signup and login buttons
* @author Omar Ahmed
* @version 1.0
* */

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

    private Button mLoginButton;
    private Button mSignupButton;
    private Button mLoginWithGoogleButton;
    private Button mSignupWithGoogleButton;
    private Button mLoginWithEmailButton;
    private Button mSignupWithEmailButton;
    private ViewPager2 mViewPager;
    private loginPageAdapter mAdapter;
    private SpringDotsIndicator mDotsIndicator;
    private LinearLayout mSignupButtons;
    private LinearLayout mLoginButtons;
    private LinearLayout mAuthenticationButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        initViews();
        initOnClickListeners();

        mSignupButtons.setVisibility(View.GONE);
        mLoginButtons.setVisibility(View.GONE);

        mAdapter = new loginPageAdapter(getSupportFragmentManager(), getLifecycle());
        mViewPager.setAdapter(mAdapter);
        mDotsIndicator.setViewPager2(mViewPager);


    }

    /**
     * This function assign the views in the page to their xml files using findById function
     * @return void
    * */
    private void initViews(){

        mLoginButton = (Button)findViewById(R.id.login_button);
        mSignupButton = (Button)findViewById(R.id.signup_button);

        mLoginWithEmailButton = (Button)findViewById(R.id.login_with_email_button);
        mLoginWithGoogleButton = (Button)findViewById(R.id.login_with_google_button);

        mSignupWithEmailButton = (Button)findViewById(R.id.signup_with_email_button);
        mSignupWithGoogleButton = (Button)findViewById(R.id.signup_with_google_button);

        mSignupButtons = (LinearLayout)findViewById(R.id.signup_buttons);
        mLoginButtons = (LinearLayout)findViewById(R.id.login_buttons);
        mAuthenticationButtons = (LinearLayout)findViewById(R.id.authentication_buttons);

        mDotsIndicator = (SpringDotsIndicator)findViewById(R.id.authentication_dots_indicator);
        mViewPager = (ViewPager2)findViewById(R.id.login_viewPager);
    }

    /**
     * This function assign the views in the page to their click listeners
     * @return void
     * */
    private void initOnClickListeners(){
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignupButtons.setVisibility(View.VISIBLE);
                mAuthenticationButtons.setVisibility(View.GONE);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginButtons.setVisibility(View.VISIBLE);
                mAuthenticationButtons.setVisibility(View.GONE);
            }
        });

        mSignupWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(getApplicationContext(), SignupWithEmail.class);
                startActivity(signupActivity);
            }
        });

        mLoginWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(getApplicationContext(), LoginWithEmail.class);
                startActivity(signupActivity);
            }
        });
    }
}