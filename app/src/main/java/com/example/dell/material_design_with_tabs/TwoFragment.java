package com.example.dell.material_design_with_tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Dell on 4/14/2017.
 */

public class TwoFragment extends Fragment {
    TextView tv;
    SharedPreferences sharedPreferences;
    public TwoFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two,container,false);
        tv = (TextView)v.findViewById(R.id.textView2);
        sharedPreferences = v.getContext().getSharedPreferences("Company_details", Context.MODE_PRIVATE);
        String SelectedCompany = sharedPreferences.getString("SelectedCompany","None");
        tv.setText("The selected company is :" + SelectedCompany);
        return v;
    }

}
