package com.csg.warrior.android.persistence;

import java.util.ArrayList;
import java.util.List;

import com.csg.warrior.android.domain.MobileKey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "warrior";
    private static final String TABLE_TRIPLES = "triples";
    private static final String COLUMN_TRIPLES_ID = "tripleid";
    private static final String COLUMN_TRIPLES_USERNAME = "username";
    private static final String COLUMN_TRIPLES_URL = "url";
    private static final String COLUMN_TRIPLES_KEY= "key";


    
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
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPLES);
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
                mk.setDatabaseId(cursor.getLong(0));
        		mk.setKeyOwner(cursor.getString(1));
        		mk.setUrlForUpload(cursor.getString(2));        		
        		mk.setKey(cursor.getString(3));
        		mobileKeyList.add(mk);
        	} while(cursor.moveToNext());
        }
        
        cursor.close();
        return mobileKeyList;
    }
    //Checker if nageexist na yung username
    public boolean doesUsernameExist(String username){
    	
    	String selectQuery = "SELECT * FROM " +TABLE_TRIPLES + " WHERE username=?"; 
    	boolean doesExist = true;
    	SQLiteDatabase db = this.getWritableDatabase();
    	String[] parameters = new String [] {username};
        Cursor cursor = db.rawQuery(selectQuery, parameters);
        if(cursor.getCount() == 0) { doesExist = false; }
        cursor.close();
    	return doesExist;
    }
    
    public void updateMobileKey(MobileKey mobileKey) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateQuery = "UPDATE triples SET username=?, key=?, url=? WHERE tripleid="+mobileKey.getDatabaseId();
        String[] queryParameters = new String[] {mobileKey.getKeyOwner(), mobileKey.getKey(), mobileKey.getUrlForUpload()};
        Cursor cursor = db.rawQuery(updateQuery, queryParameters);
        cursor.moveToFirst();
        cursor.close();
        // TODO how to get the ID of the settings
    }

    public void addMobileKey(MobileKey currentMobileKeySettings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valueToInsert = toContentValues(currentMobileKeySettings);
        db.insert(TABLE_TRIPLES, null, valueToInsert);
        db.close();
    }

    private ContentValues toContentValues(MobileKey mobileKey) {
        ContentValues values = new ContentValues();
        if (mobileKey != null) {
            values.put(COLUMN_TRIPLES_USERNAME, mobileKey.getKeyOwner());
            values.put(COLUMN_TRIPLES_URL, mobileKey.getUrlForUpload());
            values.put(COLUMN_TRIPLES_KEY, mobileKey.getKey());

        }
        return values;
    }
    
    public void addData(String username, String url, String key){
    	SQLiteDatabase db = this.getWritableDatabase();
    	ContentValues valueToInsert = toPut(username, url, key);
    	db.insert(TABLE_TRIPLES, null, valueToInsert);
        db.close();
    }
    
    public ContentValues toPut(String username, String url, String key){
    	ContentValues values = new ContentValues();
    	
    	values.put(COLUMN_TRIPLES_USERNAME, username);
        values.put(COLUMN_TRIPLES_URL, url);
        values.put(COLUMN_TRIPLES_KEY, key);
    	
    	return values;
    }
    
    
}
