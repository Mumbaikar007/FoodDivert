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

import java.util.ArrayList;

public class Transport_hiring extends AppCompatActivity {

    TextView transport_hiring;
    ListView listView_transport;
    ArrayList<String>arrayList_transport_contacts;
    ArrayAdapter<String> adapter_tranport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_hiring);
        transport_hiring=(TextView)findViewById(R.id.textView_transport);
        listView_transport=(ListView)findViewById(R.id.listview_transport);
        arrayList_transport_contacts=new ArrayList<>();

        arrayList_transport_contacts.add("8976300750");

        adapter_tranport=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList_transport_contacts);
        listView_transport.setAdapter(adapter_tranport);

        listView_transport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String transport_contact_no=adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(Transport_hiring.this,transport_contact_no,Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+transport_contact_no)));

            }
        });


    }






}
