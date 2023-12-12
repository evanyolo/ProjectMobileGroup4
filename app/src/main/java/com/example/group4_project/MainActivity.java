package com.example.group4_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private List<OrderListModel> orderListModels;
    private List<labourer> items;
    private RecyclerView.Adapter rAdapter;
    private RecyclerView recyclerView;
    private LabourerAdapter adapter;
    //private CategoryAdapter categoryAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        recyclerView = findViewById(R.id.recyclerViewLabourer);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderListModels = new ArrayList<>();


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
                                labourer labourer = new labourer(id ,team_lab,image,categories, information, price);

                                items.add(labourer);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                rAdapter = new LabourerAdapter(getApplicationContext(),items);
                        recyclerView.setAdapter(rAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}