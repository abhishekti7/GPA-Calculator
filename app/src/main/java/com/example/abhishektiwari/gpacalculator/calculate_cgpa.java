package com.example.abhishektiwari.gpacalculator;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class calculate_cgpa extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;
    private TextView result;
    private double cgpa;
    private Button cgpa_calc;
    private EditText s1,s2,s3,s4,s5,s6,s7,s8;
    private String sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_cgpa);

        s1 = (EditText)findViewById(R.id.grade_sem1);
        s2 = (EditText)findViewById(R.id.grade_sem2);
        s3 = (EditText)findViewById(R.id.grade_sem3);
        s4 = (EditText)findViewById(R.id.grade_sem4);
        s5 = (EditText)findViewById(R.id.grade_sem5);
        s6 = (EditText)findViewById(R.id.grade_sem6);
        s7 = (EditText)findViewById(R.id.grade_sem7);
        s8 = (EditText)findViewById(R.id.grade_sem8);
        result = (TextView)findViewById(R.id.cgpa_result);
        cgpa_calc = (Button)findViewById(R.id.calc_cgpa);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/SpecialElite-Regular.ttf");

        result.setTypeface(typeface);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Initialising semester scroes...");
        progressDialog.show();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);
                sem1 = profile.userGrades.getSem1();
                sem2 = profile.userGrades.getSem2();
                sem3 = profile.userGrades.getSem3();
                sem4 = profile.userGrades.getSem4();
                sem5 = profile.userGrades.getSem5();
                sem6 = profile.userGrades.getSem6();
                sem7 = profile.userGrades.getSem7();
                sem8 = profile.userGrades.getSem8();
                s1.setText(sem1);
                s2.setText(sem2);
                s3.setText(sem3);
                s4.setText(sem4);
                s5.setText(sem5);
                s6.setText(sem6);
                s7.setText(sem7);
                s8.setText(sem8);
                progressDialog.dismiss();
                try{
                    cgpa = (Integer.parseInt(sem1)+Integer.parseInt(sem1))/2 +(Integer.parseInt(sem3)+Integer.parseInt(sem4))/2 + (Integer.parseInt(sem5)+Integer.parseInt(sem6))/2 + (Integer.parseInt(sem7)+Integer.parseInt(sem8))/2;
                }catch(NumberFormatException nfe){
                    nfe.printStackTrace();
                }
                cgpa = cgpa/4;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        cgpa_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(String.format("Your CGPA is: %.2f",cgpa));
                result.setVisibility(View.VISIBLE);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
