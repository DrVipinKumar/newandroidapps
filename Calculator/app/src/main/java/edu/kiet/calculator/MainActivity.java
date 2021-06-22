package edu.kiet.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button sum, sub, div, mul;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1=findViewById(R.id.txtNum1);
        num2=findViewById(R.id.txtNum2);
        sum=findViewById(R.id.btnSum);
        sub=findViewById(R.id.btnSub);
        div=findViewById(R.id.btnDiv);
        mul=findViewById(R.id.btnMul);
        result=findViewById(R.id.txtResult);
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1=Integer.parseInt(num1.getText().toString());
                int n2=Integer.parseInt(num2.getText().toString());
                int s=n1*n2;
               // Toast.makeText(getApplicationContext(),"Multiplication of two number="+s,Toast.LENGTH_SHORT).show();
                result.setText("Multiplication of two number="+s);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1=Integer.parseInt(num1.getText().toString());
                int n2=Integer.parseInt(num2.getText().toString());
                int s=n1/n2;
                //Toast.makeText(getApplicationContext(),"Division of two number="+s,Toast.LENGTH_SHORT).show();
                result.setText("Division of two number="+s);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1=Integer.parseInt(num1.getText().toString());
                int n2=Integer.parseInt(num2.getText().toString());
                int s=n1-n2;
                //Toast.makeText(getApplicationContext(),"Subtraction of two number="+s,Toast.LENGTH_SHORT).show();
                result.setText("Subtraction of two number="+s);
            }
        });
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1=Integer.parseInt(num1.getText().toString());
                int n2=Integer.parseInt(num2.getText().toString());
                int s=n1+n2;
               // Toast.makeText(getApplicationContext(),"Sum of two number="+s,Toast.LENGTH_SHORT).show();
                result.setText("Sum of two number="+s);
            }
        });
    }
}