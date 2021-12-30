package com.example.tumblr4u.View;
/**
 * First page in the app (authentication page) that contains the signup and login buttons
 *
 * @author Omar Ahmed
 * @version 1.0
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tumblr4u.Adapters.loginPageAdapter;
import com.example.tumblr4u.R;
import com.example.tumblr4u.ViewModel.SignInWithGoogleViewModel;
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
    private Button mLoginWithEmailButton;
    private Button mSignupWithEmailButton;
    private ViewPager2 mViewPager;
    private loginPageAdapter mAdapter;
    private SpringDotsIndicator mDotsIndicator;
    private LinearLayout mSignupButtons;
    private LinearLayout mLoginButtons;
    private LinearLayout mAuthenticationButtons;
    private ImageView mLandingPage;

    // sign in with google
    int RC_SIGN_IN = 0; // request code of the intent
    //    SignInButton mLoginWithGoogleButton;
    private Button mLoginWithGoogleButton;
    GoogleSignInClient mGoogleSignInClient;

    // signup with google
    private Button mSignupWithGoogleButton;
    // login with google
    private SignInWithGoogleViewModel mSignInWithGoogleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        initViews();

        // google sign in client init
        initClient();
        initGoogleObserver();

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
    private void initViews() {

        mLoginButton = (Button) findViewById(R.id.login_button);
        mSignupButton = (Button) findViewById(R.id.signup_button);

        mLoginWithEmailButton = (Button) findViewById(R.id.login_with_email_button);

        // login with google
        mLoginWithGoogleButton = findViewById(R.id.login_with_google_button);

        mSignupWithEmailButton = (Button) findViewById(R.id.signup_with_email_button);
        mSignupWithGoogleButton = (Button) findViewById(R.id.signup_with_google_button);

        mSignupButtons = (LinearLayout) findViewById(R.id.signup_buttons);
        mLoginButtons = (LinearLayout) findViewById(R.id.login_buttons);
        mAuthenticationButtons = (LinearLayout) findViewById(R.id.authentication_buttons);
        mLandingPage = (ImageView) findViewById(R.id.landing_page);

        mDotsIndicator = (SpringDotsIndicator) findViewById(R.id.authentication_dots_indicator);
        mViewPager = (ViewPager2) findViewById(R.id.login_viewPager);
        // Google
        mSignInWithGoogleViewModel = new ViewModelProvider(this).get(
                SignInWithGoogleViewModel.class);
    }

    /**
     * This function assign the views in the page to their click listeners
     * @return void
     * */
    private void initOnClickListeners() {
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
                Intent signupActivity = new Intent(getApplicationContext(),
                        FullSignupWithEmail.class);
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

        // signup with google
        mSignupWithGoogleButton.setOnClickListener(v -> {
            startActivity(new Intent(Authentication.this, SignupWithGoogle.class));
        });
    }

    // ------------ Login with Google functions: ---------------

    /**
     * observe changes in view model to see if there is a successful sign in.
     * if there is, give the user access to the home page
     * */
    private void initGoogleObserver() {
        mSignInWithGoogleViewModel.successfulSignIn.observe(this, aBoolean -> {
            if (!mSignInWithGoogleViewModel.successfulSignIn.getValue()) {
                return;
            }
            startActivity(new Intent(Authentication.this, MainActivity.class));
        });
    }

    /**
     * helper function to initialize client
     * client will be used to make the Google Sign in intent
     * */
    private void initClient() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    /**
     * Let the user choose account to sign in
     * */
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /**
     * process result of the intent
     * */
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

    /**
     * get the google token from the signed in user
     * then login from viewModel and pass this token to get the response with the server's token
     * */
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            //todo save to shared preferences
            String token = account.getIdToken();
            Log.e("AUTHENTICATION", "GoogleIdToken = " + token);

            // Signed in successfully, show authenticated UI.
            mSignInWithGoogleViewModel.login(token);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(Authentication.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * At each start, check if the user is signed in by google before or not.
     * if the user is signed in, continue and go to home screen
     *
     * */
    //  TODO: some mess happens when signup fails and then you return back here, it will redirect
    //  TODO: you the MainActivity and it shouldn't
    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        // TODO: account != null and signup.successful != false
        if (account != null) {
            startActivity(new Intent(Authentication.this, MainActivity.class));
        }
        super.onStart();
    }

}