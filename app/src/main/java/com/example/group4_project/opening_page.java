package com.example.group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.Button;

public class opening_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_page);

        Button loginpage = findViewById(R.id.tologin);
        loginpage.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), login_page.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
}