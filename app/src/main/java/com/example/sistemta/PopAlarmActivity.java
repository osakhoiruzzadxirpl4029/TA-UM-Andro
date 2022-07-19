package com.example.sistemta;

import static com.example.sistemta.TestNotif.channel1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.util.Calendar;

public class PopAlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    DatabaseReference mDatabase;
    Button btn1,btn2;
    TextView t1,t2;


    private NotificationManagerCompat notifManage;

    int isFromClicked = 0;


    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

        if (isFromClicked == 1){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            c.set(Calendar.SECOND,0);
            t1.setText("Pukul: " + hourOfDay+":"+minute+" WIB");
            setAlarm(c);

        }
        else if(isFromClicked == 2){
            Calendar d = Calendar.getInstance();
            d.set(Calendar.HOUR_OF_DAY, hourOfDay);
            d.set(Calendar.MINUTE, minute);
            d.set(Calendar.SECOND,0);
            t2.setText("Pukul: " + hourOfDay+":"+minute+" WIB");
            setAlarm2(d);
        }
        else if (isFromClicked == 3){
            Calendar e = Calendar.getInstance();
            e.set(Calendar.HOUR_OF_DAY, hourOfDay);
            e.set(Calendar.MINUTE, minute);
            e.set(Calendar.SECOND,0);
            t1.setText("Pukul: " + hourOfDay+":"+minute+" WIB");
            setAlarm3(e);

        }
        else if(isFromClicked == 4){
            Calendar f = Calendar.getInstance();
            f.set(Calendar.HOUR_OF_DAY, hourOfDay);
            f.set(Calendar.MINUTE, minute);
            f.set(Calendar.SECOND,0);
            t2.setText("Pukul: " + hourOfDay+":"+minute+" WIB");
            setAlarm4(f);
        }


    }

    private void setAlarm4(Calendar f) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver4.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, f.getTimeInMillis(),24*60*60*1000, pendingIntent);
        Toast.makeText(this, "Off Alarm Aktif", Toast.LENGTH_SHORT).show();
    }

    private void setAlarm3(Calendar e) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver3.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, e.getTimeInMillis(),24*60*60*1000, pendingIntent);
        Toast.makeText(this, "On Alarm Aktif", Toast.LENGTH_SHORT).show();
    }

    private void setAlarm2(Calendar d) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver2.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, d.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "Off Alarm Aktif", Toast.LENGTH_SHORT).show();

    }

    private void setAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "On Alarm Aktif", Toast.LENGTH_SHORT).show();
    }

    //public void builderNotif(){
    //    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotif");
    //    builder.setContentTitle("My Notif");
    //    builder.setContentText("Hello bro");
    //    builder.setSmallIcon(R.drawable.ic_launcher_background);
    //    builder.setAutoCancel(true);

    //    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
    //    managerCompat.notify(1,builder.build());
    //}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_alarm);
        notifManage = NotificationManagerCompat.from(this);
        t1 = findViewById(R.id.Alarm1);
        t2 = findViewById(R.id.Alarm2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        SwitchMaterial once = findViewById(R.id.once);
        SwitchMaterial daily = findViewById(R.id.daily);
        btn1 = findViewById(R.id.startButton1);
        btn2 = findViewById(R.id.endButton1);


        setCurrentTimeOnView();
        addListenerButton();

        once.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    daily.setChecked(false);
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DialogFragment timePicker = new TimePickerFragment();
                            timePicker.show(getSupportFragmentManager(), "timepicker");
                            isFromClicked = 1;
                        }
                    });
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DialogFragment timePicker = new TimePickerFragment2();
                            timePicker.show(getSupportFragmentManager(), "timepicker");
                            isFromClicked = 2;
                        }
                    });
                }
            }
        });
        daily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    once.setChecked(false);
                    btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DialogFragment timePicker = new TimePickerFragment3();
                            timePicker.show(getSupportFragmentManager(), "timepicker");
                            isFromClicked = 3;
                        }
                    });
                    btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DialogFragment timePicker = new TimePickerFragment4();
                            timePicker.show(getSupportFragmentManager(), "timepicker");
                            isFromClicked = 4;
                        }
                    });
                }
            }
        });

    }

    private void addListenerButton() {

    }

    private void setCurrentTimeOnView() {
    }


}