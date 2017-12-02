package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class Donor_user_info extends AppCompatActivity {

    LocationManager locationManager;
    String provider;
    Geocoder geocoder;
    double latitude;
    double longitude;
    List<Address> list;
    TextView longitude1;
    TextView latitude1;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    Donor_Info_storage donor_info_storage;

    EditText name1;
    EditText phone1;
    EditText address1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_user_info);
        geocoder=new Geocoder(this);

        longitude1=(TextView)findViewById(R.id.textView);
        latitude1=(TextView)findViewById(R.id.textView4);

        name1=(EditText)findViewById(R.id.editText3_donor_user);
        phone1=(EditText)findViewById(R.id.editText4);
        address1=(EditText)findViewById(R.id.editText5);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);



        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            provider = LocationManager.NETWORK_PROVIDER;
        else
            provider = LocationManager.GPS_PROVIDER;
        // locationManager.requestLocationUpdates(provider, 100000, 1, this);
        Toast.makeText(this,provider,Toast.LENGTH_SHORT).show();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference();





    }

    public void getdonorlocation(View v) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 1000000, 1, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


                latitude=location.getLatitude();
                longitude=location.getLongitude();

                Toast.makeText(Donor_user_info.this,""+longitude,Toast.LENGTH_SHORT).show();
                Toast.makeText(Donor_user_info.this,""+latitude,Toast.LENGTH_SHORT).show();

                latitude1.setText("Latitude:"+latitude);
                longitude1.setText("Longitude:"+longitude);



                /*try {
                    l1=  geocoder.getFromLocation(latitude,longitude,1);
                } catch (IOException e) {
                    // e.printStackTrace();
                }

                if(l1!=null) {
                    t2.setText(l1.get(0).toString());
                }
                else
                {
                    Toast.makeText(Donor_user_info.this,"null",Toast.LENGTH_LONG).show();
                }*/


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });

    }

    public void onSaveandproceed(View v)
    {

        //Toast.makeText(this,"under construction",Toast.LENGTH_SHORT).show();
        String name=name1.getText().toString();
        String phone=phone1.getText().toString();
        String address=address1.getText().toString();

        //donor_info_storage=new Donor_Info_storage(name,phone,address);

        //String uid=firebaseUser.getUid();

        //databaseReference.child(uid).setValue(donor_info_storage);

        Intent in=new Intent(this,Doners_Listview.class);
        //in.putExtra("donoruid",uid);
        in.putExtra("latitude",latitude+"");
        in.putExtra("longitude",longitude+"");
        Toast.makeText(this,latitude+"send "+longitude,Toast.LENGTH_SHORT).show();
        in.putExtra("name",name);
        in.putExtra("phone",phone);
        in.putExtra("address",address);
        startActivity(in);


    }





}



