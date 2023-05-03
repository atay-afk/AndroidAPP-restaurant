package com.example.ordertaking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ordertaking.tools.Meal;
import com.example.ordertaking.tools.Panier;

import java.util.ArrayList;

public class AdapterConfirm extends RecyclerView.Adapter<AdapterConfirm.MyViewHolder> {

    Context context;
    Button b;
    public static ArrayList<Meal> list;

    public AdapterConfirm(Context context, ArrayList<Meal> list, Button b){
        this.context=context;
        this.list=list;

        this.b=b;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.productlist,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Meal meal= list.get(position);

        holder.designation.setText(meal.getDesignation());
        holder.quantity.setText(String.valueOf(meal.getQuantity()));
        holder.prix.setText(String.valueOf(meal.getPrix())+" MAD");
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);

                Panier.getInstance().delMeal(meal);
                notifyItemRemoved(position);
                b.setText("Terminer la commande pour "+Panier.getInstance().getPrice()+"DHS");

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView designation,prix,quantity;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            designation=itemView.findViewById(R.id.prod);
            prix=itemView.findViewById(R.id.prix);
            img=itemView.findViewById(R.id.delete);
            quantity=itemView.findViewById(R.id.quantity);

        }
    }

}
