package com.example.sistemta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Switch sol, buz;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (sol.isChecked()){
            mDatabase.child("Solenoid").setValue("1");
        }else{
            mDatabase.child("Solenoid").setValue("0");
        }
        if (buz.isChecked()){
            mDatabase.child("Buzzer").setValue("1");
        }else{
            mDatabase.child("Buzzer").setValue("0");
        }

    }

}