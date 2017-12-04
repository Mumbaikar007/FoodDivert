package com.example.abhishek.fooddivert;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Doners_Listview extends AppCompatActivity implements View.OnClickListener {

    private Button button_add;
    private Button button_donate;
    private ListView listView_add_items;

    private ArrayList<String> arrayList_food_name;
    private ArrayList<Donor_Info_storage> arrayList_doner_object;
    ArrayAdapter<String> adapter;

    String longitude1;
    String latitude1;
    //String uid1;
    String donorname;
    String donorphone;
    String donoraddress;

    /*
    String food_name = "";
    String quantity = "";
    String food_type= "";
    String date = "";
    String time = "";*/

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doners__listview);

        button_add = (Button) findViewById(R.id.button_add_item);
        button_donate = (Button) findViewById(R.id.button_donate);
        listView_add_items = (ListView) findViewById(R.id.listview_add_items);

        arrayList_food_name = new ArrayList<>();
        arrayList_doner_object = new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseUser=firebaseAuth.getCurrentUser();

        if(firebaseUser== null) {
            finish();
            startActivity(new Intent(this, AlredyRegister.class) );
        }


        Intent x=getIntent();

        // from doner user info

        longitude1=x.getStringExtra("longitude");
        latitude1=x.getStringExtra("latitude");
        //uid1=x.getStringExtra("donoruid");
        donorname=x.getStringExtra("name");
        donorphone=x.getStringExtra("phone");
        donoraddress=x.getStringExtra("address");

        /*// from doner user info 2
        food_name = x.getStringExtra("food_name");
        quantity = x.getStringExtra("quantity");
        date = x.getStringExtra("date");
        time = x.getStringExtra("time");
        food_type = x.getStringExtra("food_type");*/


        listView_add_items.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {

                //Toast.makeText(RecieverActivity.this,"pressed item " + index + " !!",Toast.LENGTH_SHORT).show();

                alertMessage(index);
                return false;


            }});

        button_donate.setOnClickListener(this);
        button_add.setOnClickListener(this);
    }


    public void alertMessage(final int index){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
                        Toast.makeText(Doners_Listview.this, "Item Deleted ... ",Toast.LENGTH_LONG).show();
                        arrayList_food_name.remove(index);
                        arrayList_doner_object.remove(index);
                        adapter = new ArrayAdapter<String>(Doners_Listview.this, android.R.layout.simple_list_item_1, arrayList_food_name);
                        listView_add_items.setAdapter(adapter);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        Toast.makeText(Doners_Listview.this, "Item not Deleted ... ", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirm Delete ... ")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();


    }



    @Override
    public void onClick(View view) {

        if ( view == button_add){
            Intent in=new Intent(Doners_Listview.this,Donor_user_info2.class);
            //in.putExtra("donoruid",uid);
            in.putExtra("latitude",latitude1+"");
            in.putExtra("longitude",longitude1+"");
            //Toast.makeText(this,latitude+"send "+longitude,Toast.LENGTH_SHORT).show();
            in.putExtra("name",donorname);
            in.putExtra("phone",donorphone);
            in.putExtra("address",donoraddress);
            //Toast.makeText(this,"Asking Result",Toast.LENGTH_SHORT).show();
            startActivityForResult(in, 1);

        }

        if (view == button_donate){


            //databaseReference.child(uid1).setValue(donor_info_storage);
            for (Donor_Info_storage donor_info_storage: arrayList_doner_object) {
                databaseReference.push().setValue(donor_info_storage);
            }

            Toast.makeText(this,"Done...",Toast.LENGTH_SHORT).show();

            Toast.makeText(this,"Thank You for donation",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Doners_Listview.this,Donor_or_Receiver.class));

        }


    }

    // Call Back method  to get the Message form other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Toast.makeText(Doners_Listview.this,"Result Recieved",Toast.LENGTH_SHORT).show();
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        //Toast.makeText(Doners_Listview.this,"Result Recieved",Toast.LENGTH_SHORT).show();
        if(requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK) {
               // Toast.makeText(Doners_Listview.this, " req code 1", Toast.LENGTH_SHORT).show();
                Donor_Info_storage donor_info_storage = data.getParcelableExtra("donor_info");
                arrayList_food_name.add(donor_info_storage.food_name);
                arrayList_doner_object.add(donor_info_storage);



                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList_food_name);
                listView_add_items.setAdapter(adapter);
            }
        }
    }


}