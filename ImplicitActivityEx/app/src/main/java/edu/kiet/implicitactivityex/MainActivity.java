package edu.kiet.implicitactivityex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button call, openweb,opensecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call=findViewById(R.id.btnCall);
        openweb=findViewById(R.id.btnOpenWeb);
        opensecond=findViewById(R.id.btnOpenSecond);
        opensecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent second =new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(second);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri phoneno=Uri.parse("tel:9898989898");
                Intent i = new Intent(Intent.ACTION_DIAL,phoneno);
                startActivity(i);
            }
        });
        openweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage=Uri.parse("https://www.kiet.edu");
                Intent i = new Intent(Intent.ACTION_VIEW,webpage);
                startActivity(i);
            }
        });
    }
}