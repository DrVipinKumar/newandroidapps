package edu.kiet.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox java, python, kotlin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        java=findViewById(R.id.chkJava);
        python=findViewById(R.id.chkPython);
        kotlin=findViewById(R.id.chkKotlin);
        kotlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kotlin.isChecked())
                {
                    Toast.makeText(getApplicationContext(),"You clicked on Kotlin",Toast.LENGTH_LONG).show();
                }
            }
        });
        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(python.isChecked())
                {
                    Toast.makeText(getApplicationContext(),"You clicked on Python",Toast.LENGTH_LONG).show();
                }
            }
        });
        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(java.isChecked())
                {
                    Toast.makeText(getApplicationContext(),"You clicked on Java",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}