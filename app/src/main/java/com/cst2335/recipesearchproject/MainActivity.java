package com.cst2335.recipesearchproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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
MyOpenHelper myOpenHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase RecipeDataBase = myOpenHelper.getWritableDatabase();

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


                ContentValues newRow = new ContentValues();

                adapter.getFilter().filter(s);
             if( s.equals ("Apple Pie")){
                tv.setText("APPLE PIE:");
                tv2.setText("2 1/2 cups of flour" +
                        "4 teaspoons  sugar" +
                        "14 tablespoons of butter" +
                        "1 large egg" +
                        "3 pounds of apples" +
                        "1/4 teaspoon of cinnamon" +
                        "pinch of ground nutmeg");
                }
             if( s.equals("Custard")){
                 tv.setText("CUSTARD:");
                 tv2.setText("4 cups of milk" +
                         "1 tablespoon vanilla extract" +
                         "1 teaspoon of butter" +
                         "4 eggs" +
                         "1/2 cup of sugar" +
                         "3 tablespoons of cornstarch");
             }

             if(s.equals("Cookies")){
                 tv.setText("COOKIES:");
                 tv2.setText("2 1/4 cups of flour" +
                         "1 teaspoon baking soda" +
                         "1/2 teaspoon salt" +
                         "1 cup butter" +
                         "3/4 cups brown sugar" +
                         "1 egg" +
                         "2 cups of chocolate chips");
             }

             if(s.equals("Brownies")){
                 tv.setText("BROWNIES:");
                 tv2.setText("1/2 cup butter" +
                         "1 cup white sugar" +
                         "2 eggs" +
                         "1 teaspoon vanilla extract" +
                         "1/2 cup of flour" +
                         "1/4 teaspoon of salt" +
                         "1/3 cup cocoa powder" +
                         "1/4 teaspoon baking powder");
             }
                return false;
            }








        });




    }




}