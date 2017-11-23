package com.example.marion.devandroid;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by marion on 20/11/17.
 */


//...

public class MaVille {
    private String nom;
    public LatLng position;
    private int logo;



    public MaVille() {

    }


    public MaVille(String nom, LatLng position) {
        this.nom = nom;
        this.position = position;
    }

    public MaVille(String nom, LatLng position,int logo) {
        this.nom = nom;
        this.position = position;
        this.logo = logo;
    }


    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public String getNom() {
        return nom;
    }

    public LatLng getPosition() {
        return position;
    }
}
