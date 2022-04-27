package com.example.sistemta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.sistemta.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.Calendar;

public class PopAlarmActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    RadioButton cb1, cb2, cb3;
    RadioGroup radioG;
    String days;
    DatabaseReference mDatabase;
    Button btn1,btn2,btnSet;
    MaterialTimePicker time1;
    TextView t1,t2;
    boolean isFromClicked = false;


    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        if (isFromClicked){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            c.set(Calendar.SECOND,0);
            t1.setText("Jam: " + hourOfDay + "Menit: "+ minute);
            setAlarm1(c);
        }
        else{
            t2.setText("Jam: " + hourOfDay + "Menit: "+ minute);
        }


    }

    private void setAlarm1(Calendar c) {
        AlarmManager alarm1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
        alarm1.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pendingIntent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_alarm);
        t1 = findViewById(R.id.Alarm1);
        t2 = findViewById(R.id.Alarm2);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        cb1 = (RadioButton) findViewById(R.id.sekaliCheck);
        cb2 = (RadioButton) findViewById(R.id.harikerjaCheck);
        cb3 = (RadioButton) findViewById(R.id.hariliburCheck);

        btn1 = findViewById(R.id.startButton1);
        btn2 = findViewById(R.id.endButton1);

        setCurrentTimeOnView();
        addListenerButton();

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(), "timepicker");
                    isFromClicked = false;
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment timePicker = new TimePickerFragment2();
                    timePicker.show(getSupportFragmentManager(), "timepicker");
                    isFromClicked = true;
                }
            });




    }

    private void addListenerButton() {

    }

    private void setCurrentTimeOnView() {
    }

}