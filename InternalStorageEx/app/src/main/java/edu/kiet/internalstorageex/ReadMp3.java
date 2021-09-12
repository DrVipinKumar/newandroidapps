package edu.kiet.internalstorageex;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class ReadMp3 extends AppCompatActivity {
    ActivityResultLauncher<Intent> activityResultLauncher;
    String[] permission ={READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE};
    ListView listView;
    ArrayList<String> mp3file=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_mp3);
        listView=findViewById(R.id.listview);
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

       if(checkPermission())
       {
           File directory=new File(String.valueOf(Environment.getExternalStoragePublicDirectory("Music")));
           File[] mp3files=directory.listFiles(new FileFilter() {
               @Override
               public boolean accept(File pathname) {
                   return pathname.getName().endsWith(".mp3");
               }
           });
           for (File f: mp3files)
           {
               mp3file.add(f.getName());
           }
           adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,mp3file);
           listView.setAdapter(adapter);
       }
       else
       {
           requestPermission();
       }


    }
    void requestPermission()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R)
        {
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", getApplicationContext().getPackageName())));
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
            ActivityCompat.requestPermissions(ReadMp3.this,permission,30);

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
            int readcheck= ContextCompat.checkSelfPermission(getApplicationContext(),READ_EXTERNAL_STORAGE);
            int writecheck=ContextCompat.checkSelfPermission(getApplicationContext(),WRITE_EXTERNAL_STORAGE);
            return readcheck == PackageManager.PERMISSION_GRANTED && writecheck ==PackageManager.PERMISSION_GRANTED;
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
}