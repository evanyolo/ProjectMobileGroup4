package com.example.group4_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class LabourerAdapter extends RecyclerView.Adapter<LabourerAdapter.labourerHold> {
    Context context;
    List<labourer> labourerList;

    public class labourerHold extends RecyclerView.ViewHolder {
      TextView name_team, address, price,image;
      LinearLayout listLabourer;
        public labourerHold(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imagelabourer);
            name_team = itemView.findViewById(R.id.namateamsinfo);
            address = itemView.findViewById(R.id.addressinfo);
            price = itemView.findViewById(R.id.priceinfo);
            listLabourer = itemView.findViewById(R.id.layoutInfo);
        }
    }
    public LabourerAdapter(Context context, List<labourer> labourerList) {
        this.context = context;
        this.labourerList = labourerList;
    }

    @NonNull
    @Override
    public labourerHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View labourerLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main,parent,false);

        return new labourerHold(labourerLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull labourerHold holder, int position) {
   final labourer labourer = labourerList.get(position);
    holder.name_team.setText(labourer.getTeam_labourer());

    holder.address.setText(labourer.getAddress());
    holder.price.setText(labourer.getPrice());
        //Glide.with(context).load(labourer.getImage_lab()).into(holder.image);

    holder.listLabourer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    }

    @Override
    public int getItemCount() {
        return labourerList.size();
    }


}