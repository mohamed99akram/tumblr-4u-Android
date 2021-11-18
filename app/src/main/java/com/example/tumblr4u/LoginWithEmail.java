package com.example.tumblr4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class LoginWithEmail extends AppCompatActivity {

    private Button mLogin;
    private ImageButton mBack;
    private Button mContinue;
    private EditText mEmailField;
    private EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);

        mLogin = (Button)findViewById(R.id.login_with_email_login_button);
        mBack = (ImageButton) findViewById(R.id.login_with_email_back_button);
        mContinue = (Button)findViewById(R.id.login_with_email_continue_button);
        mEmailField = (EditText) findViewById(R.id.login_with_email_email_field);
        mPasswordField = (EditText) findViewById(R.id.login_with_email_password_field);

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
                Intent homeActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeActivity);
            }
        });
    }
}