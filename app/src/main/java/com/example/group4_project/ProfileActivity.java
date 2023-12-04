package com.example.group4_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    TextView name_tv, emails_tv , phones_tv;
    String nama, email, phone;

    Button logoutBtn;
    UserManagement um;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.bottom_home){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_article) {
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                return true;
            }
            return false;
        });
        show();
        logout();


    }

    public void logout(){



        logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(view -> {
            um = new UserManagement(this);
            um.checkLog();


            HashMap<String, String> u = um.userDetails();
            final String mEmail = u.get(um.emails);
            final String mName = u.get(um.names);
            final String mPhone = u.get(um.phones);

            AlertDialog.Builder ad = new AlertDialog.Builder(this);

            ad.setTitle("Log out?");
            ad.setMessage("Are you sure want to Log out?");
            ad.setPositiveButton("Yes", (dialogInterface, i) -> {
                um.logout();
            });
            ad.setNegativeButton("No",(dialogInterface, i) -> {
                Toast.makeText(this, "Log out failed",Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            });

        });


    }
    public void show(){
        Intent i = getIntent();
        name_tv = findViewById(R.id.profilenames);
        emails_tv = findViewById(R.id.profileemail);
        phones_tv = findViewById(R.id.profilephones);
         if (i != null) {
             final String mName = i.getStringExtra("name_customer");
             final String mEmails = i.getStringExtra("email");
             final String mPhone = i.getStringExtra("phone");

        if (mName != null) {
            name_tv.setText(mName);
        }
        if (mEmails != null) {
            emails_tv.setText(mEmails);
        }
        if (mPhone != null) {
            phones_tv.setText(mPhone);
        }
    }
    }
}