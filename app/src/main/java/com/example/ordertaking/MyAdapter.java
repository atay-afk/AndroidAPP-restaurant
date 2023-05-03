package com.example.ordertaking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.ordertaking.tools.Meal;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    public static ArrayList<Meal> list;

    public MyAdapter(Context context,ArrayList<Meal> list){
        this.context=context;
        this.list=list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.popup,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Meal meal= list.get(position);

        holder.designation.setText(meal.getDesignation());
        holder.designation.setChecked(list.get(position).getSelected());

        holder.description.setText(meal.getDescription());
        holder.prix.setText(String.valueOf(meal.getPrix())+" MAD");

        holder.designation.setTag(position);

        holder.designation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.designation.getTag();

                if (list.get(pos).getSelected()) {
                    list.get(pos).setSelected(false);
                } else {
                    list.get(pos).setSelected(true);
                    list.get(pos).setQuantity(Integer.parseInt(holder.quantite.getNumber()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView description,prix;
        CheckBox designation;
        ElegantNumberButton quantite;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            description=itemView.findViewById(R.id.info);
            designation=itemView.findViewById(R.id.desgn);
            prix=itemView.findViewById(R.id.prix);
            quantite=itemView.findViewById(R.id.number_button);


        }
    }



}
