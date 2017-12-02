package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

public class Receiver_food_image extends AppCompatActivity {

    String image;
    ImageView food_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_food_image);

        food_image=(ImageView)findViewById(R.id.imageView_receiver);
        Intent x=getIntent();

        image=x.getStringExtra("image1");
        Toast.makeText(Receiver_food_image.this,image,Toast.LENGTH_SHORT).show();

        byte[] decode_image= Base64.decode(image,Base64.DEFAULT);
        Bitmap bitmap_image= BitmapFactory.decodeByteArray(decode_image,0,decode_image.length);

        food_image.setImageBitmap(bitmap_image);





    }
}
