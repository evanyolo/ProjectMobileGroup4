package com.example.group4_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    TextView name_tv, emails_tv , phones_tv;
    String nama, email, phone;
    Button logoutBtn,btnEdit;
    UserManagement um;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        btnEdit = findViewById(R.id.editprofilebtn);
        btnEdit.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), editProfile.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

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
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Log out?");
            ad.setMessage("Are you sure want to Log out?");
            ad.setPositiveButton("Yes", (dialogInterface, i) -> {
                Toast.makeText(this, "Log out",Toast.LENGTH_SHORT).show();
                    um.checkLog();
                    um.logout(logoutBtn);


            });
            ad.setNegativeButton("No",(dialogInterface, i) -> {
                Toast.makeText(this, "Log out failed",Toast.LENGTH_SHORT).show();
            });
            ad.show();
        });
    }
    public void show(){
        name_tv = findViewById(R.id.profilenames);
        emails_tv = findViewById(R.id.profileemail);
        phones_tv = findViewById(R.id.profilephones);
             name_tv.setText(profiless.getNama());
             emails_tv.setText(profiless.getEmail());
             phones_tv.setText(profiless.getPhone());

    }
}