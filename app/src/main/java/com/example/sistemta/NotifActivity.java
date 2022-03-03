package com.example.sistemta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class NotifActivity extends AppCompatActivity {
    RecyclerView recView;
    String notifH[], notifD[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        notifH = getResources().getStringArray(R.array.notificationHead);
        notifD = getResources().getStringArray(R.array.notificationDesc);

        recView = findViewById(R.id.recView);

        myAdapter MyAdapter = new myAdapter(this, notifH,notifD);
        recView.setAdapter(MyAdapter);
        recView.setLayoutManager(new LinearLayoutManager(this));

    }
}