package com.example.group4_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class login_page extends AppCompatActivity {

    private EditText etEmail, etPass;
    private String email, pass;
    private RequestQueue requestQueue;
    private String url ="http://192.168.1.11/File Mobile/projek/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Button signuppg = findViewById(R.id.toSignup);
        signuppg.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), signup_page.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });




    }
    public void login (View v){
        email = etEmail.getText().toString().trim();
        pass = etPass.getText().toString().trim();
        if (!email.equals("") && !pass.equals("")) {
            StringRequest sr = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("success")) {
                                Intent i = new Intent(login_page.this, homepage.class);
                                startActivity(i);
                                finish();
                            } else if (response.equals("failed")) {
                                Toast.makeText(login_page.this, "Invalid login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle Volley error here (e.g., display an error message)
                            Toast.makeText(login_page.this, "Error occurred", Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email",email);
                    data.put("password",pass);

                    return data;
                }
            };
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(sr);
        }else {
            Toast.makeText(this, "Fields Can not be empty",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}