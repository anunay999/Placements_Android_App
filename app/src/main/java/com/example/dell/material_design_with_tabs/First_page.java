package com.example.dell.material_design_with_tabs;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Dell on 4/16/2017.
 */

public class First_page extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        lv = (ListView)findViewById(R.id.listviewfirstpage);
        ArrayList<String> list_view_items = new ArrayList<>();
        list_view_items.add("Resume");
        list_view_items.add("Placements");

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list_view_items);

        lv.setAdapter(adapter);

       lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(parent.getItemAtPosition(position).toString().equals("Placements"))
               {
                   Intent i = new Intent(First_page.this,Companies_list.class);
                   startActivity(i);
               }
           }
       });
    }

}
