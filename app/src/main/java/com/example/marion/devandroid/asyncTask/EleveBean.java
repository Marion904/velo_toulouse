package com.example.marion.devandroid.asyncTask;

/**
 * Created by marion on 21/11/17.
 */

public class EleveBean {
    private String nom;
    private String prenom;

    public EleveBean(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
