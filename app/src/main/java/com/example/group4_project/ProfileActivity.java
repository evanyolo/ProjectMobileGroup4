package com.example.group4_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

public class ProfileActivity extends AppCompatActivity {
    TextView name_tv, emails_tv , phones_tv;
    Button logoutBtn,btnEdit, delAccount;
    String id = profiless.getId();
    UserManagement um;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        btnEdit = findViewById(R.id.editprofilebtn);
        delAccount = findViewById(R.id.delAccountbtn);
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
        editProf();
        maps();
        logout();
        setDelAccount();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshUserInfo();

    }
    private void refreshUserInfo() {

        String updatedName = profiless.getEmail();
        String updatedEmail = profiless.getEmail();
        String updatedPhone = profiless.getPhone();

        // Update the TextViews with the latest user information
        name_tv.setText(updatedName);
        emails_tv.setText(updatedEmail);
        phones_tv.setText(updatedPhone);
    }

    public void setDelAccount(){
        delAccount.setOnClickListener(view -> {
            AlertDialog.Builder Ad = new AlertDialog.Builder(this);
            Ad.setTitle("Delete Account");
            Ad.setMessage("Are you want to Delete Account? \n"+profiless.getNama());
            Ad.setPositiveButton("Yes", (dialogInterface, i) -> {
                StringRequest sr = new StringRequest(Request.Method.POST, dbs.urlDelAccount, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            int check = object.getInt("success");
                            if(check == 1){
                                Intent i = new Intent(getApplicationContext(), login_page.class);
                                Toast.makeText(getApplicationContext(), "Success Delete Account "+profiless.getId(), Toast.LENGTH_SHORT).show();
                                startActivity(i);
                            }else if (check == 0){
                                Toast.makeText(getApplicationContext(), "Failed",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                ){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> del= new HashMap<>();
                        del.put("account_id",id);
                        return del;
                    }
                };
          RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
          queue.add(sr);

            });
            Ad.setNegativeButton("No",(dialogInterface, i) -> {
                Toast.makeText(getApplicationContext(), "Canceled ", Toast.LENGTH_SHORT).show();
            });
            Ad.show();

        });

    }

    public void editProf(){
        btnEdit.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), editProfile.class);
            startActivity(i);
            onResume();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

    }
    public void maps(){
        Button btn = findViewById(R.id.aboutusbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsAbout.class);
                startActivity(i);
            }
        });
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
            ad.setNegativeButton("No",(dialogInterface, i) -> Toast.makeText(this, "Log out failed",Toast.LENGTH_SHORT).show());
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