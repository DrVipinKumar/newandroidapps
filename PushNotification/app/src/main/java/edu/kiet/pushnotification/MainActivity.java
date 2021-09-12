package edu.kiet.pushnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;

public class MainActivity extends AppCompatActivity {
    TextView txttoken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txttoken=findViewById(R.id.txtToken);
        FirebaseInstallations.getInstance().getToken(true)
                     .addOnCompleteListener(new OnCompleteListener<InstallationTokenResult>() {
                         @Override
                         public void onComplete(Task<InstallationTokenResult> task) {
                             if (task.isSuccessful()) {
                                 String tokenid=task.getResult().getToken();
                                 Log.i("token",tokenid);
                                 txttoken.setText(tokenid);
                             }
                             else {
                                 Toast.makeText(getApplicationContext(),"Not connected",Toast.LENGTH_SHORT).show();
                             }

                         }
                     });


        }
    }
