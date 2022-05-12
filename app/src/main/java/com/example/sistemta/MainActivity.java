package com.example.sistemta;

import static com.example.sistemta.TestNotif.channel1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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
    private NotificationManagerCompat notifManage;
    //scheduling ->
    //TextView sTime, eTime;
    //int hour1, minute1;
    //int hour2, minute2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();
        zDialog = new Dialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        btnLogout = findViewById(R.id.logoutButton);
        btnNotif = findViewById(R.id.notifButton);
        final TextView kondisi1 = findViewById(R.id.kondisiTitle);
        final TextView kondisi2 = findViewById(R.id.kondisiDesc);
        SwitchMaterial sole = findViewById(R.id.sol1);
        SwitchMaterial buzz = findViewById(R.id.buz1);


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
    //notif button
    private void notifPage() {
        startActivity(new Intent(this, NotifActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }
    //signout button
    private void signOutUser() {
        Intent logout = new Intent(MainActivity.this, LoginActivity.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logout);
        finish();
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




    //public void ShowPopUp(View v){
    //    TextView txtClose;
    //   zDialog.setContentView(R.layout.popup_alarm);
    //   sTime.findViewById(R.id.startTime);
    //    eTime.findViewById(R.id.endTime);
    //    txtClose = zDialog.findViewById(R.id.closePop);
    //    txtClose.setOnClickListener(new View.OnClickListener() {
    //        @Override
    //        public void onClick(View view) {
    //           zDialog.dismiss();
    //        }
    //    });
    //    zDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    //    zDialog.show();
    //}


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