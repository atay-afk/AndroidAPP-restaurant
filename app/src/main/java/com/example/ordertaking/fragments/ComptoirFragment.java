package com.example.ordertaking.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ordertaking.AdapterConfirm;
import com.example.ordertaking.tools.Meal;
import com.example.ordertaking.MyAdapter;
import com.example.ordertaking.tools.Panier;
import com.example.ordertaking.R;
import com.example.ordertaking.tools.Table;
import com.example.ordertaking.database.dbAcess;
import com.example.ordertaking.login.User;
import com.example.ordertaking.navVertical.MainActivity2;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComptoirFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComptoirFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    Dialog myDialog;
    ImageView hamburger,tacos,plate,pizza,boisson,pates,sandwich,salade,delete,confirm;
    Button btn;

    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<Integer> adapterItems;
    ArrayList<Table> list=new ArrayList<>();
    ArrayList<Integer> items=new ArrayList<>();

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    AdapterConfirm adapter;

    Table table=new Table();
    Panier panier= Panier.getInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComptoirFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComptoirFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComptoirFragment newInstance(String param1, String param2) {
        ComptoirFragment fragment = new ComptoirFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comptoir, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
            myDialog=new Dialog(getActivity());

            hamburger=view.findViewById(R.id.hamburger);
            tacos=view.findViewById(R.id.tacos);
            plate=view.findViewById(R.id.plate);
            pizza=view.findViewById(R.id.pizza);
            boisson=view.findViewById(R.id.boisson);
            pates=view.findViewById(R.id.pates);
            sandwich=view.findViewById(R.id.sandwich);
            salade=view.findViewById(R.id.salade);

            autoCompleteTxt=view.findViewById(R.id.auto_complete);

            delete=view.findViewById(R.id.delete);
            confirm=view.findViewById(R.id.confirm);

            Meal.setNull();
            Meal.setMeals();

            for(Table table: dbAcess.tables){
                if(table.getResponsable().equals(User.getInstance().getUsername()) && table.getEtat().equals("non occupe")) {
                    list.add(table);
                    items.add(table.getNum());
                }
            }



            adapterItems=new ArrayAdapter<Integer>(getActivity(),R.layout.list_item,items);
            autoCompleteTxt.setAdapter(adapterItems);
            autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                    int item= (int) parent.getItemAtPosition(position);
                    table.setNum(item);

                }
            });



            hamburger.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowPopup(view,Meal.getBurgers());
                }
            });
            tacos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowPopup(view,Meal.getTacos());
                }
            });
            plate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowPopup(view,Meal.getPlats());
                }
            });
            pizza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowPopup(view,Meal.getPizzas());
                }
            });
            boisson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowPopup(view,Meal.getBoisson());
                }
            });
            pates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowPopup(view,Meal.getPasta());
                }
            });
            sandwich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowPopup(view,Meal.getSandwich());
                }
            });
            salade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowPopup(view,Meal.getSalades());
                }
            });


            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!panier.getList().isEmpty())
                        confirmPopup();
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    deletePopup(getActivity());
                }
            });





    }


    private void confirmPopup() {

        myDialog.setContentView(R.layout.itemslist);
        recyclerView=myDialog.findViewById(R.id.list);
        btn=myDialog.findViewById(R.id.btn);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        btn.setText("Confirmer la commande pour "+panier.getPrice()+"DHS");
        adapter=new AdapterConfirm(getActivity(),panier.getList(),btn);
        recyclerView.setAdapter(adapter);

        myDialog.show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAcess.updateTable("traitement",table.getNum());
                dbAcess.DBConnect();
                table.setEtat("traitement");
                table.setResponsable(User.getInstance().getUsername());
                panier.setTable(table);
                Panier.paniers.add(panier);
                Panier.setInstance(new Panier());
                backMenu();
            }
        });

    }

    private void backMenu(){
        Intent intent=new Intent(getActivity(), MainActivity2.class);
        startActivity(intent);
    }

    private void deletePopup(Activity a) {
        AlertDialog.Builder builder=new AlertDialog.Builder(a);
        //Set title
        builder.setTitle("Supprimer la commande actuelle ");
        builder.setMessage("Voulez vous supprimer la commande et revenir au menu principal");
        //Set positive button
        builder.setPositiveButton("CONFIRMER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Dismiss dialog
                panier.getList().clear();
                Panier.setInstance(panier);
                dialogInterface.dismiss();
                backMenu();
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Dismiss dialog
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    public void ShowPopup(View v,ArrayList<Meal> list){
        ImageView quit;

        myDialog.setContentView(R.layout.meallist);
        quit=myDialog.findViewById(R.id.quit);

        recyclerView=myDialog.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        myAdapter=new MyAdapter(getActivity(),list);
        recyclerView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                for(Meal meals:list) {
                    if (meals.isSelected) {
                        panier.addMeal(meals);
                    }
                }

            }
        });

        myDialog.show();

    }

}