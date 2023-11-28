package com.example.group4_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class signup_page extends AppCompatActivity {

private EditText etUsername, etEmail,etPass, etNumber,etRepassword;
private RequestQueue requestQueue;
private TextView tvStat;
private String url ="http://192.168.1.11/File Mobile/projek/register.php";
private String username, email, password, number, repass;
Button btnRegis;
Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        etUsername = findViewById(R.id.inputUsername);
        etEmail = findViewById(R.id.inputEmail);
        etPass = findViewById(R.id.inputPassword);
        etNumber = findViewById(R.id.inputPhone);
        btnRegis = findViewById(R.id.btnRegis);
        tvStat = findViewById(R.id.tvstatus);
        etRepassword = findViewById(R.id.inputRepass);
        btnRegis = findViewById(R.id.btnRegis);

    }
    public void save (View v){
       username = etUsername.getText().toString().trim();
       email = etEmail.getText().toString().trim();
       password = etPass.getText().toString().trim();
       number = etNumber.getText().toString().trim();
       repass = etRepassword.getText().toString().trim();
       if(!password.equals(repass)){
           Toast.makeText(this,"Password didn't match",Toast.LENGTH_SHORT).show();
       } else if (!username.equals("")&&!email.equals("")&&!password.equals("")) {
           StringRequest sr = new StringRequest(Request.Method.POST, url,
                   new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           if (response.equals("success")) {
                                tvStat.setText("Successfully registered");
                                btnRegis.setClickable(false);
                           } else if (response.equals("failed")) {
                               tvStat.setText("Something wrong");

                           }
                       }
                   },
                   new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           // Handle Volley error here (e.g., display an error message)
                           Toast.makeText(signup_page.this, "Error occurred", Toast.LENGTH_SHORT).show();
                       }
                   }){
               @Nullable
               @Override
               protected Map<String, String> getParams() throws AuthFailureError {
                   Map<String, String> data = new HashMap<>();
                   data.put("name_customer",username);
                   data.put("email",email);
                   data.put("phone",number);
                   data.put("password",password);
                   return data;
               }
           };
           requestQueue = Volley.newRequestQueue(getApplicationContext());
           requestQueue.add(sr);
       }


    }


}