package com.example.sistemta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Switch sol, buz;
    DatabaseReference mDatabase;
    Dialog zDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zDialog = new Dialog(this);
     //   mDatabase = FirebaseDatabase.getInstance().getReference();

      //  if (sol.isChecked()){
       //     mDatabase.child("Solenoid").setValue("1");
        //}else{
         //   mDatabase.child("Solenoid").setValue("0");
        //}
        //if (buz.isChecked()){
        //    mDatabase.child("Buzzer").setValue("1");
        //}else{
         //   mDatabase.child("Buzzer").setValue("0");
        //}

    }
    public void ShowPopUp(View v){
        TextView txtclose;
        zDialog.setContentView(R.layout.popup_alarm);
        txtclose = (TextView) zDialog.findViewById(R.id.closePop);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zDialog.dismiss();
            }
        });
        zDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        zDialog.show();
    }

}