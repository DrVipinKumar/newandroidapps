package edu.kiet.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("LifeCyle","This is onCreate() method");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LifeCyle","This is onStart() method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LifeCyle","This is onStop() method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LifeCyle","This is onDestroy() method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("LifeCyle","This is onRestart() method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LifeCyle","This is onPause() method");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.i("LifeCyle","This is onResume() method");
    }
}