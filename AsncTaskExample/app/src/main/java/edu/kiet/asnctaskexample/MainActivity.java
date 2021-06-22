package edu.kiet.asnctaskexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ImageView myImage;
    Button loadImage;
    ProgressDialog dialog;
    Bitmap image=null;
    String imagepath="https://fsa.zobj.net/crop.php?r=InzbtmXscw3rLyv4t8XH4MbergB0QC1dPXk52ElFT3FuaMseuowTc8z8wTGuk8Z8zn3yTEXZY0gIVvcdVZdEvNS4G4ETYO79TFSw2u831S9BrZtbWaYAXIcfNwRalCLfa5jTxEOp1FGATWSK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadImage=findViewById(R.id.btnLoadImage);
        myImage=findViewById(R.id.myImage);

        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 //MyAsyncTask myAsyncTask = new MyAsyncTask();
                 // myAsyncTask.execute(imagepath);
                ExecutorService service =Executors.newSingleThreadExecutor();
                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        //onPreExecute Method
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog = new ProgressDialog(MainActivity.this);
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.show();
                            }
                        });
                        //doInBrackground method of Asynctask
                        try {
                            URL url=new URL(imagepath);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.connect();
                            image= BitmapFactory.decodeStream(httpURLConnection.getInputStream());

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //onPostExecute Method
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dialog.cancel();
                                myImage.setImageBitmap(image);
                            }
                        });
                    }
                });


            }
        });
        //

    }

    class MyAsyncTask extends AsyncTask<String,String,Bitmap>{
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap image=null;
            try {
                URL url=new URL(params[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                image= BitmapFactory.decodeStream(httpURLConnection.getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            dialog.cancel();
            myImage.setImageBitmap(bitmap);
        }
    }
}