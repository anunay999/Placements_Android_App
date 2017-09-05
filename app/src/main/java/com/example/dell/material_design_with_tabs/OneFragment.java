package com.example.dell.material_design_with_tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Dell on 4/14/2017.
 */

public class OneFragment extends Fragment {
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference myRef;
    View v;
    TextView tv;
    //ListView lv;
    SharedPreferences sharedPreferences ;
    String SelectedCompany ;
    //Bar graph related variables
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    BarDataSet barDataSet;
    ArrayList<String> theYears = new ArrayList<>();
    BarChart barChart;

    public OneFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_one,container,false);
        tv = (TextView) v.findViewById(R.id.textView1);
       // lv = (ListView)v.findViewById(R.id.listview1);
        sharedPreferences = v.getContext().getSharedPreferences("Company_details", Context.MODE_PRIVATE);
        SelectedCompany = sharedPreferences.getString("SelectedCompany","None");
        tv.setText("The data related to company is:"+SelectedCompany);
        barChart = (BarChart)v.findViewById(R.id.bargraph);
        //connecting to Firebase
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
        return v;
    }

    private void showData(DataSnapshot dataSnapshot) {

       // ArrayList<String> al = new ArrayList<>();

        for(DataSnapshot ds : dataSnapshot.getChildren())
        {

            for(DataSnapshot es : ds.getChildren())
                {
                    int i=0;
                    if(es.getKey().equals(SelectedCompany))
                    for(DataSnapshot fs : es.getChildren())
                    {
                       // al.add(fs.getKey()+""+fs.getValue().toString());
                        barEntries.add(new BarEntry(Float.parseFloat(fs.getValue().toString()),i++));
                        theYears.add(fs.getKey());
                    }
                }
        }
        barDataSet = new BarDataSet(barEntries,"Number of Selected");

        BarData theData = new BarData(theYears,barDataSet);

        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

       // ArrayAdapter adapter = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,al);
        //lv.setAdapter(adapter);

    }
}
