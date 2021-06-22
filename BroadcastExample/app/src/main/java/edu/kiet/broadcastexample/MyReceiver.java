package edu.kiet.broadcastexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");
        if(intent.getAction().equals("android.intent.action.ACTION_POWER_CONNECTED"))
        {
            Toast.makeText(context,"Power is ON",Toast.LENGTH_SHORT).show();
        }
        if(intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED"))
        {
            Toast.makeText(context,"Power is OFF",Toast.LENGTH_SHORT).show();
        }
        if(intent.getAction().equals("edu.kiet.mybroadcast"))
        {
            Toast.makeText(context,"This is my Custom Broadcast",Toast.LENGTH_SHORT).show();
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null)
        {
            if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE)
            {
                Toast.makeText(context,"Interent is ON by Mobile",Toast.LENGTH_SHORT).show();
            }
            if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI)
            {
                Toast.makeText(context,"Interent is ON by WIFI",Toast.LENGTH_SHORT).show();
            }

        }else
        {
            Toast.makeText(context,"Internet is not Working, Please On your Internet",Toast.LENGTH_SHORT).show();
        }

    }
}