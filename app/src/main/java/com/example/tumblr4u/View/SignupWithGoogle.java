package com.example.tumblr4u.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.tumblr4u.R;
import com.example.tumblr4u.ViewModel.SignupWithEmailViewModel;
import com.example.tumblr4u.ViewModel.SignupWithGoogleViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SignupWithGoogle extends AppCompatActivity {
    // sign in with google
    int RC_SIGN_UP = 0; // request code of the intent
    //    SignInButton mChooseAccountButton;
    private Button mChooseAccountButton;
    GoogleSignInClient mGoogleSignInClient;


    private SignupWithGoogleViewModel mViewModel;
    private EditText mAge;
    private EditText mName;
    private ImageButton mBackButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_with_google);


        initView();
        initOnChangeListener();
        initOnClickListener();
        initObservers();

        initClient();

        mChooseAccountButton.setEnabled(false);
    }

    /**
     * initialize views
     * */
    public void initView() {
        mChooseAccountButton = findViewById(R.id.choose_account);
        mBackButton = findViewById(R.id.signup_with_google_back_button);
        mAge = findViewById(R.id.signup_with_google_age_field);
        mName = findViewById(R.id.signup_with_google_name_field);
        mViewModel = new ViewModelProvider(this).get(SignupWithGoogleViewModel.class);

    }

    /**
     * tell buttons where to go
     * */
    public void initOnClickListener() {
        mChooseAccountButton.setOnClickListener(v -> {
            signUp();
        });

        mBackButton.setOnClickListener(v -> {
            Intent authenticationActivity = new Intent(getApplicationContext(),
                    Authentication.class);
            startActivity(authenticationActivity);
        });
    }

    /**
     * link to the ViewModel with observers
     * */
    public void initObservers() {
        mViewModel.successfulSignup.observe(this, aBoolean ->
        {
            if (!mViewModel.successfulSignup.getValue()) {
//                Toast.makeText(SignupWithGoogle.this, "unsuccessful signup",
//                        Toast.LENGTH_SHORT).show();
                return;
            }
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(home);
        });
    }

    private class customTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            mChooseAccountButton.setEnabled(!(mName.getText().toString().isEmpty() |
                    mAge.getText().toString().isEmpty()));
        }
    }

    /**
     * if data entered is correct, enable the button from the class customTextWatcher
     * */
    public void initOnChangeListener() {
        mAge.addTextChangedListener(new customTextWatcher());
        mName.addTextChangedListener(new customTextWatcher());
    }

    // ------------ SignUp with Google functions: ---------------
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

    private void signUp() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_UP);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_UP) {
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

            mViewModel.signup(mAge.getText().toString(),mName.getText().toString(),token);
            // Signed in successfully, show authenticated UI.
//            startActivity(new Intent(SignupWithGoogle.this, MainActivity.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(SignupWithGoogle.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }
}
