package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Receiver_retrive_information extends AppCompatActivity  {

    ListView listView;
    Spinner spinner_locations;
    Spinner spinner_veg_or_nonveg;

   // private EditText editTextAddress ;
   // private Button buttonAddress;

    private ArrayList<String>arrayList_foodnames ;
    ArrayList<String>arrayList_veg_or_nonveg;
    private ArrayList<String>arrayListUid;
    ArrayList<String>arrayList_locations;
    //ArrayAdapter<String> adapter;


    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

     ArrayAdapter<String> adapter;
    ArrayAdapter<String>adapter_locations;
    ArrayAdapter<String>adapter_veg_or_nonveg;
   // List list_uid;
    String uid;
    String selected_location;

    //int z=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_retrive_information);

        listView=(ListView)findViewById(R.id.listview_receiver_retrive);
        spinner_locations=(Spinner)findViewById(R.id.spinner_stations_names);
        spinner_veg_or_nonveg=(Spinner)findViewById(R.id.spinner_veg_or_nonveg);

       // editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        //buttonAddress = (Button) findViewById(R.id.buttonAddress);

         arrayListUid= new ArrayList<>();
        arrayList_foodnames = new ArrayList<String>();
        arrayList_locations=new ArrayList<>();
        arrayList_veg_or_nonveg=new ArrayList<>();
        //arrayList_foodnames=new ArrayList<String>();
        //arrayList=new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
            firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseUser=firebaseAuth.getCurrentUser();




       // arrayList_locations.add("All");
        arrayList_locations.add("Nothing Selected");
        arrayList_locations.add("Dadar");
        arrayList_locations.add("Andheri");
        arrayList_locations.add("Borivali");
        arrayList_locations.add("Bandra");

        arrayList_veg_or_nonveg.add("Nothing Selected");
        arrayList_veg_or_nonveg.add("Veg");
        arrayList_veg_or_nonveg.add("Non-Veg");
        arrayList_veg_or_nonveg.add("both Veg and Non-Veg");

        adapter_locations=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList_locations);
        spinner_locations.setAdapter(adapter_locations);

        adapter_veg_or_nonveg=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList_veg_or_nonveg);
        spinner_veg_or_nonveg.setAdapter(adapter_veg_or_nonveg);

        if(firebaseUser!=null) {
            uid = firebaseUser.getUid();
            //Toast.makeText(this, uid, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "null 1", Toast.LENGTH_LONG).show();
        }

        //final ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                showData(dataSnapshot);
                // Toast.makeText(Receiver_retrive_information.this,"inside ondatachange",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

    public void showData(DataSnapshot dataSnapshot) {

        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            /*
            //Toast.makeText(Receiver_retrive_information.this,ds.child("name").toString(),Toast.LENGTH_LONG).show();




          Receiver_info_retrival_storage retrive=new Receiver_info_retrival_storage();
           // Receiver_info_retrival_storage r=ds.child(uid).getValue(Receiver_info_retrival_storage.class);

            retrive.setName(ds.child("name").getValue(String.class));
            retrive.setFood_name(ds.child("food_name").getValue(String.class));
            retrive.setUid(ds.getKey());
            /*retrive.setName(ds.child("phone_no").getValue(String.class));
            retrive.setName(ds.child("address").getValue(String.class));
            retrive.setName(ds.child("food_name").getValue(String.class));
            retrive.setName(ds.child("veg").getValue(String.class));
            retrive.setName(ds.child("non_veg").getValue(String.class));
            retrive.setName(ds.child("quantity").getValue(String.class));
            retrive.setName(ds.child("best_before").getValue(String.class));
            retrive.setName(ds.child("available_time").getValue(String.class));
            retrive.setName(ds.child("latitude").getValue(String.class));
            retrive.setName(ds.child("longitude").getValue(String.class));


           arrayList.add(retrive.getFood_name());
            // list_uid.add(retrive.getUid());
           // Toast.makeText(Receiver_retrive_information.this,ds.getKey(),Toast.LENGTH_SHORT).show();

            */

            //Toast.makeText(Receiver_retrive_information.this,"before",Toast.LENGTH_SHORT).show();
            Donor_Info_storage donor_info_storage = ds.getValue(Donor_Info_storage.class);
            //Toast.makeText(Receiver_retrive_information.this,"after",Toast.LENGTH_SHORT).show();

            if(donor_info_storage.book.equals("0")) {
                arrayList_foodnames.add(donor_info_storage.food_name);
                arrayListUid.add(ds.getKey());

            }
        }


        adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList_foodnames);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String info_uid=  arrayListUid.get(i) ;
               //Toast.makeText(Receiver_retrive_information.this,info_uid,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Receiver_retrive_information.this,Receiver_click_activity.class).putExtra("uid",info_uid));

            }
        });



    }



    void singleQuery(String string, int type){

        final Query query;
        if ( type == 0 ){
            query = databaseReference.orderByChild("Get_All").equalTo(string);
        }
        else if ( type == 1) {
            query = databaseReference.orderByChild("station").equalTo(string);
        }
        else {
            query = databaseReference.orderByChild("food_type").equalTo(string);
        }

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    void locationFoodTypeQuery(String user_Location, final String food_type){

        final Query querylocationfoodtype = databaseReference.orderByChild("station").equalTo(user_Location);


        querylocationfoodtype.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Donor_Info_storage donor_info_storage = ds.getValue(Donor_Info_storage.class);

                    if ( donor_info_storage.food_type.equals(food_type)) {
                        arrayList_foodnames.add(donor_info_storage.food_name);
                        arrayListUid.add(ds.getKey());
                    }

                }


                adapter =new ArrayAdapter<String>(Receiver_retrive_information.this,android.R.layout.simple_list_item_1,arrayList_foodnames);
                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        String info_uid=  arrayListUid.get(i) ;
                        //Toast.makeText(Receiver_retrive_information.this,info_uid,Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Receiver_retrive_information.this,Receiver_click_activity.class).putExtra("uid",info_uid));

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*ref
                .orderBy('genre')
                .startAt('comedy').endAt('comedy')
                .on('value', function(snapshot) {
            var movie = snapshot.val();
            if (movie.lead == 'Jack Nicholson') {
                console.log(movie);
            }
        });*/

    }

    public void check(View view) {
        String user_location = spinner_locations.getSelectedItem().toString();
        String veg_or_not = spinner_veg_or_nonveg.getSelectedItem().toString();

        arrayListUid.clear();
        arrayList_foodnames.clear();

        if (user_location != "Nothing Selected" && veg_or_not == "Nothing Selected") {
            singleQuery( user_location, 1);
        }

        else if (user_location != "Nothing Selected" && veg_or_not != "Nothing Selected"){
            locationFoodTypeQuery( user_location, veg_or_not);
        }

        else if (user_location == "Nothing Selected" && veg_or_not != "Nothing Selected"){
            singleQuery(veg_or_not, 2);
        }

        else {
            singleQuery( "Get_All", 0);
        }


    }

    public  void receiver_status(View view)
    {

        startActivity(new Intent(Receiver_retrive_information.this,Receiver_status.class));

    }

}
