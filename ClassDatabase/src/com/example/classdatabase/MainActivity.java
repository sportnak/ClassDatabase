package com.example.classdatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity
{
	SQLiteDatabase mydb;
	TextView name,email;
	Button save,myview;
	ListView lv;
	ArrayList data;
	private static final int REQUEST_BARCODE = 0;
    private static String contents = "";
	private String hold="";
    private String[] open = null;
    
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
 
    
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		name= (TextView)findViewById(R.id.entername);
		save= (Button)findViewById(R.id.bsave);
		
		//lv = (ListView)findViewById(R.id.list);
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		//prepareListData();
		 
        
 
        // setting list adapter
        
        
		/*lv.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?>arg0, View arg1, int arg2, long arg3){
				Toast.makeText(getApplicationContext(), data.get(arg2).toString(), Toast.LENGTH_LONG).show();
			}
		});*/
		
		mydb = MainActivity.this.openOrCreateDatabase("androidituts",MODE_PRIVATE,null);
		mydb.delete("test", null, null);
		mydb.execSQL("CREATE TABLE IF NOT EXISTS test (id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar,class varchar);");
		
		
		//insert
		save.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				//scanBarCode(v);
				contents = name.getText().toString();
				addToList(contents);
				listAdapter = new ExpandableListAdapter(MainActivity.this, listDataHeader, listDataChild);
				expListView.setAdapter(listAdapter);
			}
		});
		
		//myview is update
		myview = (Button)findViewById(R.id.bview);
		data=new ArrayList();
		myview.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				if(hold != contents){
					mydb.execSQL("insert into test (name) values(?);",new String[]{contents});
					newUser(contents);
					hold = contents;
					sendMessage(v);
				
				}
				Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
				Cursor cursor2 = mydb.rawQuery("SELECT * FROM test;", null);
				
				if(cursor2.moveToFirst()){
					data.clear();
					do{
						data.add(cursor2.getString(cursor2.getColumnIndex("name")));
					}
					while(cursor2.moveToNext());
					
					ArrayAdapter <String> adapter = new ArrayAdapter <String>
					(MainActivity.this,android.R.layout.simple_list_item_1,data);
					lv.setAdapter(adapter);
				}
				else{
					Toast.makeText(getApplicationContext(), "DATA NOT AVAILABLE", Toast.LENGTH_LONG).show();
				}
				cursor2.close();
			}
		});
	}
	
	/*public void scanBarCode(View view) {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "PRODUCT_MODE");  

        startActivityForResult(intent, REQUEST_BARCODE);

        Toast toast = Toast.makeText(this, "Start scanning Barcode", Toast.LENGTH_SHORT);
        toast.show();
    }
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            contents = intent.getStringExtra("SCAN_RESULT");
	        } else if (resultCode == RESULT_CANCELED) {
	        	Toast toast = Toast.makeText(this, "Failed Scan" , Toast.LENGTH_SHORT);
	            toast.show();
	        }
	    }
	}*/
	public void newUser(String contents){
		if(true){
			//create intent and ask for information- start new activity
			//open orcreate table and insert info submitted!
		}
	}
	
	public void sendMessage(View v){
		Intent newIntent = new Intent(this, NewUser.class);
		newIntent.putExtra("ID", contents);
		startActivity(newIntent);
	}
	
	public void onDestroy(){
		super.onDestroy();
	}
	
	public void addToList(String contents){
		listDataHeader = new ArrayList<String>();
		listDataHeader.add(contents);
		
		listDataChild = new HashMap<String, List<String>>();
		List<String> names = new ArrayList<String>();
		names.add("Name: " + "Michael");
		names.add("Class: " + "Geometry");
		names.add("Passes: " + "3");
		
		listDataChild.put(listDataHeader.get(0), names);
		Log.i("Hello", "Stuck Here");
	}
	
	 private void prepareListData() {
	        listDataHeader = new ArrayList<String>();
	        listDataChild = new HashMap<String, List<String>>();
	 
	        // Adding child data
	        listDataHeader.add("Top 250");
	        listDataHeader.add("Now Showing");
	        listDataHeader.add("Coming Soon..");
	 
	        // Adding child data
	        List<String> top250 = new ArrayList<String>();
	        top250.add("The Shawshank Redemption");
	        top250.add("The Godfather");
	        top250.add("The Godfather: Part II");
	        top250.add("Pulp Fiction");
	        top250.add("The Good, the Bad and the Ugly");
	        top250.add("The Dark Knight");
	        top250.add("12 Angry Men");
	 
	        List<String> nowShowing = new ArrayList<String>();
	        nowShowing.add("The Conjuring");
	        nowShowing.add("Despicable Me 2");
	        nowShowing.add("Turbo");
	        nowShowing.add("Grown Ups 2");
	        nowShowing.add("Red 2");
	        nowShowing.add("The Wolverine");
	 
	        List<String> comingSoon = new ArrayList<String>();
	        comingSoon.add("2 Guns");
	        comingSoon.add("The Smurfs 2");
	        comingSoon.add("The Spectacular Now");
	        comingSoon.add("The Canyons");
	        comingSoon.add("Europa Report");
	 
	        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
	        listDataChild.put(listDataHeader.get(1), nowShowing);
	        listDataChild.put(listDataHeader.get(2), comingSoon);
	    }
}

