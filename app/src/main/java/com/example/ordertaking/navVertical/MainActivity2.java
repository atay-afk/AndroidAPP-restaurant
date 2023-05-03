package com.example.ordertaking.navVertical;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.ordertaking.R;
import com.example.ordertaking.login.User;
import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final DrawerLayout drawerLayout=findViewById(R.id.drawerlayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView=findViewById(R.id.navigationView);


        navigationView.setItemIconTintList(null);

        NavController navController= Navigation.findNavController(this,R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        View header = navigationView.getHeaderView(0);
        TextView nom = (TextView) header.findViewById(R.id.nom);

        nom.setText(User.getInstance().getNom()+" "+User.getInstance().getPrenom());

    }
}