package com.example.steventan.mobileappdevalarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;


public class MyService extends Service {

    MediaPlayer ring;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //Runs this portion of code when the service has started
    public void onCreate() {
        //create an instance of the mediaplayer with the song
        ring = MediaPlayer.create(this, R.raw.song1);
        //start playing the song
        ring.start();
        Toast.makeText(this, "MyService start.", Toast.LENGTH_SHORT).show();

    }
    @Override
    //Runs this portion of code when the service is stopped abruptly
    public void onDestroy() {
        ring.stop();
        ring.release();
        Toast.makeText(this, "MyService completed or stopped.", Toast.LENGTH_SHORT).show();
    }
}
