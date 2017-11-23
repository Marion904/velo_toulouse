package com.example.marion.devandroid.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by marion on 20/11/17.
 */

public class NotificationPublisherBR extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w("log_test","tU ES LÀ");
        Notification notification = intent.getParcelableExtra("MaClé");

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);

    }
}
