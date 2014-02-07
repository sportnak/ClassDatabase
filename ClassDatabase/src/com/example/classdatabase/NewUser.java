package com.example.classdatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NewUser extends Activity {
	TextView idNumber;
	EditText name;
	EditText className;
	SQLiteDatabase mydb;
	String message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		
		idNumber= (TextView)findViewById(R.id.inputID);
		name = (EditText)findViewById(R.id.name);
		className =(EditText)findViewById(R.id.className);
		
		Intent intent = getIntent();
		message = intent.getStringExtra("ID");
		idNumber.setText(message);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_user, menu);
		return true;
	}
	
	public void submit(View v){
		mydb = NewUser.this.openOrCreateDatabase("androidituts",MODE_PRIVATE,null);
		String people = className.getText().toString();
		//mydb.execSQL("INSERT INTO test (email) VALUES(?);",new String[]{email});
		
		ContentValues values = new ContentValues();
		values.put("class", people);
		values.put("name", message);
		
		mydb.insert("test", null, values);
	}

}
