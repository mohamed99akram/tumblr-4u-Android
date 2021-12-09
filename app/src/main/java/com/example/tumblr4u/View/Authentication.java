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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tumblr4u.Adapters.loginPageAdapter;
import com.example.tumblr4u.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

public class Authentication extends AppCompatActivity {

    private Button mLoginButton;
    private Button mSignupButton;
    private Button mSignupWithGoogleButton;
    private Button mLoginWithEmailButton;
    private Button mSignupWithEmailButton;
    private ViewPager2 mViewPager;
    private loginPageAdapter mAdapter;
    private SpringDotsIndicator mDotsIndicator;
    private LinearLayout mSignupButtons;
    private LinearLayout mLoginButtons;
    private LinearLayout mAuthenticationButtons;

    // sign in with google
    int RC_SIGN_IN = 0; // request code of the intent
    SignInButton mLoginWithGoogleButton;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        initViews();

        // google sign in client init
        initClient();

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

        // login with google
        mLoginWithGoogleButton = findViewById(R.id.login_with_google_button);

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
                Intent signupActivity = new Intent(getApplicationContext(), FullSignupWithEmail.class);
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

        // login with Google:
        mLoginWithGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    // ------------ Login with Google functions: ---------------
    private void initClient(){
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        // TODO change this
        String serverClientId = "648954387473-a7v2qlqj2557l9bsiu3lje13ce8vhaph.apps.googleusercontent.com";
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(serverClientId)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            //todo save to shared preferences
            String token = account.getIdToken();

            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(Authentication.this, MainActivity.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(Authentication.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            startActivity(new Intent(Authentication.this, MainActivity.class));
        }
        super.onStart();
    }

}