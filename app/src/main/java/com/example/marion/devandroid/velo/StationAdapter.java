package com.example.marion.devandroid.velo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marion.devandroid.R;

import java.util.ArrayList;

/**
 * Created by marion on 24/11/17.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.ViewHolder> {

    private ArrayList<StationBean> stationBeanArrayList;

    public StationAdapter(ArrayList<StationBean> stationBeanArrayList) {
        this.stationBeanArrayList = stationBeanArrayList;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.marker_velo, parent, false);
        return new StationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        StationBean station = stationBeanArrayList.get(position);
        holder.textViewAddress.setText(station.getAddress());
        holder.textViewBikes.setText(station.getAvailable_bikes());
        holder.textViewBikeStands.setText(station.getAvailable_bike_stands());


    }

    @Override
    public int getItemCount() {
        return stationBeanArrayList.size() ;
    }//3 {

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewAddress, textViewBikes, textViewBikeStands;
        public ImageView imageView;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewAddress = itemView.findViewById(R.id.textVAdress);
            textViewBikes = itemView.findViewById(R.id.textVBikes);
            textViewBikeStands = itemView.findViewById(R.id.textVBikeStnd);

        }
    }
}
