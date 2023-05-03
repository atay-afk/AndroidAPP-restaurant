package com.example.ordertaking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ordertaking.database.dbAcess;
import com.example.ordertaking.tools.Table;

import java.util.ArrayList;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    Context context;

    public static ArrayList<Table> list;

    public MyAdapter2(Context context,ArrayList<Table> list){
        this.context=context;
        this.list=list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.tablelist,parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Table table= list.get(position);

        holder.table.setText("Table "+table.getNum());
        holder.etat.setText(table.getEtat());
       // holder.etat.setTextColor(R.color.blue);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAcess.updateTable("non occupe",table.getNum());
                dbAcess.DBConnect();
                holder.etat.setText("non occupe");
                list.get(position).setEtat("non occupe");


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView table,etat;
        Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            table=itemView.findViewById(R.id.info);
            etat=itemView.findViewById(R.id.etat);
            btn=itemView.findViewById(R.id.button);

        }
    }

}
