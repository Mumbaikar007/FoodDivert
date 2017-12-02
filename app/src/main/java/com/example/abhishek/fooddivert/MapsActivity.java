package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Location location;
    String latitude_donor;
    String longitude_donor;
    String provider_name;
    String donor_address;

    int i=0;
    LocationManager locationManager;


    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;

    Query query_donor_location;

    ArrayList<String> arrayList_donor_location_latitude;
    ArrayList<String> arrayList_donor_location_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);

        arrayList_donor_location_latitude=new ArrayList();
        arrayList_donor_location_longitude=new ArrayList();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        firebaseUser=firebaseAuth.getCurrentUser();



        Intent x = getIntent();
        Toast.makeText(this, x.getStringExtra("longitude") + " ok " + x.getStringExtra("latitude"), Toast.LENGTH_SHORT).show();
        latitude_donor = x.getStringExtra("latitude");
        longitude_donor = x.getStringExtra("longitude");
        donor_address=x.getStringExtra("address");

        query_donor_location=databaseReference.orderByChild("address").equalTo(donor_address);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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
        mMap.setMyLocationEnabled(true);



        //location=mMap.getMyLocation();
       // Toast.makeText(MapsActivity.this,location.getLatitude()+" inside "+location.getLongitude(),Toast.LENGTH_SHORT).show();

        query_donor_location.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren())
                {

                    Donor_Info_storage donor_info_storage = ds.getValue(Donor_Info_storage.class);

                    arrayList_donor_location_latitude.add(donor_info_storage.latitude);
                    arrayList_donor_location_longitude.add(donor_info_storage.longitude);

                    Toast.makeText(MapsActivity.this,donor_info_storage.name+" yes "+donor_info_storage.name,Toast.LENGTH_SHORT).show();
                   // Toast.makeText(MapsActivity.this,arrayList_donor_location_latitude.get(i)+" yesss "+arrayList_donor_location_longitude.get(i),Toast.LENGTH_SHORT).show();
                    i++;
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        LatLng donor_location = new LatLng(Double.parseDouble(latitude_donor), Double.parseDouble(longitude_donor));
        mMap.addMarker(new MarkerOptions().position(donor_location).title("Donor Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(donor_location));


    }

    public void get_near_donors(View view)
    {

        for(int j=0;j<arrayList_donor_location_longitude.size();j++)
        {
            Toast.makeText(MapsActivity.this,arrayList_donor_location_latitude.get(j)+" j "+j+" "+arrayList_donor_location_longitude.get(j),Toast.LENGTH_SHORT).show();

            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(arrayList_donor_location_latitude.get(j)),Double.parseDouble(arrayList_donor_location_longitude.get(j)))).title("donor "+j));

        }

    }


    public  void  changetype(View view)
    {

        if(mMap.getMapType()==GoogleMap.MAP_TYPE_NORMAL)
        {


            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        }
        else
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

    }
}
