package com.example.qrcontrol;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
             finish();
            }
        },2500);
    }
}
