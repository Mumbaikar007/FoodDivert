package com.example.abhishek.fooddivert;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class Donor_user_info2 extends AppCompatActivity {


    EditText foodname1;
    EditText foonquantity1;
    EditText date1;
    EditText time1;
    EditText address1;
    RadioButton veg1;
    RadioButton nonveg1;
    String longitude1;
    String latitude1;
    String uid1;
    //RadioButton veg_and_nonveg1;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Donor_Info_storage donor_info_storage;
    String veg;
    String nonveg;
    String food_type;
    String donorname;
    String donorphone;
    String donor_station;
    String image_string;

    String donoraddress;
    private static Boolean aBoolean = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_user_info2);

        foodname1=(EditText)findViewById(R.id.editText_donor_info2_foodname);
        foonquantity1=(EditText)findViewById(R.id.editText_donor_info2_foodquantity);
        date1=(EditText)findViewById(R.id.editText_donor_info2_foodnamedate);
        time1=(EditText)findViewById(R.id.editText_donor_info2_foodnametime);
        address1=(EditText)findViewById(R.id.editText6);
        veg1=(RadioButton)findViewById(R.id.radioButton);
        nonveg1=(RadioButton)findViewById(R.id.radioButton2);
        //veg_and_nonveg1=(RadioButton)findViewById(R.id.radioButton3);

        Intent x=getIntent();
        longitude1=x.getStringExtra("longitude");
        latitude1=x.getStringExtra("latitude");
        //uid1=x.getStringExtra("donoruid");
        donorname=x.getStringExtra("name");
        donorphone=x.getStringExtra("phone");
        donor_station=x.getStringExtra("address");
        //donoraddress = x.getStringExtra("address");

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();


        //Toast.makeText(this,latitude1+"-- donorinfo2",Toast.LENGTH_SHORT).show();
        //Toast.makeText(this,longitude1+"-- donorinfo2",Toast.LENGTH_SHORT).show();



    }
    public void onDone(View v)
    {
        String name=foodname1.getText().toString();
        String quantity=foonquantity1.getText().toString();
        String date=date1.getText().toString();
        String time=time1.getText().toString();
        String address=address1.getText().toString();

        if(veg1.isChecked())
        {
            //veg="yes";
            //nonveg = "no";
            food_type = "Veg";
        }
        else if(nonveg1.isChecked())
        {
            //nonveg="yes";
            //veg = "no";
            food_type = "Non-Veg";
        }


//        Toast.makeText(this,firebaseUser.getUid(),Toast.LENGTH_SHORT).show();






        donor_info_storage = new Donor_Info_storage("Not Yet",firebaseUser.getUid(),donorname,donorphone,donor_station,name,food_type,quantity,date,time,latitude1,longitude1,address,"0",image_string);
        //databaseReference.child(uid1).setValue(donor_info_storage);
       // databaseReference.child(firebaseUser.getUid()).push().setValue(donor_info_storage);
        //Toast.makeText(this,"Done...",Toast.LENGTH_SHORT).show();


        Intent in=getIntent();
        in.putExtra("donor_info", donor_info_storage);
        /*in.putExtra("food_name",name);
        in.putExtra("quantity",quantity);
        in.putExtra("date",date);
        //Toast.makeText(this,latitude+"send "+longitude,Toast.LENGTH_SHORT).show();
        in.putExtra("time",time);
        in.putExtra("food_type",food_type);*/
        setResult(Activity.RESULT_OK,in);
  //      Toast.makeText(this,"calling finish",Toast.LENGTH_SHORT).show();
        finish();



    }



    public  void takepic(View view)
    {

        startActivityForResult(new Intent(Donor_user_info2.this,Food_image.class),101);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101)
        {
              Bitmap image1=data.getParcelableExtra("image_food");

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            image1.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            // lz compression
            byte[]b=byteArrayOutputStream.toByteArray();

            image_string= Base64.encodeToString(b,Base64.DEFAULT);
    //        Toast.makeText(Donor_user_info2.this,image_string,Toast.LENGTH_SHORT).show();

        // Toast.makeText(Donor_user_info2.this,data.getStringExtra("image_food"),Toast.LENGTH_SHORT).show();



        }
    }
}
