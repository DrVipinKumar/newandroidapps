package edu.kiet.internalstorageex;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    Button saveinfo, displayinfo;
    EditText info;
    TextView disinfo;
    String[] permission ={READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE};
    ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        info=findViewById(R.id.txtInfo);
        disinfo=findViewById(R.id.txtDisInfo);
        saveinfo=findViewById(R.id.btnSaveInfo);
        disinfo.setText(Environment.getExternalStorageDirectory().getAbsolutePath().toString());

        saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkPermission()) {
                    if (!info.getText().toString().isEmpty()) {
                        saveInfoInFile(info.getText().toString());
                        Toast.makeText(getApplicationContext(), "Information Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Enter information", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    requestPermission();
                }
            }


        });
        displayinfo=findViewById(R.id.btnDisplayInfo);
        displayinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkPermission()) {
                    displaySavedInformation();
                }else
                {
                    requestPermission();
                }

            }
        });
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== Activity.RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        if (Environment.isExternalStorageManager()) {
                            Toast.makeText(getApplicationContext(), "Permession Granted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Permession Denied", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }
    void requestPermission()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R)
        {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", new Object[]{getApplicationContext().getPackageName()})));
                activityResultLauncher.launch(intent);
            }catch (Exception e)
            {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                activityResultLauncher.launch(intent);
            }
        }
        else
        {
            ActivityCompat.requestPermissions(MainActivity.this,permission,30);

        }
    }

    boolean checkPermission()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R)
        {
            return Environment.isExternalStorageManager();
        }
        else
        {
            int readcheck=ContextCompat.checkSelfPermission(getApplicationContext(),READ_EXTERNAL_STORAGE);
            int writecheck=ContextCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE);
            return readcheck ==PackageManager.PERMISSION_GRANTED && writecheck ==PackageManager.PERMISSION_GRANTED;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 30:
                if(grantResults.length>0)
                {
                    boolean readper=grantResults[0]==PackageManager.PERMISSION_GRANTED;
                    boolean writeper=grantResults[1]==PackageManager.PERMISSION_GRANTED;
                    if(readper && writeper)
                    {
                        Toast.makeText(getApplicationContext(),"Permession Granted",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Permession Denied",Toast.LENGTH_SHORT).show();
                    }

                }else
                {
                    Toast.makeText(getApplicationContext(),"You Denied Permession",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    void displaySavedInformation()
    {
        FileInputStream file=null;
        String info=null;
        StringBuilder data=new StringBuilder();
        try {
            File f =new File(Environment.getExternalStoragePublicDirectory("Download"),"info.txt");
            file =new FileInputStream(f);
            InputStreamReader input = new InputStreamReader(file);
            BufferedReader br =new BufferedReader(input);
            while((info=br.readLine())!=null)
            {
                data.append(info);
            }
            disinfo.setText(data.toString());
            file.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"File Not Found",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"IO Exception",Toast.LENGTH_SHORT).show();
        }
    }
    void saveInfoInFile(String info)
    {

        FileOutputStream file=null;
        try {
            File f =new File(Environment.getExternalStoragePublicDirectory("Download"),"info.txt");
            f.createNewFile();
            file=new FileOutputStream(f);
            file.write(info.getBytes());
            file.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"File Not Found",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"IO Exception",Toast.LENGTH_SHORT).show();
        }
        }
    }

