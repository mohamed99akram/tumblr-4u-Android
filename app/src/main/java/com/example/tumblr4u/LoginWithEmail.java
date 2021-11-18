package com.example.tumblr4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginWithEmail extends AppCompatActivity {

    private Button mLogin;
    private ImageButton mBack;
    private EditText mEmailField;
    private EditText mPasswordField;
    private LoginWithEmailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);

        mLogin = (Button)findViewById(R.id.login_with_email_login_button);
        mBack = (ImageButton) findViewById(R.id.login_with_email_back_button);
        mEmailField = (EditText) findViewById(R.id.login_with_email_email_field);
        mPasswordField = (EditText) findViewById(R.id.login_with_email_password_field);
        mViewModel = new ViewModelProvider(this).get(LoginWithEmailViewModel.class);
        mLogin.setEnabled(false);

        // set the buttons event listner
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent authenticationActivity = new Intent(getApplicationContext(), authentication.class);
                startActivity(authenticationActivity);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.login(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        });

        mViewModel.isValidEmailAndPassword.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(!mViewModel.isValidEmailAndPassword.getValue()) { return; }
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(home);
            }
        });

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