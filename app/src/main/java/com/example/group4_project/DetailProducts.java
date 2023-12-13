package com.example.group4_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailProducts extends AppCompatActivity {
    TextView nama_team, address,category,information,price;
ImageView image;
Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_products);

        nama_team = findViewById(R.id.team_details);
        address = findViewById(R.id.addressDetails);
        category= findViewById(R.id.categories);
        image = findViewById(R.id.imageViewDetails);
        information = findViewById(R.id.informationDetail);
        price = findViewById(R.id.hargadetails);
        btnPay = findViewById(R.id.paymentBtn);


        nama_team.setText(labourer.getTeam_labourer());
        address.setText(labourer.getAddress());
        category.setText(labourer.getCategory());
        information.setText(labourer.getInformation());
        Glide.with(this).load(labourer.getImage_lab()).into(image);
        price.setText(String.valueOf(labourer.getPrice()));

        payment();
    }

    public void payment(){
        btnPay.setOnClickListener(view -> {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(dbs.paymenturls, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray array) {

                    try {
                        for(int i = 0; i<array.length(); i++){
                            JSONObject object = array.getJSONObject(i);
                            String id = object.getString("payment_id");
                            String method_pay = object.getString("method_payment").trim();
                            String image = object.getString("picture_payment").trim();

                            Log.e("error",id);
                            Log.e("error", method_pay);
                            Log.e("error",image);
                            paymentcons.setMethod_payment(id);
                            paymentcons.setPicPayment(image);
                            paymentcons.setMethod_payment(method_pay);

                        }
                        Intent intent = new Intent(DetailProducts.this, toPaymenst.class);
                        intent.putExtra("price",labourer.getPrice());

                        startActivity(intent);

                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error List", Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(DetailProducts.this);
            requestQueue.add(jsonArrayRequest);

        });
    }
}