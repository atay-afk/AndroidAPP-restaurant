package com.example.ordertaking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ordertaking.login.loginActivity;

public class IntroductoryActivity extends AppCompatActivity {

    ImageView logo,background,text;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        logo=findViewById(R.id.logo);
        background=findViewById(R.id.background);
        lottieAnimationView=findViewById(R.id.lottie);
        text=findViewById(R.id.text);

        background.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        text.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IntroductoryActivity.this, loginActivity.class));
                finish();
            }
        },5000);
    }

}