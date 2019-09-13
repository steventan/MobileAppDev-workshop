
package com.example.steventan.mobileappdevalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    //A widget for selecting the time of the day
    //We can choose either 24-hour or AM/PM mode
    TimePicker alarmTimePicker;

    //A description of an Intent and target action to perform with it.
    PendingIntent pendingIntent;

    //An android built-in class to provide access to the system's alarm services
    //Allows us to schedule our application to be ran at some
    AlarmManager alarmManager;

    //ToggleButton in interface
    ToggleButton alarm_button;
    //BroadcastReceiver receiver = new BroadcastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    public void OnToggleClicked(View view)
    {
        long time;
        if (((ToggleButton) view).isChecked())
        {

            //A view containing a quick little message for the user.
            Toast.makeText(MainActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();

            //An abstract class that provides methods for converting between a specific instant in time
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

            //Creates a new intent, with the Receiver class in context
            Intent intent = new Intent(this, AlarmReceiver.class);

            //Creates a pending intent(i.e. in the future)
            //Intent would perform a broadcast
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            //Getting the current time in millisecond
            //without including the second at the moment
            //E.g. 23:50:00
            time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));

            //to set it for the current time
            if(System.currentTimeMillis()>time)
            {
                //AM
                if (calendar.AM_PM == 0)
                    time = time + (1000*60*60*12);
                //PM
                else
                    time = time + (1000*60*60*24);
            }
            //Set for a repeating alarm in AlarmManager, with the Intent
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent);
        }
        else
        {
            //while the current time is still less than the current time
            //the pendingIntent would then be cancelled
            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
            Toast.makeText(MainActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
            Intent intent2 = new Intent(this, MyService.class);
            stopService(intent2);
            Toast.makeText(MainActivity.this, "ALARM OFF BUTTON", Toast.LENGTH_SHORT).show();
        }
    }

    public void off_alarm(View view) {
        //Creates a new intent with myService
        Intent intent2 = new Intent(this, MyService.class);

        //Stopping the service
        stopService(intent2);
        Toast.makeText(MainActivity.this, "ALARM OFF BUTTON", Toast.LENGTH_SHORT).show();
    }
}