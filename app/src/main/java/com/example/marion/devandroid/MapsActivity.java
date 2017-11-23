package com.example.marion.devandroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    MaVille maVille = new MaVille();

    LatLng toulouse = new LatLng(43.59999, 1.43333);
    static final int FINE_LOCATION_REQ_CODE = 101;
    LocationManager locationMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // charger la carte en asynchrone
        mapFragment.getMapAsync(this);

        //On vérifie si on a la permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_LOCATION_REQ_CODE);
        }


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
        //LatLng sydney = new LatLng(-34, 151);

        mMap.setInfoWindowAdapter(this);
        mMap.setOnInfoWindowClickListener(this);

        refreshScreen();

        }

        public View getInfoWindow (Marker marker) {
            return null;
        }

    @Override
    public View getInfoContents(Marker marker) {
        View view = LayoutInflater.from(this).inflate(R.layout.marker_cellule, null);

        TextView tv = view.findViewById(R.id.marker);
        ImageView iv = view.findViewById(R.id.lieu);

        MaVille maVille = (MaVille) marker.getTag();
        tv.setText(maVille.getNom());
        iv.setImageResource(maVille.getLogo());

        return view;
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

        }
        // j'affiche mes marqueurs


        maVille.setNom("Toulouse Rôôse");
        maVille.setPosition(toulouse);
        maVille.setLogo(R.drawable.flamingo);


        MarkerOptions markerToulouse = new MarkerOptions();
        markerToulouse.position(maVille.getPosition());
        markerToulouse.title(maVille.getNom());
        markerToulouse.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(markerToulouse)
                .setTag(maVille);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(maVille.getPosition()));

        mMap.setInfoWindowAdapter(this);
        mMap.setOnInfoWindowClickListener(this);


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


    @Override
    public void onInfoWindowClick(Marker marker) {
        MaVille v = (MaVille) marker.getTag();
        Toast.makeText(this, v.getNom()+"..."+v.getPosition().latitude+"..."+v.getPosition().longitude,Toast.LENGTH_SHORT).show();
    }
}



