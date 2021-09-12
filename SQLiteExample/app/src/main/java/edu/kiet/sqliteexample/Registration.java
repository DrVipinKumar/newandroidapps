package edu.kiet.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    FirebaseAuth auth;
    Button reg;
    EditText mail, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        auth = FirebaseAuth.getInstance();
        reg=findViewById(R.id.btnLogin);
        mail=findViewById(R.id.txtEmail);
        pass=findViewById(R.id.txtPassword);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!mail.getText().toString().isEmpty() && !pass.getText().toString().isEmpty()) {
                    auth.createUserWithEmailAndPassword(mail.getText().toString(), pass.getText().toString())
                            .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = auth.getCurrentUser();
                                        if (user != null) {
                                            login();
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(Registration.this, "Authentication failed." + task.getException(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }else
                {
                    Toast.makeText(Registration.this,"Enter email and password",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
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