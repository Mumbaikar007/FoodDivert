package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Receiver_status extends AppCompatActivity {


    ArrayList arrayList_status;
    ArrayList arrayList_status_uid;
    ListView listView_status;
    ArrayAdapter adapter_status;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Donor_Info_storage donor_info_storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_status);

        listView_status=(ListView)findViewById(R.id.listview_receiver_status);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();


        arrayList_status=new ArrayList();
        arrayList_status_uid=new ArrayList();


       // Toast.makeText(this,medium.status_uid.get(0).toString(),Toast.LENGTH_SHORT).show();

       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {


               for (DataSnapshot ds:dataSnapshot.getChildren())
               {
                   Donor_Info_storage donor_info_storage=ds.getValue(Donor_Info_storage.class);

                   if(donor_info_storage.book.equals("1"))
                   {

                       arrayList_status.add(donor_info_storage.food_name);
                       arrayList_status_uid.add(ds.getKey());

                   }

               }


           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });

        adapter_status=new ArrayAdapter(Receiver_status.this,android.R.layout.simple_list_item_1,arrayList_status);
        listView_status.setAdapter(adapter_status);

        listView_status.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String info_uid=arrayList_status_uid.get(i).toString();
                startActivity(new Intent(Receiver_status.this,Receiver_click_activity.class).putExtra("uid",info_uid));

            }
        });



    }
}
