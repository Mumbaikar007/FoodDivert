package com.example.abhishek.fooddivert;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NGO_contacts extends AppCompatActivity {

    ListView listView_contacts;
    ArrayList arrayList_NGO_CONTACTS;
    ArrayAdapter adapter_NGO_CONTACTS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngo_contacts);
        listView_contacts=(ListView)findViewById(R.id.listview_NGO_CONTACTS);
        arrayList_NGO_CONTACTS=new ArrayList();

        arrayList_NGO_CONTACTS.add(" NGO XYZ contact:");
        arrayList_NGO_CONTACTS.add("8976300750");

        arrayList_NGO_CONTACTS.add("ORPHANAGE ABC contact:");
        arrayList_NGO_CONTACTS.add("8425907521");

        adapter_NGO_CONTACTS=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList_NGO_CONTACTS);
        listView_contacts.setAdapter(adapter_NGO_CONTACTS);


        listView_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i%2!=0)
                {

                    String NGO_contact=adapterView.getItemAtPosition(i).toString();

                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:"+NGO_contact)));

                }
                else
                {

                    Toast.makeText(NGO_contacts.this,adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();

                }


            }
        });


    }





}
