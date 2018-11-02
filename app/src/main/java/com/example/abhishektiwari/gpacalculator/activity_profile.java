package com.example.abhishektiwari.gpacalculator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class activity_profile extends AppCompatActivity {

    private TextView user_name,user_email,user_college;
    private FirebaseAuth firebaseAuth;
    private Button edit_profile;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;
    private TextView sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_profile);

        sem1 = (TextView)findViewById(R.id.sem1);
        sem2 = (TextView)findViewById(R.id.sem2);
        sem3 = (TextView)findViewById(R.id.sem3);
        sem4 = (TextView)findViewById(R.id.sem4);
        sem5 = (TextView)findViewById(R.id.sem5);
        sem6 = (TextView)findViewById(R.id.sem6);
        sem7 = (TextView)findViewById(R.id.sem7);
        sem8 = (TextView)findViewById(R.id.sem8);
        user_name = (TextView)findViewById(R.id.user_name);
        user_email = (TextView)findViewById(R.id.user_email);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating User Profile...");
        progressDialog.show();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                Profile profile = dataSnapshot.getValue(Profile.class);
                String[] splits = profile.userProfile.getName().split(" +");
                user_name.setText(splits[0].toUpperCase()+"\n"+splits[1].toUpperCase());
                user_email.setText(profile.userProfile.getEmail());


                sem1.setText(profile.userGrades.getSem1());
                sem2.setText(profile.userGrades.getSem2());
                sem3.setText(profile.userGrades.getSem3());
                sem4.setText(profile.userGrades.getSem4());
                sem5.setText(profile.userGrades.getSem5());
                sem6.setText(profile.userGrades.getSem6());
                sem7.setText(profile.userGrades.getSem7());
                sem8.setText(profile.userGrades.getSem8());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(activity_profile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
