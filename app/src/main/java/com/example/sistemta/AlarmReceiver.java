package com.example.sistemta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlarmReceiver extends BroadcastReceiver {
    DatabaseReference mDatabase;

    @Override
    public void onReceive(Context context, Intent intent) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Work").setValue(1);
        //NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"My Notif");
        //builder.setContentTitle("My Notif");
        //builder.setContentText("Hello bro");
        //builder.setSmallIcon(R.drawable.ic_launcher_background);
        //builder.setAutoCancel(true);
        //NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        //managerCompat.notify(1,builder.build());

    }
}
