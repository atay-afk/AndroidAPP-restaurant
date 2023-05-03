package com.example.ordertaking.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ordertaking.R;
import com.example.ordertaking.tools.MediaClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Spinner spinner1;
    SwitchCompat switch_btn;
    CheckBox checkBox;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public settingsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static settingsFragment newInstance(String param1, String param2) {
        settingsFragment fragment = new settingsFragment();
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
        return inflater.inflate(R.layout.fragment_settings, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        switch_btn = view.findViewById(R.id.switch1);
        checkBox = (CheckBox) view.findViewById(R.id.checkbox);

        boolean checked = PreferenceManager.getDefaultSharedPreferences(getActivity())
                .getBoolean("checkBox", false);
        checkBox.setChecked(checked);

        if(checkBox.isChecked()){
            MediaPlayer mediaPlayer=new MediaPlayer();
            MediaClick.setMediaPlayer(mediaPlayer);
        }

        spinner1=view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1= ArrayAdapter.createFromResource(getActivity(),R.array.sounds, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(this);


        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switch_btn.setText("Activé");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switch_btn.setText("Descativé");
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        String text=parent.getItemAtPosition(i).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();

        if(text.equals("summer")){

            MediaPlayer mediaPlayer=MediaPlayer.create(getActivity(), R.raw.summer);
            MediaClick.setMediaPlayer(mediaPlayer);
            MediaClick.start();
        }else if(text.equals("twinkle")){

            MediaPlayer mediaPlayer=MediaPlayer.create(getActivity(), R.raw.twinkle);
            MediaClick.setMediaPlayer(mediaPlayer);
            MediaClick.start();
        }else if(text.equals("whistle")){

            MediaPlayer mediaPlayer=MediaPlayer.create(getActivity(), R.raw.whistle);
            MediaClick.setMediaPlayer(mediaPlayer);
            MediaClick.start();
        }else if(text.equals("zen")){

            MediaPlayer mediaPlayer=MediaPlayer.create(getActivity(), R.raw.zen);
            MediaClick.setMediaPlayer(mediaPlayer);
            MediaClick.start();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox:
                PreferenceManager.getDefaultSharedPreferences(getActivity()).edit()
                        .putBoolean("checkBox", checked).commit();
                break;
        }
    }
}