package com.example.marion.devandroid.velo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.marion.devandroid.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

   private RecyclerView rv;
   private ArrayList<StationBean> myStations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Intent i = getIntent();
        String jsonifiy = i.getExtras().getString("key");
        Gson gson = new Gson();
        myStations = gson.fromJson(jsonifiy, new TypeToken<ArrayList<StationBean>>(){}.getType());

        rv = findViewById(R.id.rv);

        StationAdapter myAdapter = new StationAdapter(myStations);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

    }


}
