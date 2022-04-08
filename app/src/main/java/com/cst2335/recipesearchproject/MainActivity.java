package com.cst2335.recipesearchproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";
//ProgressBar progressBar;
//int counter = 0;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;


    ListView LV;
    ArrayAdapter adapter;
    ArrayList<String>recipes;


    SearchView sv;
    TextView tv;
    TextView tv2;

    MyOpenHelper myOpenHelper;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase RecipeDataBase = myOpenHelper.getWritableDatabase();
        //prog();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_Open, R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);




        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                                                             @Override
                                                             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                                 switch(item.getItemId()){
                                                                     case R.id.home:
                                                                            Log.i("MENU_DRAWER_TAG", "Home item is clicked");
                                                                            drawerLayout.closeDrawer(GravityCompat.START);
                                                                            break;
                                                                     case R.id.favourites:
                                                                         Log.i("MENU_DRAWER_TAG", "Favourite item is clicked");
                                                                         drawerLayout.closeDrawer(GravityCompat.START);
                                                                         break;
                                                                     case R.id.help:
                                                                         Log.i("MENU_DRAWER_TAG", "Help item is clicked");
                                                                         drawerLayout.closeDrawer(GravityCompat.START);
                                                                         builder.setTitle("Looking for Help?")
                                                                                 .setMessage("Click the search button to find a recipe!")
                                                                                 .setCancelable(true)
                                                                                 .setPositiveButton("Okay!", new DialogInterface.OnClickListener() {
                                                                                     @Override
                                                                                     public void onClick(DialogInterface dialogInterface, int i) {
                                                                                         dialogInterface.cancel();
                                                                                     }
                                                                                 })
                                                                                 .show();

                                                                         break;
                                                                 }return true;
                                                             }
                                                         });



                LV = (ListView) findViewById(R.id.ListView);
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
                tv.setText("APPLE PIE:");
                tv2.setText("2 1/2 cups of flour" +

                        "\n4 teaspoons  sugar" +
                        "\n14 tablespoons of butter" +
                        "\n1 large egg" +
                        "\n3 pounds of apples" +
                        "\n1/4 teaspoon of cinnamon" +
                        "\npinch of ground nutmeg");
                }
             if( s.equals("Custard")){
                 tv.setText("CUSTARD:");
                 tv2.setText("4 cups of milk" +
                         "\n1 tablespoon vanilla extract" +
                         "\n1 teaspoon of butter" +
                         "\n4 eggs" +
                         "\n1/2 cup of sugar" +
                         "\n3 tablespoons of cornstarch");
             }

             if(s.equals("Cookies")){
                 tv.setText("COOKIES:");
                 tv2.setText("2 1/4 cups of flour" +
                         "\n1 teaspoon baking soda" +
                         "\n1/2 teaspoon salt" +
                         "\n1 cup butter" +
                         "\n3/4 cups brown sugar" +
                         "\n1 egg" +
                         "\n2 cups of chocolate chips");
             }

             if(s.equals("Brownies")){
                 tv.setText("BROWNIES:");
                 tv2.setText("1/2 cup butter" +
                         "\n1 cup white sugar" +
                         "\n2 eggs" +
                         "\n1 teaspoon vanilla extract" +
                         "\n1/2 cup of flour" +
                         "\n1/4 teaspoon of salt" +
                         "\n1/3 cup cocoa powder" +
                         "\n1/4 teaspoon baking powder");
             }
                return false;
            }
        });




    }

   /** public void prog(){
        progressBar = (android.widget.ProgressBar)findViewById(R.id.progressBar);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                progressBar.setProgress(counter);

                if(counter == 100){
                    t.cancel();

                    Toast.makeText(MainActivity.this, "Loaded!", Toast.LENGTH_SHORT).show();
                }
            }
        };
    t.schedule(tt, 0, 100);

    }
**/

}