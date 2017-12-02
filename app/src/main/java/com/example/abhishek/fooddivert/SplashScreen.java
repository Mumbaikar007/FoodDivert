package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread ts=new Thread(){

            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent in = new Intent(getApplicationContext(),AlredyRegister.class);
                    startActivity(in);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ts.start();
    }
}
