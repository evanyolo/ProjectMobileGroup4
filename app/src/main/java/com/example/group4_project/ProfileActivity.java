package com.example.group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    public void user(){

    }
    public void logout(){
        um = new UserManagement(this);
        um.checkLog();


        HashMap<String, String> u = um.userDetails();
        final String mEmail = u.get(um.emails);
        final String mName = u.get(um.names);
        final String mPhone = u.get(um.phones);


        logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(view -> {

            SharedPreferences sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove("email").commit();
            sharedPreferences.edit().remove("password").commit();
            sharedPreferences.edit().apply();
            Intent i = new Intent(getApplicationContext(), opening_page.class);
            startActivity(i);
            finish();
        });


    }
    public void show(){
        Intent i = getIntent();
        nama = i.getStringExtra("name_customer");
        email = i.getStringExtra("email");
        phone = i.getStringExtra("phone");

        name_tv = findViewById(R.id.profilenames);
        emails_tv = findViewById(R.id.profileemail);
        phones_tv = findViewById(R.id.profilephones);


    }
}