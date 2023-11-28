package com.example.group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
private String name_cus, email, password, phones, repass;
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

        btnRegis.setOnClickListener(view -> {
            name_cus = etUsername.getText().toString();
            email = etEmail.getText().toString();
            password = etPass.getText().toString();
            repass = etRepassword.getText().toString();
            phones = etNumber.getText().toString();
            if(!password.equals(repass)){
                Toast.makeText(this,"Password didn't match",Toast.LENGTH_SHORT).show();
            } else if (name_cus.isEmpty()||email.isEmpty()|| password.isEmpty()|| phones.isEmpty())  {
                StringRequest sr = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(signup_page.this, response.toString(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),login_page.class));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle Volley error here (e.g., display an error message)
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("name_customer", name_cus);
                        data.put("email",email);
                        data.put("phone", phones);
                        data.put("password",password);
                        return data;
                    }
                };
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(sr);
            }

        });

    }
}