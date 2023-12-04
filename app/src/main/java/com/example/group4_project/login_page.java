package com.example.group4_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

//        if (response.equals("1") ) {
//            Intent i = new Intent(getApplicationContext(), MainActivity.class);
//            Toast.makeText(login_page.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
//            startActivity(i);
//            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        } else {
//            Toast.makeText(login_page.this, "Email dan password tidak sesuai", Toast.LENGTH_SHORT).show();
//        }


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
        if (!email.isEmpty() && !passwrd.isEmpty()) {
            StringRequest sr = new StringRequest(Request.Method.POST, dbs.urllogin + "?email=" + email + "&password=" + passwrd, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject js = new JSONObject(response);
                        String r = js.getString("status");
                        JSONArray jsonArray = js.getJSONArray("data");

                        if (r.equals("success")) {
                            // Assuming you're expecting only one object in the array
                            if (jsonArray.length() > 0) {
                                JSONObject o = jsonArray.getJSONObject(0);
                                String name = o.getString("name_customer");
                                String email = o.getString("email");
                                String phone = o.getString("phone");

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("name_customer", name);
                                intent.putExtra("email", email);
                                intent.putExtra("phone", phone);

                                Toast.makeText(login_page.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                finish();
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else {
                                // Handle case when no data is present in the array
                                Toast.makeText(login_page.this, "No data found", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(login_page.this, "Email dan password tidak sesuai", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(login_page.this, "Email dan password Salah", Toast.LENGTH_SHORT).show();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }

            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email",email);
                    params.put("password",passwrd);
                    return params;
                }
            };
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