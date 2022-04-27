package com.example.sistemta;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID1";
    public static final String channelName = "channelID1";
    public static final String channelID2 = "channelID2";
    public static final String channelName2 = "channelID2";
    private NotificationManager nManager;
    public NotificationHelper(Context base) {

        super(base);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O){
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel1 = new NotificationChannel(channelID,channelName, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

    }
    public NotificationManager getnManager() {
        if (nManager == null) {
            nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return nManager;
    }
    public NotificationCompat.Builder getChannel1Notification(){
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Satu")
                .setContentText("Satu Desc");
    }
    public NotificationCompat.Builder getChannel2Notification(){
        return new NotificationCompat.Builder(getApplicationContext(), channelID2)
                .setContentTitle("Dua")
                .setContentText("Dua Desc");
    }
}
