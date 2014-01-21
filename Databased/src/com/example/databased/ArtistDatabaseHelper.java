package com.example.databased;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArtistDatabaseHelper extends SQLiteOpenHelper { 
	
	 final private static String CREATE_CMD = 
	 "CREATE TABLE artists (" 
	 + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
	 + "name" + " TEXT NOT NULL)"; 
	 
	 
	 public ArtistDatabaseHelper(Context context) { 
		 super(context, "artists_db", null, 1); 
	 } 
	 
	 public void onCreate(SQLiteDatabase db) { 
		 db.execSQL(CREATE_CMD); 
	 } 
	 
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		 /* ... */ 
		 
	 }
}
