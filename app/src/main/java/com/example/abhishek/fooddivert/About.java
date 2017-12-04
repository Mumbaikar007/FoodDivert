package com.example.abhishek.fooddivert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView About_us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        About_us=(TextView)findViewById(R.id.textView3);
       // About_us.setText("P -> PRANAV"+"\n"+"R -> ROSHAN"+"\n"+"A -> ABHISHEK"+"\n"+" T -> TANMAY"+"\n"+"");

        About_us.setText("FOODDIVERT is a revolutionary food sharing app. It allows you to donate your left overs to the needy. It has multiple features including current donation status, GPS location of donor, food quantity and serving details as well as transportation service. This app has an user friendly interface and graphics, helping you to do your good deed for the day!");


    }

}
