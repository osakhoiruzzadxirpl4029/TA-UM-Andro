package com.example.sistemta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    Dialog zDialog;
    TextView sTime, eTime;
    int hour1, minute1;
    int hour2, minute2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zDialog = new Dialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        SwitchMaterial sole = findViewById(R.id.sol1);
        SwitchMaterial buzz = findViewById(R.id.buz1);
        sole.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mDatabase.child("Solenoid").setValue("1");
                }
                else{
                    mDatabase.child("Solenoid").setValue("0");
                }
            }
        });

        buzz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    mDatabase.child("Buzzer").setValue("1");
                }
                else{
                    mDatabase.child("Buzzer").setValue("0");
                }
            }
        });

    }

    public void AlarmSet(View view) {
        startActivity(new Intent(this, PopAlarmActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    @Override
    public void onBackPressed() {

    }
    //    public void ShowPopUp(View v){
//        TextView txtClose;
//        zDialog.setContentView(R.layout.popup_alarm);
//        sTime.findViewById(R.id.startTime);
//        eTime.findViewById(R.id.endTime);
//        txtClose = zDialog.findViewById(R.id.closePop);
//        txtClose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                zDialog.dismiss();
//            }
//        });
//        zDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        zDialog.show();
//    }


    //   public void startTimePick(View view) {
    //  TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
    //      @Override
    //      public void onTimeSet(TimePicker timePicker, int selectedHour1, int selectedMinute1) {
    //          hour1 = selectedHour1;
    //          minute1 = selectedMinute1;
    //          sTime.setText(String.format(Locale.getDefault(), "%02d:%02d",hour1,minute1));
    //      }
    //  };
    //  TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour1,minute1,true);
    //  timePickerDialog.setTitle("Pilih Waktu");
    //  timePickerDialog.show();
    //}

    //public void endTimePick(View view) {
    //  TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
    //      @Override
    //      public void onTimeSet(TimePicker timePicker, int selectedHour2, int selectedMinute2) {
    //          hour2 = selectedHour2;
    //          minute1 = selectedMinute2;
    //          eTime.setText(String.format(Locale.getDefault(), "%02d:%02d",hour2,minute2));
    //      }
    //  };
    //  TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour2,minute2,true);
    //  timePickerDialog.setTitle("Pilih Waktu");
    //  timePickerDialog.show();
    //}
}