package com.example.tumblr4u.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tumblr4u.R;

/**
 * Splash screen begin at the start of the program and disappear within seconds
 * */
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent intent = new Intent(SplashScreen.this, Authentication.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}