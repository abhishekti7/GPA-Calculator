package com.example.abhishektiwari.gpacalculator;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class semester1 extends AppCompatActivity {

    private Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private Button gpa_calc;
    private TextView result;
    private ArrayAdapter<CharSequence> adapter;
    private int flag = 0;
    private int ex,a,b,c,d,p;
    private int value1,value2,value3,value4,value5,value6,value7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester1);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);
                ex = Integer.parseInt(profile.gradeList.ex);
                a = Integer.parseInt(profile.gradeList.a);
                b = Integer.parseInt(profile.gradeList.b);
                c = Integer.parseInt(profile.gradeList.c);
                d = Integer.parseInt(profile.gradeList.d);
                p = Integer.parseInt(profile.gradeList.p);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner4 = (Spinner)findViewById(R.id.spinner4);
        spinner5 = (Spinner)findViewById(R.id.spinner5);
        spinner6 = (Spinner)findViewById(R.id.spinner6);
        spinner7 = (Spinner)findViewById(R.id.spinner7);
        gpa_calc = (Button)findViewById(R.id.calc_gpa);
        result = (TextView)findViewById(R.id.result);
        adapter  = ArrayAdapter.createFromResource(this, R.array.grade_list,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);
        spinner7.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("--Select--")){
                }
                else{
                    flag = flag+1;
                    value1 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("--Select--")){
                }
                else{
                    flag = flag+1;
                    value2 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("--Select--")){
                }
                else{
                    flag = flag+1;
                    value3 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("--Select--")){
                }
                else{
                    flag = flag+1;
                    value4 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("--Select--")){
                }
                else{
                    flag = flag+1;
                    value5 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("--Select--")){
                }
                else{
                    flag = flag+1;
                    value6 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("--Select--")){
                }
                else{
                    flag = flag+1;
                    value7 = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        gpa_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag!=7){
                    Toasty.error(semester1.this,"Please enter grades for all subjects.",Toast.LENGTH_SHORT).show();
                }else{
                    calculateGPA();
                }

            }
        });

    }

    public void calculateGPA(){
        switch(value1){
            case 1:
                value1 = ex;
                break;

            case 2:
                value1 = a;
                break;

            case 3:
                value1 = b;
                break;
            case 4:
                value1 = c;
                break;
            case 5:
                value1 = d;
                break;
            case 6:
                value1 = p;
                break;

        }
        switch(value2){
            case 1:
                value2 = ex;
                break;

            case 2:
                value2 = a;
                break;

            case 3:
                value2 = b;
                break;
            case 4:
                value2 = c;
                break;
            case 5:
                value2 = d;
                break;
            case 6:
                value2 = p;
                break;

        }
        switch(value3){
            case 1:
                value3 = ex;
                break;

            case 2:
                value3 = a;
                break;

            case 3:
                value3 = b;
                break;
            case 4:
                value3 = c;
                break;
            case 5:
                value3 = d;
                break;
            case 6:
                value3 = p;
                break;

        }
        switch(value4){
            case 1:
                value4 = ex;
                break;

            case 2:
                value4 = a;
                break;

            case 3:
                value4 = b;
                break;
            case 4:
                value4 = c;
                break;
            case 5:
                value4 = d;
                break;
            case 6:
                value4 = p;
                break;

        }
        switch(value5){
            case 1:
                value5 = ex;
                break;

            case 2:
                value5 = a;
                break;

            case 3:
                value5 = b;
                break;
            case 4:
                value5 = c;
                break;
            case 5:
                value5 = d;
                break;
            case 6:
                value5 = p;
                break;

        }
        switch(value6){
            case 1:
                value6 = ex;
                break;

            case 2:
                value6 = a;
                break;

            case 3:
                value6 = b;
                break;
            case 4:
                value6 = c;
                break;
            case 5:
                value6 = d;
                break;
            case 6:
                value6 = p;
                break;

        }
        switch(value7){
            case 1:
                value7 = ex;
                break;

            case 2:
                value7 = a;
                break;

            case 3:
                value7 = b;
                break;
            case 4:
                value7 = c;
                break;
            case 5:
                value7 = d;
                break;
            case 6:
                value7 = p;
                break;

        }
        double gpa = value1*4+value2*3+value3*3+value4*4+value5*3+value6*2+value7*2;
        gpa = gpa/21;
        result.setText(String.format("Your GPA is: %.2f", gpa));

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        DatabaseReference grade = myRef.child("userGrades").child("sem1");

        grade.setValue(gpa);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/SpecialElite-Regular.ttf");
        result.setTypeface(typeface);
        result.setVisibility(View.VISIBLE);


    }
}
