package com.example.sistemta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button mSol1, mSol2, mBuz1, mBuz2;

    FirebaseDatabase root;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSol1 = findViewById(R.id.sol1);
        mBuz1 = findViewById(R.id.buzzer1);
        mSol2 = findViewById(R.id.sol2);
        mBuz2 = findViewById(R.id.buzzer2);

        mSol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root = FirebaseDatabase.getInstance();
                ref = root.getReference("Solenoid");
                ref.setValue("1");
            }
        });

        mSol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root = FirebaseDatabase.getInstance();
                ref = root.getReference("Solenoid");
                ref.setValue("0");
            }
        });

        mBuz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root = FirebaseDatabase.getInstance();
                ref = root.getReference("Buzzer");
                ref.setValue("1");
            }
        });

        mBuz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                root = FirebaseDatabase.getInstance();
                ref = root.getReference("Buzzer");
                ref.setValue("0");
            }
        });



    }
}