package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Receiver_click_activity extends AppCompatActivity {

   // TextView donor_uid;
    FirebaseAuth firebaseAuth;
    ArrayList<String>arrayList_donor_info;
    ArrayAdapter<String>adapter;
    ListView listView_donor_info;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String longitude3;
    String latitude3;
    String uid;
    String food_pic;
    String address_for_location;
   // FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_click_activity);


        arrayList_donor_info=new ArrayList<>();
        listView_donor_info=(ListView)findViewById(R.id.listview_donor_info) ;


        Intent x=getIntent();
         uid= x.getStringExtra("uid");
     //   String name=x.getStringExtra("name");
       // donor_uid.setText(uid);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

       // Toast.makeText(this,uid,Toast.LENGTH_SHORT).show();
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {

               // Receiver_info_retrival_storage retrive=new Receiver_info_retrival_storage();
                Donor_Info_storage donor_info_storage2 = ds.getValue(Donor_Info_storage.class);


                //Toast.makeText(Receiver_click_activity.this,ds.toString(),Toast.LENGTH_SHORT).show();
                /*retrive.setName(ds.child("name").getValue(String.class));
                retrive.setPhone_no(ds.child("phone_no").getValue(String.class));
                retrive.setAddress(ds.child("address").getValue(String.class));
                retrive.setFood_name(ds.child("food_name").getValue(String.class));
                retrive.setVeg(ds.child("veg").getValue(String.class));
                retrive.setNon_veg(ds.child("non_veg").getValue(String.class));
                retrive.setQuantity(ds.child("quantity").getValue(String.class));
                retrive.setBest_before(ds.child("best_before").getValue(String.class));
                retrive.setAvailable_time(ds.child("available_time").getValue(String.class));*/
                //retrive.setLatitude(ds.child("latitude").getValue(String.class));
                //retrive.setLongitude(ds.child("longitude").getValue(String.class));

               // Toast.makeText(Receiver_click_activity.this,retrive.getName(),Toast.LENGTH_SHORT).show();
                //donor_uid.setText(retrive.getName()+"\n"+retrive.getPhone_no()+"\n"+retrive.getAddress()+"\n"+retrive.getFood_name()+"\n"
                      //  +retrive.getVeg()+"\n"+retrive.getNon_veg()+"\n"+retrive.getQuantity()+"\n"
                       // +retrive.getBest_before()+"\n"+retrive.getAvailable_time()+"\n"+retrive.getLatitude()+"\n"+retrive.getLongitude());

              /*  arrayList_donor_info.add(retrive.getName());
                arrayList_donor_info.add(retrive.getAddress());
                arrayList_donor_info.add(retrive.getAvailable_time());
                arrayList_donor_info.add(retrive.getBest_before());
                arrayList_donor_info.add(retrive.getQuantity());
                arrayList_donor_info.add(retrive.getFood_name());
                arrayList_donor_info.add(retrive.getPhone_no());
                arrayList_donor_info.add(retrive.getNon_veg());
                arrayList_donor_info.add(retrive.getVeg());*/

               // arrayList_donor_info.add(retrive.);
                //adapter=new ArrayAdapter(Receiver_click_activity.this,android.R.layout.simple_list_item_1,arrayList_donor_info);

               // listView_donor_info.setAdapter(adapter);


                arrayList_donor_info.add("Name: "+donor_info_storage2.name);
                arrayList_donor_info.add("Address: "+donor_info_storage2.address);
                arrayList_donor_info.add(donor_info_storage2.phone_no);
                arrayList_donor_info.add("Available Time: "+donor_info_storage2.available_time);
                arrayList_donor_info.add("Expiry (Time,Date): "+donor_info_storage2.best_before);
                arrayList_donor_info.add("Quantity: "+donor_info_storage2.quantity);
                //arrayList_donor_info.add("Veg: "+donor_info_storage2.veg);
                //arrayList_donor_info.add("Non Veg: "+donor_info_storage2.non_veg);
                arrayList_donor_info.add("Food Type: "+donor_info_storage2.food_type);
                arrayList_donor_info.add("Food Name: "+donor_info_storage2.food_name);
                arrayList_donor_info.add("Nearest Station: "+donor_info_storage2.station);
               //arrayList_donor_info.add(donor_info_storage2.latitude);
               //arrayList_donor_info.add(donor_info_storage2.longitude);
                address_for_location=donor_info_storage2.address;

                food_pic=donor_info_storage2.image_food;
                latitude3=donor_info_storage2.latitude;
                longitude3=donor_info_storage2.longitude;
                arrayList_donor_info.add("check food pic");

                adapter=new ArrayAdapter<String>(Receiver_click_activity.this,android.R.layout.simple_list_item_1,arrayList_donor_info);
                listView_donor_info.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listView_donor_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==2)
                {

                    Toast.makeText(Receiver_click_activity.this,adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(Receiver_click_activity.this,Call_or_msg.class).putExtra("contact_no",adapterView.getItemAtPosition(i).toString()));
                    Intent intent = new Intent(Receiver_click_activity.this,Call_or_msg.class);
                    intent.putExtra("contact_no",adapterView.getItemAtPosition(i).toString());
                    intent.putExtra("Name",adapterView.getItemAtPosition(0).toString());
                    intent.putExtra("donor_uid",uid);
                    startActivity(intent);
                }

                if(i==9)
                {
                    Toast.makeText(Receiver_click_activity.this,"check",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Receiver_click_activity.this,Receiver_food_image.class).putExtra("image1",food_pic));
                }



            }
        });
    }
    public  void  getLocation(View v) {

        Toast.makeText(this,latitude3+" done "+longitude3,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MapsActivity.class).putExtra("longitude",longitude3).putExtra("latitude",latitude3).putExtra("address",address_for_location));

      //  startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("geo:" + longitude3 + "," + latitude3)));

    }

}
