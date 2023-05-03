package com.example.ordertaking.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ordertaking.R;
import com.example.ordertaking.database.dbAcess;
import com.example.ordertaking.login.User;
import com.example.ordertaking.navVertical.MainActivity2;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText nom,prenom,adresse,tel;
    Button save;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
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
        return inflater.inflate(R.layout.fragment_profil, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        nom=view.findViewById(R.id.nom);
        prenom=view.findViewById(R.id.prenom);
        tel=view.findViewById(R.id.numtel);
        adresse=view.findViewById(R.id.adresse);
        save=view.findViewById(R.id.save);

        nom.setText(User.getInstance().getNom());
        prenom.setText(User.getInstance().getPrenom());
        tel.setText(User.getInstance().getTele());
        adresse.setText(User.getInstance().getAdresse());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAcess.updateUser(nom.getText().toString().trim(),prenom.getText().toString().trim(),adresse.getText().toString().trim(),tel.getText().toString().trim(),User.getInstance().getUsername());
                dbAcess.DBConnect();
                User.getInstance().setNom(nom.getText().toString().trim());
                User.getInstance().setPrenom(prenom.getText().toString().trim());
                mainActivity();

                Toast.makeText(getActivity(),"MODIFICATION ENREGISTREE",Toast.LENGTH_SHORT);
            }
        });


    }
    private void mainActivity() {
        Intent intent=new Intent(getActivity(), MainActivity2.class);
        startActivity(intent);
    }

}