package com.example.marion.devandroid.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by marion on 31/10/17.
 */

public class WifiScanReceiver extends BroadcastReceiver{


    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"YOUOU",Toast.LENGTH_LONG).show();
    }
}
