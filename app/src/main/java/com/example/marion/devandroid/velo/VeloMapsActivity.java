package com.example.marion.devandroid.velo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marion.devandroid.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class VeloMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener  {

    private GoogleMap mMap;
    private View alertLayout;
    private ArrayList<StationBean> stationBeans;
            private ArrayList<StationBean> mesStations= new ArrayList<>();
    private String ville = "toulouse";
    LatLng toulouse = new LatLng(43.59999, 1.43333);
    static final int FINE_LOCATION_REQ_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velo_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //On vérifie si on a la permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_LOCATION_REQ_CODE);
        }


        CPAsyncTask cpAsyncTask = new CPAsyncTask();
        cpAsyncTask.execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLng(toulouse));



        refreshScreen();



    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
/**
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
        LayoutInflater inflater = getLayoutInflater();
        alertLayout = inflater.inflate(R.layout.marker_velo, null);
        StationBean stationbean = (StationBean) marker.getTag();
        final TextView tvBike = alertLayout.findViewById(R.id.TextVBikes);
        final TextView tvBikeStands =alertLayout.findViewById(R.id.textVBikeStnd);
        final TextView tvStatus = alertLayout.findViewById(R.id.textVStatus);

        tvBike.setText(stationbean.getAvailable_bikes());
        tvBikeStands.setText(stationbean.getAvailable_bike_stands());
        tvStatus.setText(stationbean.getStatus());



        AlertDialog dialog = alert.create();
        dialog.show();
 **/
        View view = LayoutInflater.from(this).inflate(R.layout.marker_velo, null);

        TextView tvStand = view.findViewById(R.id.textVBikeStnd);
        TextView tvBikes = view.findViewById(R.id.textVBikes);
        TextView tvAddress = view.findViewById(R.id.textVAdress);
        ImageView iv = view.findViewById(R.id.velo);

        StationBean sb = (StationBean) marker.getTag();
        tvStand.setText(sb.getAvailable_bike_stands()+" places disponibles");
        tvBikes.setText(sb.getAvailable_bikes()+" vélos libres");
        tvAddress.setText(sb.getName()+" "+sb.getAddress());

        switch (sb.getStatus()){
            case "OPEN":
                if(Integer.parseInt(sb.getAvailable_bike_stands())>0
                        && Integer.parseInt(sb.getAvailable_bikes())>0){
                    iv.setColorFilter(Color.GREEN);
                }else if(Integer.parseInt(sb.getAvailable_bike_stands())>0){
                    iv.setColorFilter(Color.CYAN);
                }else if(Integer.parseInt(sb.getAvailable_bikes())>0){
                    iv.setColorFilter(Color.RED);
                }
                break;

            case "CLOSED":
               iv.setColorFilter(Color.MAGENTA);
        }
        iv.setImageResource(R.drawable.flamingo);

        return view;

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        marker.hideInfoWindow();
    }

    public class CPAsyncTask extends AsyncTask {

        String urlS;
        public CPAsyncTask() {


        }

        //private ArrayList<VilleBean> resultat;

        /**
         * thread à part
         * @param objects
         * @return
         */
        @Override
        protected Object doInBackground(Object[] objects) {

            try {

                stationBeans = WSUtils.getStations();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            progressBar.setVisibility(View.VISIBLE);
        }

        /**
         * UIThread
         * @param o
         */


        protected void onPostExecute (Object o){
            //          progressBar.setVisibility(View.GONE);


            mesStations.clear();
            mesStations.addAll(stationBeans);
            refreshScreen();

            //urlS.equals(null);




        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case FINE_LOCATION_REQ_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    refreshScreen();
                }
            }
        }


    }




    //Actualisation de la carte
    private void refreshScreen(){
        // la carte est prête?
        if(mMap==null){
            return;
        }
        //est-ce que j'ai la permission?
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //On a la permission
            mMap.setMyLocationEnabled(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(toulouse));
        //}

        if(!mesStations.isEmpty()) {
            for (StationBean stationBean : mesStations) {

                double lat = Double.parseDouble(stationBean.getPosition().getLat());

                double lng = Double.parseDouble(stationBean.getPosition().getLng());

                LatLng marker = new LatLng(lat, lng);
                MarkerOptions markerVelo = new MarkerOptions();
                markerVelo.title(stationBean.getName()
                        + "Places dispo : " + stationBean.getAvailable_bike_stands()
                        + "Vélos : " + stationBean.getAvailable_bikes());

                switch (stationBean.getStatus()) {
                    case "OPEN":
                        if (Integer.parseInt(stationBean.getAvailable_bike_stands()) > 0
                                && Integer.parseInt(stationBean.getAvailable_bikes()) > 0) {
                            markerVelo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        } else if (Integer.parseInt(stationBean.getAvailable_bike_stands()) > 0) {
                            markerVelo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
                        } else if (Integer.parseInt(stationBean.getAvailable_bikes()) > 0) {
                            markerVelo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        }
                        break;

                    case "CLOSED":
                        markerVelo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                }


                markerVelo.position(marker);

                mMap.addMarker(markerVelo).setTag(stationBean);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(markerVelo.getPosition()));


                mMap.setInfoWindowAdapter(this);
                mMap.setOnInfoWindowClickListener(this);
                mMap.animateCamera(CameraUpdateFactory.zoomBy(7));
            }

        }

        }




    }


}
