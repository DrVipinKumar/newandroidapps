package edu.kiet.broadcastexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button fireBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyReceiver myReceiver =new MyReceiver();
        IntentFilter intentFilter =new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANCED");
        intentFilter.addAction("edu.kiet.mybroadcast");
        registerReceiver(myReceiver,intentFilter);
        fireBroadcast = findViewById(R.id.btnFireBroadcast);
        fireBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent i =new Intent();
                   i.setAction("edu.kiet.mybroadcast");
                   sendBroadcast(i);
            }
        });

    }
}