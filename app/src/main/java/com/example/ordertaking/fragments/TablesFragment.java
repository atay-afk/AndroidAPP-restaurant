package com.example.ordertaking.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ordertaking.MyAdapter2;
import com.example.ordertaking.R;
import com.example.ordertaking.tools.Table;
import com.example.ordertaking.database.dbAcess;
import com.example.ordertaking.login.User;

import java.util.ArrayList;


public class TablesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    ArrayList<Table> list=new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TablesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TablesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TablesFragment newInstance(String param1, String param2) {
        TablesFragment fragment = new TablesFragment();
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
        return inflater.inflate(R.layout.fragment_tables, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        for(Table table: dbAcess.tables){
            if(table.getResponsable().equals(User.getInstance().getUsername())) {
                list.add(table);
            }
        }

        recyclerView=view.findViewById(R.id.list);
        MyAdapter2 myAdapter2=new MyAdapter2(getActivity(),list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter2);
    }
}