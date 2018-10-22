package com.example.abhishektiwari.gpacalculator;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class about extends AppCompatActivity {

    private TextView about, about_para;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        about = (TextView)findViewById(R.id.about_the_developer);
        about_para = (TextView)findViewById(R.id.about_the_developer_para);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/PermanentMarker-Regular.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getAssets(),"fonts/Mali-Regular.ttf");
        about.setTypeface(typeface);
        about_para.setTypeface(typeface1);

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
