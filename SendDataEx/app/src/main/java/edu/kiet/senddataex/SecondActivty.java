package edu.kiet.senddataex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivty extends AppCompatActivity {

    TextView value;
    Button goodbye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        value=findViewById(R.id.txtValue);
        goodbye=findViewById(R.id.btnBye);
        Bundle data=getIntent().getExtras();
        value.setText(data.getString("info"));
        goodbye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent();
                i.putExtra("reply","Good Bye");
                setResult(Activity.RESULT_OK,i);
                finish();
            }
        });

    }
}