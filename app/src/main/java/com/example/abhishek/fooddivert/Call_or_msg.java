package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Call_or_msg extends AppCompatActivity {

    String phone_no;
    TextView textView_contact_no;
    EditText edittext_sms_content;
    SmsManager smsManager;
    TextView textViewDonerName;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Donor_Info_storage donor_info_storage;

    String donor_uid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_or_msg);

        smsManager=SmsManager.getDefault();
        Intent x1=getIntent();
        phone_no=x1.getStringExtra("contact_no");
        Toast.makeText(Call_or_msg.this,phone_no,Toast.LENGTH_SHORT).show();
        textView_contact_no=(TextView)findViewById(R.id.textView3_display_contact);
        edittext_sms_content=(EditText) findViewById(R.id.editText_sms_content);
        textView_contact_no.setText(phone_no);
        textViewDonerName = (TextView) findViewById(R.id.textViewDonerName);
        textViewDonerName.setText(x1.getStringExtra("Name"));




        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        Intent x=getIntent();

        donor_uid=x.getStringExtra("donor_uid");


    }
    public  void call(View v)
    {

        startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+phone_no)));

    }
    public  void sms(View v)
    {


        smsManager.sendTextMessage(phone_no,null,edittext_sms_content.getText().toString(),null,null);

    }
    public void hireTransport(View v)
    {


        startActivity(new Intent(this,Transport_hiring.class));

    }
    public  void book(View view)
    {

        databaseReference.child(donor_uid).child("book").setValue("1");
        databaseReference.child(donor_uid).child("received").setValue("received");

       // databaseReference.child(donor_uid).push().setValue("Received");

       // medium.status_uid.add(donor_uid);
        Toast.makeText(Call_or_msg.this,"Booked...."+donor_uid,Toast.LENGTH_SHORT).show();
       // Toast.makeText(Call_or_msg.this,medium.status_uid.size(),Toast.LENGTH_SHORT).show();
        //donor_info_storage=new Donor_Info_storage("1");


    }
    public void unbook(View view)
    {

        databaseReference.child(donor_uid).child("book").setValue("0");
        databaseReference.child(donor_uid).child("received").setValue("Not Yet");
        Toast.makeText(Call_or_msg.this,"UnBooked....",Toast.LENGTH_SHORT).show();

    }

}
