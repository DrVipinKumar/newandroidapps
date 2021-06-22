package edu.kiet.loginex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, pwd;
    Button login;
    String uname, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.txtName);
        pwd=findViewById(R.id.txtPassword);
        login=findViewById(R.id.btnLogin);

        if(name.getText().toString().equals(uname) && pwd.getText().toString().equals(password))
        {
            Intent i =new Intent(getApplicationContext(),Welcome.class);
            startActivity(i);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
                    String uname=sp.getString("uname","");
                    String password=sp.getString("password","");
                    SharedPreferences.Editor editor= sp.edit();
                    if(!name.getText().toString().isEmpty() && !pwd.getText().toString().isEmpty() ){
                    if(!uname.isEmpty()&& !password.isEmpty())
                    {
                        if(uname.equals(name.getText().toString())&& password.equals(pwd.getText().toString()))
                        {
                            Intent i =new Intent(getApplicationContext(),Welcome.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Password Did Not Match",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        editor.putString("uname", name.getText().toString());
                        editor.putString("password",pwd.getText().toString());
                        editor.apply();
                        Intent i =new Intent(getApplicationContext(),Welcome.class);
                        startActivity(i);
                    }

                }else
                {
                    Toast.makeText(getApplicationContext(),"Please Insert Value First",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}