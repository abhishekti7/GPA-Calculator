package com.example.abhishektiwari.gpacalculator;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.TextureView;
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


public class GPAcalculator extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseAuth firebaseAuth;
    private DrawerLayout MyDrawer;
    private ActionBarDrawerToggle myToggle;
    private FirebaseDatabase firebaseDatabase;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private Button submit;
    private int option;
    private TextView navName;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpacalculator);

        spinner = (Spinner)findViewById(R.id.spinner);
        submit = (Button)findViewById(R.id.semester_submit);
        adapter = ArrayAdapter.createFromResource(this,R.array.semester_list,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("--Select--")){
                    option = position;

                }else{
                    option = position;
                }

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (option){
                            case 0:
                                Toasty.warning(GPAcalculator.this,"Please select your semester to continue",Toast.LENGTH_SHORT).show();
                                break;

                            case 1:
                                startActivity(new Intent(GPAcalculator.this,semester1.class));
                                break;
                            case 2:
                                startActivity(new Intent(GPAcalculator.this,semester2.class));
                                break;
                            case 3:
                                startActivity(new Intent(GPAcalculator.this,semester3.class));
                                break;
                            case 4:
                                startActivity(new Intent(GPAcalculator.this,semester4.class));
                                break;
                            case 5:
                                startActivity(new Intent(GPAcalculator.this,semester5.class));
                                break;
                            case 6:
                                startActivity(new Intent(GPAcalculator.this,semester6.class));
                                break;
                            case 7:
                                startActivity(new Intent(GPAcalculator.this,semester7.class));
                                break;
                            case 8:
                                startActivity(new Intent(GPAcalculator.this,semester8.class));
                                break;
                        }
                    }
                });
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        NavigationView mNavigationView = (NavigationView) findViewById(R.id.my_navigation_view);
        if(mNavigationView!=null){
            mNavigationView.setNavigationItemSelectedListener(GPAcalculator.this);
        }
        MyDrawer = (DrawerLayout)findViewById(R.id.MyDrawer);
        myToggle = new ActionBarDrawerToggle(this, MyDrawer, R.string.open, R.string.close);
        MyDrawer.addDrawerListener(myToggle);
        myToggle.syncState();

        View headerView = mNavigationView.getHeaderView(0);
        navName = (TextView)headerView.findViewById(R.id.navName);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Profile profile = dataSnapshot.getValue(Profile.class);
                navName.setText(profile.userProfile.name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(myToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(GPAcalculator.this, LoginActivity.class));
                break;

            case R.id.profile:
                startActivity(new Intent(GPAcalculator.this, activity_profile.class));
                break;

            case R.id.About:
                startActivity(new Intent(GPAcalculator.this, about.class));
                break;
            case R.id.settings:
                startActivity(new Intent(GPAcalculator.this,settings.class));
                break;
        }
        MyDrawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
