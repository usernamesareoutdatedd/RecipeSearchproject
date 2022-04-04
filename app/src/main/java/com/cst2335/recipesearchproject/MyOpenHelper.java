package com.cst2335.recipesearchproject;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyOpenHelper extends SQLiteOpenHelper {
    public static final String filename = "RecipeDataBase";
    public static final int version = 1;
    public static final String TABLE_NAME = "MyRecipe";
    public static final String COL_ID = "_id";
    public static final String COL_RECIPE = "NameOfRecipe";





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
}
