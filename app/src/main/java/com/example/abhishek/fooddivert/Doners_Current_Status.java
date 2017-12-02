package com.example.abhishek.fooddivert;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doners_Current_Status extends AppCompatActivity {

    private TextView textViewStatus;
    private ListView listView;
    private ArrayList<String> arrayList_food_status;
    private ArrayList<String> arrayListUid;
    private ArrayAdapter<String> arrayAdapter;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    //String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doners__current__status);

        textViewStatus = (TextView) findViewById(R.id.textView_Status);

        arrayList_food_status = new ArrayList<>();
        arrayListUid = new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseUser=firebaseAuth.getCurrentUser();

        if(firebaseUser== null) {
            finish();
            startActivity(new Intent(this, AlredyRegister.class) );
        }

        listView = (ListView) findViewById(R.id.listview_Status);
        arrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList_food_status);
        listView.setAdapter(arrayAdapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Donor_Info_storage donor_info_storage = dataSnapshot.getValue(Donor_Info_storage.class);

                if ( donor_info_storage.doner_uid.equals(firebaseUser.getUid() )) {
                    arrayList_food_status.add(donor_info_storage.food_name);
                    arrayListUid.add(dataSnapshot.getKey());

                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                String key = dataSnapshot.getKey();
                //Toast.makeText(RecieverActivity.this, "size = " + arrayListUserInformation.size(), Toast.LENGTH_SHORT).show();
                for (int i = 0 ; i < arrayListUid.size(); i++){
                    if (key.equals(arrayListUid.get(i)) ){
                        arrayList_food_status.remove(i);
                        arrayListUid.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {

                //Toast.makeText(RecieverActivity.this,"pressed item " + index + " !!",Toast.LENGTH_SHORT).show();

               alertMessage(index);
               return false;


            }});
    }

    public void alertMessage(final int index){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
                        Toast.makeText(Doners_Current_Status.this, "Item Deleted ... ",Toast.LENGTH_LONG).show();
                        databaseReference.child(arrayListUid.get(index)).removeValue();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        Toast.makeText(Doners_Current_Status.this, "Item not Deleted ... ", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirm Delete ... ")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();


    }


    public  void goNGO(View view)
    {

        startActivity(new Intent(Doners_Current_Status.this,NGO_contacts.class));

    }


}
