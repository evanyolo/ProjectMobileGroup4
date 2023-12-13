package com.example.group4_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class LabourerAdapter extends RecyclerView.Adapter<LabourerAdapter.labourerHold> {
    Context context;
    List<labourer> labourerList;

    public class labourerHold extends RecyclerView.ViewHolder {
      TextView name_team, address, price;
      ImageView image;

      CardView cardView;
        public labourerHold(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imagelabourer);
            name_team = itemView.findViewById(R.id.namateamsinfos);
            address = itemView.findViewById(R.id.addressinfos);
            price = itemView.findViewById(R.id.priceinfos);

        }
    }
    public LabourerAdapter(Context context, List<labourer> labourerList) {
        this.context = context;
        this.labourerList = labourerList;
    }

    @NonNull
    @Override
    public labourerHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new labourerHold(LayoutInflater.from(context).inflate(R.layout.labourerlistt,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull labourerHold holder, int position) {
        labourer labourers = labourerList.get(position);
    holder.name_team.setText(labourers.getTeam_labourer());
    holder.address.setText(labourers.getCategory());
    holder.price.setText(String.valueOf(labourers.getPrice()));
    Glide.with(context).load(labourers.getImage_lab()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return labourerList.size();
    }


}