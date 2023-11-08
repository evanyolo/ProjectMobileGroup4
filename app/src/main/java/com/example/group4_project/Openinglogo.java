package com.example.group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import java.util.Timer;
import java.util.TimerTask;

public class Openinglogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openinglogo);
        Timer tm = new Timer();
        tm.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(Openinglogo.this, opening_page.class);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(i);
                finish();
            }
        },3000);

    }
}