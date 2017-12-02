package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Team_intro extends AppCompatActivity {

    int time_delay=2000;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_intro);
        imageView=(ImageView)findViewById(R.id.imageView);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.team_intro_welcome_anim);

        imageView.setAnimation(animation);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                //finish();
                //startActivity(new Intent(Team_intro.this,project_intro.class));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Team_intro.this,Project_intro.class));

                    }
                },time_delay);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Team_intro.this,project_intro.class));

            }
        },time_delay);
        */


    }
}
