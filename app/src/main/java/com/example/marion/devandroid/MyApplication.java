package com.example.marion.devandroid;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by marion on 31/10/17.
 */

public class MyApplication extends Application {

    private static Bus bus;

    public static Bus getBus(){
        return bus;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        bus = new Bus();

    }
}
