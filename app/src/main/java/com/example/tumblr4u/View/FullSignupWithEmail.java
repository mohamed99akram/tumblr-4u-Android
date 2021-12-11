package com.example.tumblr4u.View;


/**
 * @author Omar Ahmed
 * @version 1.0
 * */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tumblr4u.GeneralPurpose.InputMinMaxFilter;
import com.example.tumblr4u.R;
import com.example.tumblr4u.ViewModel.SignupWithEmailViewModel;

/**
 * This class is the signup with email class using email password and name
 * */
public class FullSignupWithEmail extends AppCompatActivity {

    private Button mDoneButton;
    private ImageButton mBackButton;
    private EditText mAge;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mName;
    private TextView mInvalidEmail;
    private SignupWithEmailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_signup_with_email);

        initView();
        initOnChangeListner();
        initOnClickListner();
        initObservers();

        mDoneButton.setEnabled(false);
        mAge.setFilters(new InputFilter[] {new InputMinMaxFilter(1, 100)});
    }

    /**
     * Assign the views with their xml files
     * @return void
     * */
    private void initView() {
        mDoneButton = (Button) findViewById(R.id.full_signup_with_email_done_button);
        mBackButton = (ImageButton) findViewById(R.id.full_signup_with_email_back_button);
        mAge = (EditText) findViewById(R.id.full_signup_with_email_age_field);
        mEmail = (EditText) findViewById(R.id.full_signup_with_email_email_field);
        mPassword = (EditText) findViewById(R.id.full_signup_with_email_password_field);
        mName = (EditText) findViewById(R.id.full_signup_with_email_name_field);
        mInvalidEmail = (TextView) findViewById(R.id.full_signup_with_email_invalid_email_message);
        mViewModel = new ViewModelProvider(this).get(SignupWithEmailViewModel.class);
    }

    /**
     * Set the views to the container of the data they waiting for, after demanding it from view model
     * @return void
     * */
    public void initObservers(){
        mViewModel.successfulSignup.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!mViewModel.successfulSignup.getValue()) {
                    Toast.makeText(FullSignupWithEmail.this, "unsuccessful signup", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });
        mViewModel.isValidEmail.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!mViewModel.isValidEmail.getValue()){ mInvalidEmail.setVisibility(View.VISIBLE); }
            }
        });

    }

    /**
     * Assign the views to their click listner
     *
     * @return void
     */
    public void initOnClickListner() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent authenticationActivity = new Intent(getApplicationContext(), Authentication.class);
                startActivity(authenticationActivity);
            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.signup(mAge.getText().toString(),
                        mEmail.getText().toString(),
                        mPassword.getText().toString(),
                        mName.getText().toString());
            }
        });
    }

    /**
     * Assign the views to their change listner
     *
     * @return void
     */
    public void initOnChangeListner() {
        mAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mDoneButton.setEnabled(!(mEmail.getText().toString().equals("") |
                        mPassword.getText().toString().equals("") |
                        mName.getText().toString().equals("") |
                        mAge.getText().toString().equals("")));

            }
        });

        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mDoneButton.setEnabled(!(mEmail.getText().toString().equals("") |
                        mPassword.getText().toString().equals("") |
                        mName.getText().toString().equals("") |
                        mAge.getText().toString().equals("")));

            }
        });

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                mDoneButton.setEnabled(!(mEmail.getText().toString().equals("") |
                        mPassword.getText().toString().equals("") |
                        mName.getText().toString().equals("") |
                        mAge.getText().toString().equals("")));
            }
        });

        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                mDoneButton.setEnabled(!(mEmail.getText().toString().equals("") |
                        mPassword.getText().toString().equals("") |
                        mName.getText().toString().equals("") |
                        mAge.getText().toString().equals("")));
            }
        });
    }
}