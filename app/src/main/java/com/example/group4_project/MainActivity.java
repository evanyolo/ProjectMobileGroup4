package com.example.group4_project;

import androidx.annotation.Nullable;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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
        recyclerView = findViewById(R.id.recyclerView);

        labourerList = new ArrayList<labourer>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        labourerList = new ArrayList<labourer>();
        StringRequest sr = new StringRequest(Request.Method.GET, dbs.urllabourerlist,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for(int i =0; i<array.length();i++){
                                JSONObject object = array.getJSONObject(i);

                                String id = object.getString("labourer_id");
                                String team_lab = object.getString("name_labourer");
                                String categories= object.getString("category");
                                String image = object.getString("image_labourer");
                                String information = object.getString("information");
                                int price = object.getInt("price");
                                Log.e("error",id);
                                Log.e("error",team_lab);
                                Log.e("error",categories);

                                labourer labourers = new labourer();
                                labourers.setTeam_labourer(team_lab);
                                labourers.setCategory(categories);
                                labourers.setPrice(price);
                                labourers.setImage_lab(image);
                                labourers.setInformation(information);
                                labourerList.add(labourers);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                       adapter = new LabourerAdapter(MainActivity.this,labourerList);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }

        });
        Volley.newRequestQueue(getApplicationContext()).add(sr);
    }

}