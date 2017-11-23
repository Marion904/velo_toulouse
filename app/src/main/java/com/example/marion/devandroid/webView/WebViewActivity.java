package com.example.marion.devandroid.webView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marion.devandroid.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener{

    Button req, cpButton;
    TextView answer;
    EditText url, cpEditText;
    WebView webView;
    String result, urlString;
    Exception exception;
    ArrayList<VilleBean> villeBeanArrayList;
    String URLWEBSERVICE = "http://www.citysearch-api.com/fr/city?login=webserviceexemple&apikey=sof940dd26cf107eabf8bf6827f87c3ca8e8d82546&cp=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        req = findViewById(R.id.post);
        answer=findViewById(R.id.text);
        url = findViewById(R.id.url);
        webView = findViewById(R.id.webView);
        cpButton = findViewById(R.id.askWS);
        cpEditText = findViewById(R.id.CPostal);
        req.setOnClickListener(this);
        cpButton.setOnClickListener(this);


        webView.setWebViewClient(new WebViewClient());

        WebSettings webviewSettings = webView.getSettings();
        webviewSettings.setJavaScriptEnabled(true);



    }

    @Override
    public void onClick(View view) {
        if(view == req){
            webView.loadUrl(url.getText().toString());
            //webView.loadUrl("file:///android_asset/index.html");
            //webView.loadData("<html><body>Bonjour !</body></html>« , "text/html", "UTF-8");

            LoadData loadData = new LoadData();
            loadData.execute();


        } if (view == cpButton) {
            if (!cpEditText.getText().equals(null)) {

                //WebServiceUtils wsUtils = new WebServiceUtils(urlString);
                CPAsyncTask cpAsyncTask = new CPAsyncTask();

                cpAsyncTask.execute();

            } else {
                Toast.makeText(this, "Donne un cp!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public class LoadData extends AsyncTask {

        private String resultat;

        /**
         * thread à part
         * @param objects
         * @return
         */
        @Override
        protected Object doInBackground(Object[] objects) {

             try {
                result = OkHttpUtils.get(url.getText().toString());
            } catch (IOException e) {
                exception = e;
                e.printStackTrace();
            }

            try {
                result = OkHttpUtils.get(urlString);
            } catch (IOException e) {
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

                answer.setText(result);
            urlString.equals(null);

        }

    }


/**
    public class WebServiceUtils {
        String URLWEBSERVICE = "http://www.citysearch-api.com/fr/city?login=webserviceexemple&apikey=sof940dd26cf107eabf8bf6827f87c3ca8e8d82546&cp=";
        String cp;
        public String result;
        String urlString = URLWEBSERVICE + cp;

        public WebServiceUtils(String urlString) {
            this.urlString = urlString;
        }
**/
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

                villeBeanArrayList = WebServiceUtils.getVilles(cpEditText.getText().toString());

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

            answer.setText(result);

            String listCity = "";
            for (int i =0;i<villeBeanArrayList.size();i++){

                VilleBean v = villeBeanArrayList.get(i);
                int codep = Integer.parseInt(v.getCp());


                if (i != 0){

                    int index = 0 ;
                    for (int j = 0 ; j < i; j++){

                        if (Integer.parseInt(villeBeanArrayList.get(j).getCp()) < codep){
                            index++;


                        }

                    }
                    villeBeanArrayList.add(index, v);
                }


                listCity+= v.getVille()+" " + v.getCp() + ",";
            }

           answer.setText(listCity);


            //urlS.equals(null);




        }


    }


}
//}
