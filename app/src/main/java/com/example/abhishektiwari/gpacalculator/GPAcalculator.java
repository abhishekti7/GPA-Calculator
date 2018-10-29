package com.example.abhishektiwari.gpacalculator;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abhishektiwari.gpacalculator.menu.DrawerAdapter;
import com.example.abhishektiwari.gpacalculator.menu.DrawerItem;
import com.example.abhishektiwari.gpacalculator.menu.SimpleItem;
import com.example.abhishektiwari.gpacalculator.menu.SpaceItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;

import es.dmoral.toasty.Toasty;


public class GPAcalculator extends AppCompatActivity implements TabLayout.OnTabSelectedListener,DrawerAdapter.OnItemSelectedListener{

    private FirebaseAuth firebaseAuth;
    private DrawerLayout MyDrawer;
    private ActionBarDrawerToggle myToggle;
    private FirebaseDatabase firebaseDatabase;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    private Button submit, check_cgpa;
    private int option;
    private TextView navName;

    private SlidingRootNav slidingRootNav;
    private static final int POS_DASHBOARD = 0;
    private static final int POS_ACCOUNT = 1;
    private static final int POS_ABOUT = 2;
    private static final int POS_LOGOUT = 3;
    private static final int POS_SETTINGS = 5;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpacalculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        check_cgpa = (Button)findViewById(R.id.cgpa_calc);
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

        check_cgpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GPAcalculator.this,calculate_cgpa.class));
            }
        });
        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter drawadapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_ABOUT),
                createItemFor(POS_SETTINGS),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        drawadapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(drawadapter);

        drawadapter.setSelected(POS_DASHBOARD);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    @Override
    public void onItemSelected(int position) {
        if (position == POS_LOGOUT) {
            firebaseAuth.signOut();
            startActivity(new Intent(GPAcalculator.this,LoginActivity.class));
        }
        if (position == POS_ACCOUNT) {
            Intent i =new Intent(getApplication(), activity_profile.class);
            startActivity(i);
        }


        if (position == POS_ABOUT) {
            startActivity(new Intent(GPAcalculator.this, about.class));
        }
        if(position == POS_SETTINGS){
            startActivity(new Intent(GPAcalculator.this, settings.class));
        }
        slidingRootNav.closeMenu();
        // Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
        // showFragment(selectedScreen);
    }



    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.colorAccent))
                .withTextTint(color(R.color.colorAccent))
                .withSelectedIconTint(color(R.color.colorPrimary))
                .withSelectedTextTint(color(R.color.colorPrimary));
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }
    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


}
