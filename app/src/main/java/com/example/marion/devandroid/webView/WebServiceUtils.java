package com.example.marion.devandroid.webView;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;



public class WebServiceUtils {


   static  String URLWEBSERVICE = "http://www.citysearch-api.com/fr/city?login=webserviceexemple&apikey=sof940dd26cf107eabf8bf6827f87c3ca8e8d82546&cp=";


    public static ArrayList<VilleBean>  getVilles(String cp) throws Exception {
     String json =    OkHttpUtils.get(URLWEBSERVICE + cp);

        Gson gson = new Gson();
        Results result  =  gson.fromJson(json, Results.class);

        if (result.getError() != null){
            Exception exception = new Exception("Coucou ca marche pas"+  result.getError().getMessage());
            throw exception;
        }
        return  result.getResults();


    }


}
