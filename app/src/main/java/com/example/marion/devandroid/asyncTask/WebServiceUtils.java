package com.example.marion.devandroid.asyncTask;

import android.os.SystemClock;

/**
 * Created by marion on 21/11/17.
 */
public class WebServiceUtils { //Classe appartenant au model. Gérera les futurs requêtes
    public static EleveBean loadEleveFromWeb(){
        SystemClock.sleep(500); //Attente de 5 secondes
        return new EleveBean("Toto", "Tata");
    }
}