package com.example.marion.devandroid;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.marion.devandroid.asyncTask.AsyncTaskActivity;
import com.example.marion.devandroid.broadcast.WifiScanReceiver;
import com.example.marion.devandroid.notification.NotifActivity;
import com.example.marion.devandroid.service.ServiceActivity;
import com.example.marion.devandroid.velo.VeloMapsActivity;
import com.example.marion.devandroid.webView.WebViewActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    static final int ID_ALERT_DIALOG = 1;
    static final int ID_DATE_PICKER = 2;
    static final int ID_TIME_PICKER = 3;
    static final int ID_SERVICE = 4;
    static final int ID_NOTIFICATION = 5;
    static final int ID_MAPS = 6;
    static final int ASYNC_TASK = 7;
    static final int WEB_VIEW=8;
    static final int VELO_ACTIVITY = 9;

    static final int FINE_LOCATION_REQ_CODE = 100;

    WifiManager mainWifiObj;
    ArrayList wifi = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mainWifiObj = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        WifiScanReceiver wifiReceiver = new WifiScanReceiver();
        registerReceiver(wifiReceiver , new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
        /**
        for(int i = 0;i< wifiScanList.size();i++)
        {
            wifi.add(i,wifiScanList.get(i).toString());

            Toast.makeText(this,wifiScanList.get(i).toString(),Toast.LENGTH_LONG).show();
        }
**/

    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,ID_ALERT_DIALOG,0, R.string.adMenu);
        menu.add(0,ID_DATE_PICKER,0, R.string.dpMenu);
        menu.add(0,ID_TIME_PICKER,0, R.string.tpMenu);
        menu.add(0,ID_SERVICE,0,R.string.servMenu);
        menu.add(0,ID_NOTIFICATION,0,R.string.Notif);
        menu.add(0,ID_MAPS,0,R.string.maps);
        menu.add(0,ASYNC_TASK,0,"AsyncTask");
        menu.add(0,WEB_VIEW,0,"WebView");
        menu.add(0,VELO_ACTIVITY, 0, "Vélib'");
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case ID_ALERT_DIALOG :
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.adTitle))
                        .setMessage(R.string.adMessage)
                        .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), R.string.toastText,Toast.LENGTH_LONG).show();
                            }
                        }).show();
                break;

            case ID_DATE_PICKER:
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        this,
                        this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
                break;
            case ID_TIME_PICKER:
                final Calendar calendarTime = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        this,
                        (TimePickerDialog.OnTimeSetListener) this,
                        calendarTime.get(Calendar.HOUR_OF_DAY),
                        calendarTime.get(Calendar.MINUTE),
                        true);
                timePickerDialog.show();
                break;

            case ID_SERVICE:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                //On a la permission
                    Intent servActivityIntent = new Intent(MainActivity.this, ServiceActivity.class);
                    startActivity(servActivityIntent);
                }
                else {
                    // Etape 2 : On demande la permission
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            FINE_LOCATION_REQ_CODE);

                }
                break;

            case ID_NOTIFICATION:
                Intent notifIntent = new Intent(MainActivity.this, NotifActivity.class);
                startActivity(notifIntent);
                break;

            case ID_MAPS:
                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapIntent);
                break;

            case ASYNC_TASK:
                startActivity(new Intent(MainActivity.this, AsyncTaskActivity.class));
                break;

            case WEB_VIEW:
                if(isInternetConnexion(this)) {
                    startActivity(new Intent(MainActivity.this, WebViewActivity.class));
                }
                else{
                    Toast.makeText(this, "Pas de connection!!", Toast.LENGTH_SHORT).show();
                }
                break;
            case VELO_ACTIVITY:
                if (isInternetConnexion(this)){
                startActivity(new Intent(MainActivity.this, VeloMapsActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Connexion perdue", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {


    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Toast.makeText(this,i2+"/"+(i1+1)+"/"+i,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Toast.makeText(this,i+"h"+i1,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode ==FINE_LOCATION_REQ_CODE ){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent servActivityIntent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(servActivityIntent);
            }
            else{
                Toast.makeText(this, R.string.toastFineLocRefused,Toast.LENGTH_SHORT).show();
            }
        }

    }

    public static boolean isInternetConnexion(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
