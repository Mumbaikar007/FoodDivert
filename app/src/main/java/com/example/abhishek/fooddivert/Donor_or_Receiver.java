package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Donor_or_Receiver extends AppCompatActivity implements View.OnClickListener {

    private Button button_Donation_Status;
    FirebaseAuth firebaseAuth;
    TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_or__receiver);
        welcome=(TextView)findViewById(R.id.textView_donor_or_receiver);
        firebaseAuth=FirebaseAuth.getInstance();
        welcome.setText(firebaseAuth.getCurrentUser().getEmail());
        button_Donation_Status = (Button) findViewById(R.id.button_Donation_Status);
        button_Donation_Status.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_logout,menu);
        return  true;
    }


    public void donor(View v)
    {

        startActivity(new Intent(this,Donor_user_info.class));
    }
    public void receiver(View v)
    {
        //Toast.makeText(this,"under construction",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,Receiver_retrive_information.class));

    }
    public  void onLogout(MenuItem menuItem)
    {

        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,AlredyRegister.class));
    }
    public  void onAbout(MenuItem menuItem)
    {

        finish();
        startActivity(new Intent(this,About.class));


    }

    @Override
    public void onClick(View view) {
        if (view == button_Donation_Status){
            startActivity(new Intent(this,Doners_Current_Status.class ));
        }
    }
}
