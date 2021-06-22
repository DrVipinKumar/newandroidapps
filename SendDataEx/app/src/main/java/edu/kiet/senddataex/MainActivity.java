package edu.kiet.senddataex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText data;
    Button sendData;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=findViewById(R.id.txtData);
        sendData=findViewById(R.id.btnSendData);
        result=findViewById(R.id.txtResult);
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.getText().toString().isEmpty())
                {
                    Intent i =new Intent(getApplicationContext(),SecondActivty.class);
                    i.putExtra("info",data.getText().toString());
                    startActivityForResult(i,1);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter some information first",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode== Activity.RESULT_OK)
        {
            Toast.makeText(getApplicationContext(),data.getStringExtra("reply"),Toast.LENGTH_SHORT).show();
            result.setText(data.getStringExtra("reply"));
        }
    }
}