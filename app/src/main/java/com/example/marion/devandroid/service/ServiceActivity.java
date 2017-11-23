package com.example.marion.devandroid.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.marion.devandroid.MyApplication;
import com.example.marion.devandroid.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button start, stop;
    private String sb;
    MyApplication application = (MyApplication) getApplication();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);



        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);


        application.getBus().register(this); //OnStart


        start.setOnClickListener(this);
        stop.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view== start){
            startService(new Intent(this, LocationService.class));


        }
        if(view==stop){
            stopService(new Intent(this, LocationService.class));
            MyApplication.getBus().unregister(this); //OnStop
        }
    }


}
