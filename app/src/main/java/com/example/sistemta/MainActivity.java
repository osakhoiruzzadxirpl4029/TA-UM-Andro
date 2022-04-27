package com.example.sistemta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.sistemta.databinding.ActivityMainBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseAuth fAuth;
    Dialog zDialog;
    Button btnLogout, btnNotif;
    MaterialAlertDialogBuilder alertD;
    private FirebaseUser username;
    private DatabaseReference privref;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();
        zDialog = new Dialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnLogout = findViewById(R.id.logoutButton);
        alertD = new MaterialAlertDialogBuilder(MainActivity.this);
        btnNotif = findViewById(R.id.notifButton);
        final TextView kondisi1 = findViewById(R.id.kondisiTitle);
        final TextView kondisi2 = findViewById(R.id.kondisiDesc);
        SwitchMaterial sole = findViewById(R.id.sol1);
        SwitchMaterial buzz = findViewById(R.id.buz1);

        ValueEventListener getListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String kondisi = snapshot.child("PIR").getValue(String.class);
                if(kondisi == "0"){
                    kondisi1.setText("Aman");
                    kondisi2.setText("Tidak terdeteksi pergerakan");
                }
                else{
                    kondisi1.setText("Tidak Aman");
                    kondisi2.setText("Terdeteksi pergerakan");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        //solenoid control
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

        //buzzer control
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

        //Logout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth.signOut();
                signOutUser();
            }
        });

        //notif button
        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifPage();
            }
        });

        //get Nama User dan Email
        username = FirebaseAuth.getInstance().getCurrentUser();
        privref = FirebaseDatabase.getInstance().getReference("User");
        userId = username.getUid();

        final TextView user_name = findViewById(R.id.user_name);
        final TextView user_email = findViewById(R.id.user_email);

        privref.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user_profile = snapshot.getValue(User.class);
                if(user_profile != null){
                    String nama = user_profile.name;
                    String email = user_profile.email;

                    //user profile show
                    user_name.setText(nama);
                    user_email.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //logout method
    private void signOutUser(){
        Intent logout = new Intent(MainActivity.this, LoginActivity.class);
         logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logout);
        finish();
    }
    //notif method
    private void notifPage() {
        startActivity(new Intent(this, NotifActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
    //Button to Alarm Page
    public void AlarmSet(View view) {
        startActivity(new Intent(this, PopAlarmActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
    // disabled back button
    @Override
    public void onBackPressed() {

    }
}