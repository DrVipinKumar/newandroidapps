package edu.kiet.radiobuttonex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton java, python, nodejs, kotlin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg=findViewById(R.id.radiobutton);
        java=findViewById(R.id.rdpJava);
        python=findViewById(R.id.rdpPython);
        nodejs=findViewById(R.id.rdpNodeJS);
        kotlin=findViewById(R.id.rdpKotlin);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rdpJava)
                {
                    Toast t= Toast.makeText(getApplicationContext(),"You clicked on Java",Toast.LENGTH_SHORT);
                    t.show();
                }
                switch (checkedId)
                {
                   // case R.id.rdpJava:
                    //   Toast t= Toast.makeText(getApplicationContext(),"You clicked on Java",Toast.LENGTH_SHORT);
                   //    t.show();
                   //     break;
                    case R.id.rdpPython:
                        Toast.makeText(getApplicationContext(),"You clicked on Python",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rdpNodeJS:
                        Toast.makeText(getApplicationContext(),"You clicked on NodeJS",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rdpKotlin:
                        Toast.makeText(getApplicationContext(),"You clicked on Kotlin",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}