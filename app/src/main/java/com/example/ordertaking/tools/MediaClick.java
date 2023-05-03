package com.example.ordertaking.tools;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

import com.example.ordertaking.R;

public class MediaClick {


    static MediaPlayer mediaPlayer=new MediaPlayer();


    public static void setMediaPlayer(MediaPlayer m) {
        mediaPlayer = m;
    }

    public static void start(){
        mediaPlayer.start();
    }
}
