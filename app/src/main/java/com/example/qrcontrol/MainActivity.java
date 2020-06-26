package com.example.qrcontrol;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity{

    private ImageView ivQrScan;
    private TextView tvPositionInfo;
    private ImageView koks;


    WifiManager wifiManager;

    FirebaseDatabase database;
    DatabaseReference myRef;

    String bssid;
    String ssid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ;

            {
                ivQrScan = findViewById(R.id.iv_qrScan);
                tvPositionInfo = findViewById(R.id.id_position_info);
                koks = findViewById(R.id.kok);


                database = FirebaseDatabase.getInstance();
                myRef = database.getReference();


                wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                bssid = wifiInfo.getBSSID();
                ssid = wifiInfo.getSSID();
                tvPositionInfo.setText(ssid + "\n" + bssid  );

                ivQrScan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, QrReadActivity.class);
                        startActivityForResult(intent, 0);
                    }
                });
                koks.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, history.class);
                        startActivityForResult(intent, 0);
                    }
                });


            }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Date currentDate = new Date();
        if(resultCode == RESULT_OK && requestCode == 0){
            String s = data.getStringExtra("result");

            myRef.child("points").push().setValue(s + " -> " + ssid + ": " + bssid + " Текущее время : " + currentDate);




        }


;
    }



}

