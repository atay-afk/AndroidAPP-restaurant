package com.example.ordertaking.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ordertaking.R;
import com.example.ordertaking.database.dbAcess;

import com.example.ordertaking.navVertical.MainActivity2;
import com.example.ordertaking.tools.MediaClick;

public class loginActivity extends AppCompatActivity {
    EditText username,password;
    ProgressBar progressBar;
    Button login;
    TextView error;
    ImageView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(User.getInstance().getUsername()!=null) {
            dbAcess.updateStatus("deconnecte", User.getInstance().getUsername());
        }
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        progressBar=findViewById(R.id.progressbar);
        login=findViewById(R.id.login);
        error=findViewById(R.id.error);
        show=findViewById(R.id.show);

        User user=new User();
        User.setInstance(new User());

        dbAcess.DBConnect();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user.setPassword(password.getText().toString().trim());
                user.setUsername(username.getText().toString().trim());

                if(user.getUsername().isEmpty()) {
                    username.setError("Nom d'utilisateur requis !");
                    username.requestFocus();
                    return;
                }
                if(user.getPassword().isEmpty()) {
                    password.setError("Mot de passe requis !");
                    password.requestFocus();
                    return;
                }

                for(User user1:dbAcess.users){
                    if(user1.getFonction().equals("serveur")){
                        if(user1.getUsername().equals(user.getUsername()) && user1.getPassword().equals(user.getPassword())){
                            progressBar.setVisibility(View.VISIBLE);
                            error.setText("");
                            User.setInstance(user1);
                            dbAcess.updateStatus("connecte",user.getUsername());
                            mainActivity();
                            MediaPlayer mediaPlayer=new MediaPlayer();
                            MediaClick.setMediaPlayer(mediaPlayer);
                            MediaClick.start();
                        }
                        else
                            error.setVisibility(View.VISIBLE);
                    }
                    else
                        error.setVisibility(View.VISIBLE);
                }
            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

    }

    private void mainActivity() {
        Intent intent=new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}