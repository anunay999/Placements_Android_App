package com.example.dell.material_design_with_tabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Dell on 4/16/2017.
 */

public class Companies_list extends AppCompatActivity {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;
    ArrayList<String> Comapnies_names;
    ListView lv;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor sharedpreferenceEditor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.companies_list);
        lv =(ListView)findViewById(R.id.listview_company_list);
        Comapnies_names = new ArrayList<>();
        //setting up shared prefs
        sharedpreferences = this.getSharedPreferences("Company_details",MODE_PRIVATE);
        sharedpreferenceEditor = sharedpreferences.edit();
        //connecting to firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReferenceFromUrl("https://materialdesignwithtabs.firebaseio.com/");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Comapnies_names);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selecteditem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),selecteditem,Toast.LENGTH_SHORT).show();
                sharedpreferenceEditor.putString("SelectedCompany",selecteditem);
                sharedpreferenceEditor.commit();
                Intent i = new Intent(Companies_list.this,MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            for(DataSnapshot es: ds.getChildren())
            {
                Comapnies_names.add(es.getKey());
            }
        }
    }
}
