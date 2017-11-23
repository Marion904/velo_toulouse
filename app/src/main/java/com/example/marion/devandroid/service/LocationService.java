package com.example.marion.devandroid.service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.example.marion.devandroid.MyApplication;

/**
 * Created by marion on 31/10/17.
 */

public class LocationService extends Service implements LocationListener {


    double latitude, longitude;
    String locationProvider = LocationManager.NETWORK_PROVIDER;
    MyApplication application = (MyApplication) getApplication();
    LocationManager locationMgr = null;
    @Override
    public void onCreate() {
        super.onCreate();
        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Toast.makeText(getApplicationContext(),"toto",Toast.LENGTH_SHORT).show();

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

            if (locationMgr.getAllProviders().contains(LocationManager.GPS_PROVIDER))
                locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);

    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locationMgr.removeUpdates(this);
    }


    @Override
    public void onLocationChanged(Location location) {
        /**rounding the latitude and longitude to 2 decimals*/
        longitude = (double) Math.round(location.getLongitude() * 100d) / 100d;
        latitude = (double) Math.round(location.getLatitude() * 100d) / 100d;
        StringBuilder sb = new StringBuilder();
        sb.append("longitude : ");
        sb.append(longitude);
        sb.append("/ latidude : ");
        sb.append(latitude);
        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        application.getBus().post(new Location(location));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
