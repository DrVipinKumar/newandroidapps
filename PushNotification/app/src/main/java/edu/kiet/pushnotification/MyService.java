package edu.kiet.pushnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService extends FirebaseMessagingService {
    public MyService() {
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("token", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("token", "Message data payload: " + remoteMessage.getData());


        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("token", "Message Notification Title: " + remoteMessage.getNotification().getTitle());
            Log.d("token", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
   void sendNotification(String title, String msgBody)
   {
       String channelid="fcmmessage";
       Notification.Builder builder;
       if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
       {
           builder=new Notification.Builder(getApplication().getApplicationContext(),channelid);
           NotificationChannel channel = new NotificationChannel(channelid,"FCM", NotificationManager.IMPORTANCE_HIGH);
           channel.setDescription("for FCM messsage");
           NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
           manager.createNotificationChannel(channel);
       }
       else
       {
           builder=new Notification.Builder(getApplication().getApplicationContext());
       }
          builder.setContentTitle(title);
          builder.setContentText(msgBody);
          builder.setSmallIcon(R.mipmap.ic_launcher);
          NotificationManager manager =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
          manager.notify(1,builder.build());
   }

}