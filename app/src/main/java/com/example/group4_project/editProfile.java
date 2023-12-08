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

import java.util.HashMap;
import java.util.Map;

public class editProfile extends AppCompatActivity {
    Button edit;
    EditText edEmail, edName,edPhone;
    String nama,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        edit = findViewById(R.id.btnEdit);
        edEmail = findViewById(R.id.edtEmails);
        edName = findViewById(R.id.edtName);
        edPhone = findViewById(R.id.edtPhone);
        editProfiles();
    }
    public void editProfiles(){
        nama = profiless.getNama().toString();
        edName.setText(nama);

        email = profiless.getEmail().toString();
        edEmail.setText(email);


        phone = profiless.getPhone().toString();
        edPhone.setText(phone);
        edit.setOnClickListener(view -> {
            String updName = edName.getText().toString().trim();
            String updEmail = edEmail.getText().toString().trim();
            String upPhone = edPhone.getText().toString().trim();

            if(updName.isEmpty()||updEmail.isEmpty()||upPhone.isEmpty()){
                Toast.makeText(this,"Some Fields is Empty",Toast.LENGTH_SHORT).show();
            }else{
                StringRequest sr = new StringRequest(Request.Method.POST, dbs.urlEditProfile,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(editProfile.this,response.toString(),Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(editProfile.this, error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> upd = new HashMap<>();
                        upd.put("name_customer",updName);
                        upd.put("email",updEmail);
                        upd.put("phone",upPhone);
                        return upd;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(editProfile.this);
                queue.add(sr);
            }
        });
    }

}