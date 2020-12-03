package com.example.task31fixes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myCuisineAdapter extends RecyclerView.Adapter<myCuisineAdapter.MyViewHolder> {
  ArrayList<Cuisine> checkedCuisines= new ArrayList<>();
  Context c;
  Cuisine[] cuisines;


  public myCuisineAdapter(Context c, Cuisine[] cuisines, ArrayList<Cuisine> cuisineList){

      this.c=c;
      this.cuisines=cuisines;
      this.checkedCuisines=cuisineList;
  }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
      /*Integer im=cuisineList.get(position).getImage();
      String na=cuisineList.get(position).getName();
      holder.ss.setText(na);
      holder.imff.setImageResource(im);
      holder.nn.setChecked(cuisineList.get(position).isSelected());
*/
        final Cuisine cuisine = cuisines[position];
        holder.ss.setText(cuisine.getName());
        holder.nn.setChecked(cuisine.isSelected());
        holder.imff.setImageResource(cuisine.getImage());

        holder.setItemClickListener(new MyViewHolder.ItemClickListener() {
            @Override
            public void onItemClick(View v) {
                CheckBox myCheckBox = (CheckBox)v;
                Cuisine currentCuisine = cuisines[position];

                if(myCheckBox.isChecked()){
                    currentCuisine.setSelected(true);
                    checkedCuisines.add(currentCuisine);
                }else if(!myCheckBox.isChecked()){
                    currentCuisine.setSelected(false);
                    checkedCuisines.remove(currentCuisine);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cuisines.length;
    }

  public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
      private CheckBox nn;
      private ImageView imff;
      private TextView ss;

      ItemClickListener itemClickListener;

      public MyViewHolder(View view){
          super(view);
          nn=view.findViewById(R.id.food1);
          ss=view.findViewById(R.id.tez);
          imff=view.findViewById(R.id.imageView);

          imff.setOnClickListener(this);

      }

      public void setItemClickListener(ItemClickListener itemClickListener){
          this.itemClickListener=itemClickListener;
      }

      @Override
      public void onClick(View v) {
          this.itemClickListener.onItemClick(v);
      }

      interface  ItemClickListener{
          void onItemClick(View v);
      }
  }

    @NonNull
    @Override
    public myCuisineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cuisine_item, parent, false);
        MyViewHolder holder=new MyViewHolder(itemView);
        return holder;
    }




}
