package com.csg.warrior.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.csg.warrior.domain.MobileKey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "warrior";
    
    // Table names
    private static final String TABLE_TRIPLES = "triples";
    
    //Column names
    private static final String COLUMN_TRIPLES_ID = "tripleid";
    private static final String COLUMN_TRIPLES_USERNAME = "username";
    private static final String COLUMN_TRIPLES_URL = "url";
    private static final String COLUMN_TRIPLES_KEY = "key";
    

 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	String CREATE_TRIPLE_TABLE = "CREATE TABLE " + TABLE_TRIPLES + "("
    			+ COLUMN_TRIPLES_ID + " INTEGER PRIMARY KEY,"
    			+ COLUMN_TRIPLES_USERNAME + " TEXT,"
    			+ COLUMN_TRIPLES_URL + " TEXT,"
    			+ COLUMN_TRIPLES_KEY + " TEXT"
    			+ ")";
        db.execSQL(CREATE_TRIPLE_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPLES);
 
        // Create tables again
        onCreate(db);
    }
    
    //Create read update delete OPERATIONS
    public List<MobileKey> getMobileKeys() {
    	List<MobileKey> mobileKeyList = new ArrayList<MobileKey>();
    	
    	String selectQuery = "SELECT * FROM " + TABLE_TRIPLES;
    	
    	SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
    	 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
        	do {
        		MobileKey mk = new MobileKey();
        		
        		//System.out.println("Triple username: " + cursor.getString(1));
        		//System.out.println("Triple url: " + cursor.getString(2));
        		//System.out.println("Triple key: " + cursor.getString(3));
        		
        		mk.setKeyOwner(cursor.getString(1));
        		mk.setUrlForUpload(cursor.getString(2));        		
        		mk.setAssociatedFile(new File(cursor.getString(3)));        		
        		
        		mobileKeyList.add(mk);
        	} while(cursor.moveToNext());
        }
        
        return mobileKeyList;
    }
    
    public void addTriple(Triple p_triple) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(COLUMN_TRIPLES_USERNAME, p_triple.getUsername());
    	values.put(COLUMN_TRIPLES_URL, p_triple.getURL());
    	values.put(COLUMN_TRIPLES_KEY, p_triple.getKey());
    	
    	db.insert(TABLE_TRIPLES, null, values);
    	db.close();
    }
    
}
