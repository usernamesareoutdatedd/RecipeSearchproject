package com.cst2335.recipesearchproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper {
    public static final String filename = "RecipeDataBase";
    public static final int version = 1;
    public static final String TABLE_NAME = "MyRecipe";
    public static final String COL_ID = "_id";
    public static final String COL_RECIPE = "NameOfRecipe";
    private static final String TAG = "MyOpenHelper";


    public MyOpenHelper( Context context) {
        super(context, filename, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "Create table " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_RECIPE + " TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists " + TABLE_NAME);
        this.onCreate(db);

    }

    public void addRecipe(Recipe recipe){
        SQLiteDatabase RecipeDataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NameOfRecipe",recipe.getNameOfRecipe());
        long MyRecipe = RecipeDataBase.insert("MyRecipe",null,contentValues);
        Log.e(TAG, "insertRecipe: "+ MyRecipe);

//        RecipeDataBase.insert("MyRecipe",null,contentValues);
//
//        contentValues.put(MyOpenHelper.COL_RECIPE,recipe.getNameOfRecipe());




    }

//    public  Boolean addRecipe(String TABLE_NAME, String COL_ID,String COL_RECIPE){
//
//        SQLiteDatabase RecipeDataBase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("table name",TABLE_NAME);
//        contentValues.put("col id",COL_ID);
//        contentValues.put("col Recipe",COL_RECIPE);
//        Cursor cursor = RecipeDataBase.rawQuery("Select * from TABLE_NAME where MyRecipe = ?",new String[] {TABLE_NAME});
//        if(cursor.getCount() > 0){
//
//
//        long result = RecipeDataBase.insert("MyRecipe",null,contentValues);
//        if(result == -1){
//            return  false;
//        }else{
//            return  true;
//        }
//        }else{
//            return false;
//        }
//
//
//
//    }
}