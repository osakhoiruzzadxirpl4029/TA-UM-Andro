package com.example.sistemta;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class TestNotif extends Application {
    public static final String channel1 = "channel1";
    public static final String channel2 = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();
        testnotif();
    }

    private void testnotif() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel Channel1 = new NotificationChannel(
                    channel1,"Channel 1", NotificationManager.IMPORTANCE_HIGH
            );
            Channel1.setDescription("This is channel 1");
            NotificationChannel Channel2 = new NotificationChannel(
                    channel2,"Channel 2", NotificationManager.IMPORTANCE_LOW
            );
            Channel2.setDescription("This is channel 2");

            NotificationManager manage = getSystemService(NotificationManager.class);
            manage.createNotificationChannel(Channel1);
            manage.createNotificationChannel(Channel2);

        }
    }
}
