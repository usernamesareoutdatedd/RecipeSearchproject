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
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ProgressBar;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";

/**
 * Everything is defined here before onCreate method to function.
 * */
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    private int CurrentProgress = 0;
    private ProgressBar progressBar;



    ListView LV;
    ArrayAdapter adapter;
    ArrayList<String>recipes;

    /**
     * All the textviews to display the recepies.
     * */
    SearchView sv;
    TextView tv;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;
    TextView tv9;

    /**
     * Database is opened
     * */

    MyOpenHelper myOpenHelper;

    Button b2;

    /**
     * Menu if an option is selected
     * */
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

        /**
         * Progress bar is defined
         * */
       progressBar = findViewById(R.id.progressbar);

        /**
         * Database is opened
         * */
        myOpenHelper = new MyOpenHelper(this);
        SQLiteDatabase RecipeDataBase = myOpenHelper.getWritableDatabase();

        /**
         * Toolbar/Navigation drawer opened
         * */

        b2 = findViewById(R.id.recpButton);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_Open, R.string.close_menu);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);

        progressBar = findViewById(R.id.progressbar);

        /**
         * Logic for ProgressBar
         * */
        final CountDownTimer countDownTimer = new CountDownTimer(4*100, 1000) {
            @Override
            public void onTick(long millisInFuture) {
                CurrentProgress = CurrentProgress + 50;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);
            }

            /**
             * Toast message when progress bar is finished
             * */
            @Override
            public void onFinish() {

                Toast.makeText(MainActivity.this, "Refreshed!",
                        Toast.LENGTH_LONG).show();
            }
        };


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            countDownTimer.start();

            }
        });


        /**
         * If something is clicked in the navigation drawer
         * */
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
        tv3 = (TextView)findViewById(R.id.textView4);
        tv4 = (TextView)findViewById(R.id.textView5);
        tv5 = (TextView)findViewById(R.id.textView6);
        tv6 = (TextView)findViewById(R.id.textView7);
        tv7 = (TextView)findViewById(R.id.textView8);
        tv8 = (TextView)findViewById(R.id.textView9);
        tv9 = (TextView)findViewById(R.id.textView10);

        /**
         * Array to store recepies
         * */
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


/**
 * If s equals one of the array items, the recepie is listed for each item in the searchView
 * */
             if( s.equals ("Apple Pie")){
                tv.setText("APPLE PIE:");
                tv2.setText("2 1/2 cups of flour");
                 tv3.setText("4 teaspoons  sugar");
                 tv4.setText("14 tablespoons of butter");
                 tv5.setText( "1 large egg");
                 tv6.setText( "3 pounds of apples");
                 tv6.setText( "1/4 teaspoon of cinnamon");
                 tv7.setText("pinch of ground nutmeg");

                }
             if( s.equals("Custard")){
                 tv.setText("CUSTARD:");
                 tv2.setText("4 cups of milk");
                 tv3.setText("1 tablespoon vanilla extract");
                 tv4.setText("1 teaspoon of butter");
                 tv5.setText("4 eggs");
                 tv6.setText("1/2 cup of sugar");
                 tv7.setText("3 tablespoons of cornstarch");
             }

             if(s.equals("Cookies")){
                 tv.setText("COOKIES:");
                 tv2.setText("2 1/4 cups of flour");
                 tv3.setText("1 teaspoon baking soda");
                 tv4.setText("1/2 teaspoon salt");
                 tv5.setText("1 cup butter");
                 tv6.setText("3/4 cups brown sugar");
                 tv7.setText("1 egg");
                 tv8.setText("2 cups of chocolate chips");

             }

             if(s.equals("Brownies")){
                 tv.setText("BROWNIES:");
                 tv2.setText("1/2 cup butter");
                 tv3.setText("1 cup white sugar");
                 tv4.setText("2 eggs");
                 tv5.setText("1 teaspoon vanilla extract");
                 tv6.setText( "1/2 cup of flour");
                 tv7.setText("1/4 teaspoon of salt");
                 tv8.setText("1/3 cup cocoa powder");
                 tv9.setText("1/4 teaspoon baking powder");

             }
                return false;
            }
        });




    }



}