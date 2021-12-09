package com.example.tumblr4u.View;

/**
 * Sign up with email activity
 * @author Omar Ahmed
 * @version 1.0
 * */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.tumblr4u.GeneralPurpose.InputMinMaxFilter;
import com.example.tumblr4u.R;

public class SignupWithEmail extends AppCompatActivity {

    private EditText mAgeField;
    private ImageButton mBackButton;
    private Button mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_with_email);

        initViews();
        initOnClickListner();
        initOnChangeListner();

        mNextButton.setEnabled(false);

    }

    /**
     * Assign the views to their xml files
     * @return void
     * */
    public void initViews(){
        mAgeField = (EditText)findViewById(R.id.signup_with_email_edit_text_field);
        mBackButton = (ImageButton)findViewById(R.id.signup_with_email_back_button);
        mNextButton = (Button)findViewById(R.id.signup_with_email_next_button);
        mAgeField.setFilters(new InputFilter[] {new InputMinMaxFilter(1, 100)});

    }

    /**
     * Assign the views to their click listner
     * @return void
     * */
    public void initOnClickListner(){
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent authenticationActivity = new Intent(getApplicationContext(), Authentication.class);
                startActivity(authenticationActivity);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeActivity);
            }
        });
    }

    /**
     * Assign the views to their change listner
     * @return void
     * */
    public void initOnChangeListner(){
        mAgeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                mNextButton.setEnabled(!mAgeField.getText().toString().equals(""));

            }
        });

    }
}