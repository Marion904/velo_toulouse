package com.example.marion.devandroid.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * Created by marion on 31/10/17.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"YOUOU",Toast.LENGTH_LONG).show();
        final String action = intent.getAction();
        if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)){
                Toast.makeText(context,"Connecté",Toast.LENGTH_SHORT).show();
            } else {
                // wifi connection was lost
            }
        }
    }
}
