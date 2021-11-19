package com.example.tumblr4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tumblr4u.additional.InputMinMaxFilter;

public class SignupWithEmail extends AppCompatActivity {

    EditText ageField;
    ImageButton backButton;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_with_email);

        ageField = (EditText)findViewById(R.id.signup_with_email_edit_text_field);
        backButton = (ImageButton)findViewById(R.id.signup_with_email_back_button);
        nextButton = (Button)findViewById(R.id.signup_with_email_next_button);
        ageField.setFilters(new InputFilter[] {new InputMinMaxFilter(1, 100)});

        // deactivate the next button while the input field is empty
        nextButton.setEnabled(false);

        ageField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                nextButton.setEnabled(!ageField.getText().toString().equals(""));

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent authenticationActivity = new Intent(getApplicationContext(), authentication.class);
                startActivity(authenticationActivity);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(homeActivity);
            }
        });
    }
}