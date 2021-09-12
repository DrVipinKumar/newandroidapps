package edu.kiet.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    TextView singup;
    Button login;
    EditText lemail, lpass;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        singup=findViewById(R.id.txtSignUp);
        login=findViewById(R.id.btnLogin);
        lemail=findViewById(R.id.txtLoginEmail);
        lpass=findViewById(R.id.txtLoginPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!lpass.getText().toString().isEmpty() && !lemail.getText().toString().isEmpty())
                {
                    mAuth.signInWithEmailAndPassword(lemail.getText().toString(), lpass.getText().toString())
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if(user != null){
                                            login();
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.
                                      Toast.makeText(Login.this, "Login Failed Due to:"+task.getException(),
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter email and password first",Toast.LENGTH_SHORT).show();
                }
            }
        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),Registration.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            login();
        }
    }
    void login()
    {
        Intent i =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}