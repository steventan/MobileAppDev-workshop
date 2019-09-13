package com.example.steventan.mobileappdevalarm;

import android.app.Application;
import android.media.MediaPlayer;

public class myApplication extends Application {
    public MediaPlayer ring= MediaPlayer.create(this,R.raw.song1);

    public MediaPlayer getMediaPlayer() {
        return ring;
    }
}
