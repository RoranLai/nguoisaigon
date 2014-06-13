package com.nguoisaigon.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DBHelper {
	
	protected SQLiteDatabase sqlite;
	
	public DBHelper() {
		sqlite = null;
	    try {
	    	sqlite = SQLiteDatabase.openDatabase("assets/nguoisaigondb.rdb", null,
	                SQLiteDatabase.OPEN_READONLY);
	    	sqlite.close();
	    } catch (SQLiteException e) {
	        throw e;
	    }
	}
	

}
