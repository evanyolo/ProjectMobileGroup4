package com.example.group4_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView nama;
    private List<labourer> labourerList;
    private LabourerAdapter la;
    private RecyclerView recyclerView;
    private LabourerAdapter adapter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.bottom_home){
                return true;
            } else if (item.getItemId() == R.id.bottom_article) {
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_profile) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });

        lists();
    }
    public void lists(){
        recyclerView = findViewById(R.id.recyclerViews);
        recyclerView.setHasFixedSize(true);

        labourerList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(dbs.urllabourerlist, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray array) {
                for(int i = 0; i<array.length(); i++){
                    try {
                        JSONObject object = array.getJSONObject(i);

                        String id = object.getString("labourer_id").trim();
                        String team_lab = object.getString("name_labourer").trim();
                        String categories = object.getString("category").trim();
                        String address = object.getString("address").trim();
                        String image = object.getString("image_labourer").trim();
                        String information = object.getString("information").trim();
                        int price = object.getInt("price");
                        Log.e("error", id);
                        Log.e("error", team_lab);
                        Log.e("error", categories);
                        Log.e("error", address);

                        labourerList.add(new labourer(id,team_lab,image,address,categories,information,price));
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                adapter = new LabourerAdapter(MainActivity.this,labourerList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error List", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

}