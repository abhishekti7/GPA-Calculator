package com.example.abhishektiwari.gpacalculator;

import android.arch.lifecycle.SingleGeneratedAdapterObserver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.HttpCookie;

public class SlideTutorial extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout slideDots;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    private Button nextBtn;
    private int currPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_tutorial);

        SharedPreferences sharedPreferences = getSharedPreferences("sliderIndex",Context.MODE_PRIVATE);
        int index = sharedPreferences.getInt("index",0);
        if(index==1){
            startActivity(new Intent(SlideTutorial.this,LoginActivity.class));
            SlideTutorial.this.finish();
        }

        nextBtn = (Button)findViewById(R.id.next);
        viewPager = (ViewPager)findViewById(R.id.slideViewPager);
        slideDots = (LinearLayout)findViewById(R.id.slideDots);
        sliderAdapter  = new SliderAdapter(this);



        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SlideTutorial.this,LoginActivity.class));
                SlideTutorial.this.finish();
            }
        });

        viewPager.setAdapter(sliderAdapter);
        savePreference();
        addDots(0);
        viewPager.addOnPageChangeListener(viewListener);
    }

    public void addDots(int position){
        mDots = new TextView[3];
        slideDots.removeAllViews();

        for(int i=0; i<mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparent));

            slideDots.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDots(i);
            currPage = i;

            if(i==0){
                nextBtn.setEnabled(true);
                nextBtn.setText("Skip");
            }else if(i==1){
                nextBtn.setEnabled(true);
                nextBtn.setText("Skip");
            }else{
                nextBtn.setEnabled(true);
                nextBtn.setText("Login");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void savePreference(){
        SharedPreferences sharedPreferences = getSharedPreferences("sliderIndex",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("index",1);
        editor.apply();
    }
}
