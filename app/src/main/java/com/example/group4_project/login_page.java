package com.example.group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class login_page extends AppCompatActivity {

    private EditText etEmaillog, etPass;
    private String email, passwrd;
    private RequestQueue requestQueue;
    private Button btnLogins,signuppg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        signuppg = findViewById(R.id.toSignup);
        btnLogins = findViewById(R.id.tohomepage);

        etEmaillog = findViewById(R.id.inputEmailLog);
        etPass = findViewById(R.id.inputPassLog);

        register();
        logins();



        }

public void register(){
    signuppg.setOnClickListener(view -> {
        Intent i = new Intent(getApplicationContext(), signup_page.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    });
}

public void logins(){
    btnLogins.setOnClickListener(view -> {
        email = etEmaillog.getText().toString();
        passwrd = etPass.getText().toString();
        if (!email.isEmpty() && !passwrd.isEmpty()) { // Corrected condition
            StringRequest sr = new StringRequest(Request.Method.GET, dbs.urllogin + "?email=" + email + "&password=" + passwrd, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("Selamat Datang")) {
                        Intent i = new Intent(getApplicationContext(), homepage.class);
                        Toast.makeText(login_page.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    } else {
                        Toast.makeText(login_page.this, "Email dan password tidak sesuai", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }

            });
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(sr);

        } else {
            Toast.makeText(getApplicationContext(), "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
    });


}

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}