package com.example.task31fixes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder>{

    private ArrayList<Cafe> cafeList;
    private Context context;

    public recyclerAdapter(ArrayList<Cafe> cafeList, Context context){
        this.cafeList=cafeList;
        this.context=context;
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cafe_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        Cafe caf=cafeList.get(position);
        holder.cafe_nm.setText(caf.getCafe_name());
        holder.cafe_address.setText(caf.getCafe_address());
        holder.cafe_opening.setText(caf.getCafe_opening_hrs());
        holder.cafe_like.setText(caf.getCafe_like());

        if(caf.getCafe_name().equals("Serendipity Cafe")){
            holder.cafe_icon.setImageResource(R.drawable.cafe1_icon);
        }
        else if(caf.getCafe_name().equals("Old Asian Cafe")){
            holder.cafe_icon.setImageResource(R.drawable.cafe2_icon);
        }
        else if(caf.getCafe_name().equals("International Taste Restaurant")){
            holder.cafe_icon.setImageResource(R.drawable.cafe3_icon);
        }
        else if(caf.getCafe_name().equals("Gardenhouse Cafe")){
            holder.cafe_icon.setImageResource(R.drawable.cafe4_icon);
        }
        else if(caf.getCafe_name().equals("Spice Cafe")){
            holder.cafe_icon.setImageResource(R.drawable.cafe5_icon);
        }

    }

    @Override
    public int getItemCount() {
        return cafeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView cafe_nm, cafe_address, cafe_opening, cafe_like;
        public ImageView cafe_icon, cafe_likecon, cafe_loc;

        public MyViewHolder(View itemView){
            super(itemView);
            cafe_nm=itemView.findViewById(R.id.cafe_name);
            cafe_address=itemView.findViewById(R.id.cafe_address);
            cafe_opening=itemView.findViewById(R.id.cafe_opening_hrs);
            cafe_like=itemView.findViewById(R.id.cafe_like);
            cafe_likecon=itemView.findViewById(R.id.like_icon);
            cafe_loc=itemView.findViewById(R.id.location_icon);
            cafe_icon=itemView.findViewById(R.id.cafe_icon);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            Bundle bundle=new Bundle();
            bundle.putParcelable("cafe", cafeList.get(position));
            Intent intent=new Intent();
            intent.putExtras(bundle);
            intent.setClass(context, Main2Activity.class);
            ((Activity)context).startActivityForResult(intent,1);
        }
    }
}

