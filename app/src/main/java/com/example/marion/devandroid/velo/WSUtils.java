package com.example.marion.devandroid.velo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by marion on 23/11/17.
 */

public class WSUtils {

    static final String api_key0 = "https://api.jcdecaux.com/vls/v1/stations?contract=toulouse&apiKey=7a1b88eb27e8be68faa3a3e1b0c7541ea0d41ba0";
    static final String api_key1 = "https://api.jcdecaux.com/vls/v1/stations?contract={";
    static final String api_key2 = "}&apiKey={7a1b88eb27e8be68faa3a3e1b0c7541ea0d41ba0}";

    public static ArrayList<StationBean> getStations() throws IOException {
        //String url = api_key1+ville+api_key2;
        String json = OkHttpUtils.get(api_key0);

        Gson gson = new Gson();
        ArrayList<StationBean> stationBeans = gson.fromJson(json, new TypeToken<ArrayList<StationBean>>(){}.getType());

        return stationBeans;

    }
}
