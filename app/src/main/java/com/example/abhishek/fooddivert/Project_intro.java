package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Project_intro extends AppCompatActivity {

    int time_delay2=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_intro);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                startActivity(new Intent(Project_intro.this,Register_page.class));
            }
        },time_delay2);


    }
}
