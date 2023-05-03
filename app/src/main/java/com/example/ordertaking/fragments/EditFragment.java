package com.example.ordertaking.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ordertaking.tools.Panier;
import com.example.ordertaking.R;
import com.example.ordertaking.editCommande.MyAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    TextView text;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
        return inflater.inflate(R.layout.fragment_edit,null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        text=view.findViewById(R.id.text);
        recyclerView=view.findViewById(R.id.list);


        if(Panier.paniers.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            text.setVisibility(View.VISIBLE);
        }

        MyAdapter myAdapter=new MyAdapter(getActivity(), Panier.paniers);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter);
    }

}