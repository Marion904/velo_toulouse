package com.example.marion.devandroid.asyncTask;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.marion.devandroid.R;

public class AsyncTaskActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result;
    Button load;
    private MonAsyncTask maTask = null;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        result =  findViewById(R.id.load);
        load = findViewById(R.id.asyncButton);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == load){




            if(maTask == null || maTask.getStatus()== AsyncTask.Status.FINISHED){
                maTask = new MonAsyncTask();
                maTask.execute();
            }
            else{
                AlertDialog.Builder alertDialogBuidler = new AlertDialog.Builder(this)
                        .setTitle(R.string.alertTitle)
                        .setMessage(R.string.alertDial)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                alertDialogBuidler.show();
            }
        }
    }


    public class MonAsyncTask extends AsyncTask {

        private EleveBean resultat;

        /**
         * thread à part
         * @param objects
         * @return
         */
        @Override
        protected Object doInBackground(Object[] objects) {
            resultat = WebServiceUtils.loadEleveFromWeb();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        /**
         * UIThread
         * @param o
         */


        protected void onPostExecute (Object o){
            progressBar.setVisibility(View.GONE);
            result.setText("Nom : " + resultat.getNom()+ "Prenom : "+resultat.getPrenom());
        }

    }
}
