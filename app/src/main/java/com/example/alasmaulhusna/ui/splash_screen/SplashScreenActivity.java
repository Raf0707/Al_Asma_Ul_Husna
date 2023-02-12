package com.example.alasmaulhusna.ui.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.alasmaulhusna.MainActivity;
import com.example.alasmaulhusna.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(
                    SplashScreenActivity.this, MainActivity.class));
            finish();
        }, 3000);
    }
}