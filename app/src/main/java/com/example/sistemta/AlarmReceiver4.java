package com.example.sistemta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlarmReceiver4 extends BroadcastReceiver {
    DatabaseReference mDatabase;
    @Override
    public void onReceive(Context context, Intent intent) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Work").setValue("0");
    }
}
