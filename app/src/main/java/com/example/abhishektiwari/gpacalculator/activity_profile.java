package com.example.abhishektiwari.gpacalculator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user_name = (TextView)findViewById(R.id.user_name);
        user_email = (TextView)findViewById(R.id.user_email);
        user_college = (TextView)findViewById(R.id.user_college);
        edit_profile = (Button)findViewById(R.id.edit_profile);
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
                user_name.setText("Name:     "+profile.userProfile.getName());
                user_email.setText("Email:    "+profile.userProfile.getEmail());
                user_college.setText("College:  "+profile.userProfile.getCollege());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(activity_profile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_profile.this,SlideTutorial.class));
            }
        });
    }
}
