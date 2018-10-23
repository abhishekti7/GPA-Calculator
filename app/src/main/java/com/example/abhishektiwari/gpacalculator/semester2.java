package com.example.abhishektiwari.gpacalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class semester2 extends AppCompatActivity {

    private Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7;
    private ArrayAdapter<CharSequence> adapter;
    private int flag = 0;
    private int value1,value2,value3,value4,value5,value6,value7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner4 = (Spinner)findViewById(R.id.spinner4);
        spinner5 = (Spinner)findViewById(R.id.spinner5);
        spinner6 = (Spinner)findViewById(R.id.spinner6);
        spinner7 = (Spinner)findViewById(R.id.spinner7);
        setContentView(R.layout.activity_semester2);
    }
}
