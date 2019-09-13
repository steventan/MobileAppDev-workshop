package com.example.steventan.mobileappdevalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();
        //Creates new intent with myService
        Intent intent2 = new Intent(context, MyService.class);
        //Start service
        context.startService(intent2);
    }


}