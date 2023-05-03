package com.example.ordertaking.editCommande;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordertaking.AdapterConfirm;
import com.example.ordertaking.tools.Panier;
import com.example.ordertaking.R;
import com.example.ordertaking.database.dbAcess;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    public static ArrayList<Panier> list;

    public MyAdapter(Context context, ArrayList<Panier> list){
        this.context=context;
        this.list=list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.editcommand,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Panier panier= list.get(position);

        holder.num.setText("TABLE "+panier.getTable().getNum());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPopup(position);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                Panier.paniers.remove(panier);
                notifyItemRemoved(position);
            }
        });
        holder.confirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                dbAcess.updateTable("traité",panier.getTable().getNum());
                dbAcess.DBConnect();
                list.remove(position);
                notifyItemRemoved(position);
                dbAcess.updateVente(panier);

            }
        });

    }

    private void editPopup(int position) {
        Dialog myDialog = new Dialog(context);
        RecyclerView recyclerView;
        AdapterConfirm adapter;
        Button btn ;

        myDialog.setContentView(R.layout.itemslist);
        recyclerView=myDialog.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        btn=myDialog.findViewById(R.id.btn);
        btn.setText("Confirmer la commande pour "+Panier.paniers.get(position).getPrice()+"DHS");
        adapter=new AdapterConfirm(context,Panier.paniers.get(position).getList(),btn);
        recyclerView.setAdapter(adapter);

        myDialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView num;
        ImageView edit,delete,confirm;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            num=itemView.findViewById(R.id.info);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
            confirm=itemView.findViewById(R.id.confirm);


        }
    }



}
