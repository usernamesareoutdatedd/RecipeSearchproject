package com.cst2335.recipesearchproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.content.SharedPreferences;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";

    ListView LV;
ArrayAdapter adapter;
    ArrayList<String>recipes;
SearchView sv;
TextView tv;
TextView tv2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LV= (ListView)findViewById(R.id.ListView);
        sv = (SearchView)findViewById(R.id.search);
        tv = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView3);

        recipes = new ArrayList<String>();
        recipes.add("Apple pie");
        recipes.add("Custard");
        recipes.add("Brownies");
        recipes.add("Cookies");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,recipes);
        LV.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
             if( s.equals ("Apple Pie")){
                tv.setText("Testing!!");
                }
             if( s.equals("Custard")){
                 tv.setText("Yet another test");
             }
                return false;
            }
        });




    }




}