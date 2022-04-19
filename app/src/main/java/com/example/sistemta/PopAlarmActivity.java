package com.example.sistemta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PopAlarmActivity extends AppCompatActivity {
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7;
    String days;
    DatabaseReference mDatabase;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_alarm);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        cb1 = (CheckBox) findViewById(R.id.seninCheck);
        cb2 = (CheckBox) findViewById(R.id.selasaCheck);
        cb3 = (CheckBox) findViewById(R.id.rabuCheck);
        cb4 = (CheckBox) findViewById(R.id.kamisCheck);
        cb5 = (CheckBox) findViewById(R.id.jumatCheck);
        cb6 = (CheckBox) findViewById(R.id.sabtuCheck);
        cb7 = (CheckBox) findViewById(R.id.mingguCheck);

        btn1 = findViewById(R.id.setAlarmFix);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb1.isChecked()){
                    days += cb1.getText().toString();
                }
                if (cb2.isChecked()){
                    days += cb1.getText().toString();
                }
                if (cb3.isChecked()){
                    days += cb1.getText().toString();
                }
                if (cb4.isChecked()){
                    days += cb1.getText().toString();
                }
                if (cb5.isChecked()){
                    days += cb1.getText().toString();
                }
                if (cb6.isChecked()){
                    days += cb1.getText().toString();
                }
                if (cb7.isChecked()){
                    days += cb1.getText().toString();
                }
                mDatabase.child("Days").setValue(days);
            }
        });
    }

}