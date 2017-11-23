package com.example.marion.devandroid.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.marion.devandroid.MainActivity;
import com.example.marion.devandroid.R;

public class NotifActivity extends AppCompatActivity implements View.OnClickListener {

    private Button now, later;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        now = (Button) findViewById(R.id.now);
        later = (Button) findViewById(R.id.later);

        now.setOnClickListener(this);
        later.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == now){
            createInstantNotification(this, "De Suite");
        }
        if(view == later){
            createScheduledNotification("Re-coucou",3000);
        }
    }


    public static void createInstantNotification(Context context, String message){

        // Quand on clique sur la notif
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 28, intent,
                PendingIntent.FLAG_ONE_SHOT);

        // La notification
        Notification notifDesuite = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Immédiate")
                .setContentText("c'est la notif immédiate")
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Bouton",
                        pendingIntent).build();

        //Préparer l'envoi de la notification
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Envoi
        notificationManager.notify(29,notifDesuite);

    }

    private void createScheduledNotification(String message, long delay){
        // La notification
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My Scheduled notif")
                .setContentText(message);

        //Redirection vers le broadcast
        Intent notifIntent = new Intent(this, NotificationPublisherBR.class);
        notifIntent.putExtra("MaClé",builder.build());
        PendingIntent pendingIntent =PendingIntent.getBroadcast(this,0, notifIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        //La date dans le futur :
        long futurInMillis = SystemClock.elapsedRealtime()+ delay;
        AlarmManager alarmManager =(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,futurInMillis,pendingIntent);
    }


}


