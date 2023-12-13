package com.example.group4_project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class LabourerAdapter extends RecyclerView.Adapter<LabourerAdapter.labourerHold> {
    Context context;
    List<labourer> labourerList;
    RecyclerView rView;

    public class labourerHold extends RecyclerView.ViewHolder {
      TextView name_team, address, price;
      ImageView image;


        public labourerHold(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imagelabourer);
            name_team = itemView.findViewById(R.id.namateamsinfos);
            address = itemView.findViewById(R.id.addressinfos);
            price = itemView.findViewById(R.id.priceinfos);
            rView = itemView.findViewById(R.id.recyclerViews);


        }
    }
    public LabourerAdapter(Context context, List<labourer> labourerList) {
        this.context = context;
        this.labourerList = labourerList;
    }

    @NonNull
    @Override
    public labourerHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View labourerLayout = LayoutInflater.from(context).inflate(R.layout.labourerlistt,parent,false);
        return new labourerHold(labourerLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull labourerHold holder, int position) {
             String nama = labourer.getTeam_labourer();

            labourer labourers = labourerList.get(position);

            holder.name_team.setText(nama);
            holder.address.setText(labourers.getCategory());
            holder.price.setText(String.valueOf(labourers.getPrice()));
            Glide.with(context).load(labourers.getImage_lab()).into(holder.image);
            holder.itemView.setOnClickListener(view -> {
                labourer labourerss = labourerList.get(position);
                Intent i = new Intent(context, DetailProducts.class);
                i.putExtra("name_labourer",labourerss.getTeam_labourer());
                i.putExtra("category",labourerss.getCategory());
                i.putExtra("address",labourerss.getAddress());
                i.putExtra("image_labourer",labourerss.getImage_lab());
                i.putExtra("information",labourerss.getInformation());
                i.putExtra("price",labourerss.getPrice());
                Log.e("error",labourerss.getTeam_labourer());
                context.startActivity(i);
            });

    }

    @Override
    public int getItemCount() {
        return labourerList.size();
    }


}