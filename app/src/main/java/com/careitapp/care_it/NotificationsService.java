package com.careitapp.care_it;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.careitapp.care_it.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;


public class NotificationsService extends FirebaseMessagingService {

    private NotificationManager notificationManager;

    public NotificationsService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        if(remoteMessage == null)
        {
            return;
        }
        if(remoteMessage.getNotification() != null)
        {
            handleNotification(remoteMessage.getNotification().getTitle());
        }
    }
    public void handleNotification(String message)
    {
        sendNotification(message);
    }

    public void sendNotification(String message)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle("It's time to take your pills!")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setColorized(true)
                .setColor(getResources().getColor(R.color.colorAccent))
                .setContentIntent(pendingIntent);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
//
//    public static void sendNotificationToUser(String topic_name, final String message, String TAG) {
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        final DatabaseReference notifications = ref.child("notificationRequests");
//        Map notification = new HashMap<>();
//        notification.put("topic_name", topic_name);
//        notification.put("message", message);
//        notification.put("click_action", TAG);
//        notifications.push().setValue(notification);
//    }

}