package com.example.tumblr4u.View;

/**
 * Login with email activity
 * @author Omar Ahmed
 * @version 1.0
 * */

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tumblr4u.R;
import com.example.tumblr4u.ViewModel.LoginWithEmailViewModel;

public class LoginWithEmail extends AppCompatActivity {

    private Button mLogin;
    private ImageButton mBack;
    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView mWrongMessage;
    private LoginWithEmailViewModel mViewModel;
    private TextView mInvalidEmailMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);

        initViews();
        initOnClickListener();
        initObservers();
        initOnChangedListner();
        mLogin.setEnabled(false);


    }

    /**
     * Assign the views with their xml files
     * @return void
     * */
    public void initViews(){
        mLogin = (Button)findViewById(R.id.login_with_email_login_button);
        mBack = (ImageButton) findViewById(R.id.login_with_email_back_button);
        mEmailField = (EditText) findViewById(R.id.login_with_email_email_field);
        mPasswordField = (EditText) findViewById(R.id.login_with_email_password_field);
        mWrongMessage = (TextView) findViewById(R.id.login_with_email_wrong_message);
        mInvalidEmailMessage = (TextView) findViewById(R.id.login_with_email_invalid_email_message);
        mViewModel = new ViewModelProvider(this).get(LoginWithEmailViewModel.class);
    }

    /**
     * Assign the views with their click listners
     * @return void
     * */
    public void initOnClickListener(){
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent authenticationActivity = new Intent(getApplicationContext(), Authentication.class);
                startActivity(authenticationActivity);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.login(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        });

    }

    /**
     * Set the views to the container of the data they waiting for, after demanding it from view model
     * @return void
     * */
    public void initObservers(){
        mViewModel.isValidEmailAndPassword.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!mViewModel.isValidEmailAndPassword.getValue()) {
                    mWrongMessage.setVisibility(View.VISIBLE);
                    mInvalidEmailMessage.setVisibility(View.GONE);
                    return;
                }
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });

        mViewModel.isValidEmail.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!mViewModel.isValidEmail.getValue()){
                    mInvalidEmailMessage.setVisibility(View.VISIBLE);
                    mWrongMessage.setVisibility(View.GONE);
                }
            }
        });

    }

    /**
     * Assign the views to their change listners
     * @return void
     * */
    public void initOnChangedListner(){
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mLogin.setEnabled(!(mEmailField.getText().toString().equals("") |
                        mPasswordField.getText().toString().equals("")));

            }
        });

        mPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                mLogin.setEnabled(!(mEmailField.getText().toString().equals("") |
                        mPasswordField.getText().toString().equals("")));
            }
        });
    }
}