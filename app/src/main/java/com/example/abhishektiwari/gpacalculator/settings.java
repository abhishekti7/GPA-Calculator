package com.example.abhishektiwari.gpacalculator;

import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class settings extends AppCompatActivity {

    private RelativeLayout dropdown,change_grades;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private EditText grade_ex,grade_a,grade_b,grade_c,grade_d,grade_p;
    private Button submit_grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dropdown = (RelativeLayout)findViewById(R.id.dropdown);
        change_grades = (RelativeLayout)findViewById(R.id.change_grades);
        grade_ex = (EditText)findViewById(R.id.grade_ex);
        grade_a = (EditText)findViewById(R.id.grade_a);
        grade_b = (EditText)findViewById(R.id.grade_b);
        grade_c = (EditText)findViewById(R.id.grade_c);
        grade_d = (EditText)findViewById(R.id.grade_d);
        grade_p = (EditText)findViewById(R.id.grade_p);
        submit_grades = (Button)findViewById(R.id.submit_grades);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        change_grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdown.setVisibility(View.VISIBLE);

                DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Profile profile = dataSnapshot.getValue(Profile.class);
                        grade_ex.setText(profile.gradeList.getEx());
                        grade_a.setText(profile.gradeList.getA());
                        grade_b.setText(profile.gradeList.getB());
                        grade_c.setText(profile.gradeList.getC());
                        grade_d.setText(profile.gradeList.getD());
                        grade_p.setText(profile.gradeList.getP());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(settings.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        submit_grades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());

                GradeList gradeList = new GradeList(grade_ex.getText().toString(),grade_a.getText().toString(),grade_b.getText().toString(),grade_c.getText().toString(),grade_d.getText().toString(),grade_p.getText().toString());
                myRef.child("gradeList").setValue(gradeList);
                dropdown.setVisibility(View.GONE);
            }
        });
    }
}
