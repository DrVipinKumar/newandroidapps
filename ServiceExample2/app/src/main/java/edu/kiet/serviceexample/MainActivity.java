package edu.kiet.serviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button starService, stopService;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starService=findViewById(R.id.btnStartService);
        stopService=findViewById(R.id.btnStopService);
        starService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(),MyService.class);
                startService(i);
            }
        });
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(i);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}