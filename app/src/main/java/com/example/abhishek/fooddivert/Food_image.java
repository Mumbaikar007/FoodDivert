package com.example.abhishek.fooddivert;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Food_image extends AppCompatActivity {

    ImageView imageView_food_pic;

    //FirebaseAuth firebaseAuth;
    //FirebaseUser firebaseUser;
    //DatabaseReference databaseReference;
    //Donor_Info_storage donor_info_storage;

    Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_image);


        imageView_food_pic=(ImageView)findViewById(R.id.imageView_food_pic);

      //  firebaseAuth=FirebaseAuth.getInstance();
       // firebaseUser=firebaseAuth.getCurrentUser();
        //databaseReference= FirebaseDatabase.getInstance().getReference();





    }


    public  void clicktopic(View view)
    {

        Intent in=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(in,10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==10)
        {

             image= (Bitmap) data.getExtras().get("data");
            imageView_food_pic.setImageBitmap(image);


        }

    }

    public  void onSave(View v)
    {


        Intent in1=getIntent();

        in1.putExtra("image_food",image);

        setResult(Activity.RESULT_OK,in1);
        finish();
     //   donor_info_storage=new Donor_Info_storage(image);
       // databaseReference.push().setValue(donor_info_storage);
        //Toast.makeText(this,"Done...",Toast.LENGTH_SHORT).show();




    }



}
