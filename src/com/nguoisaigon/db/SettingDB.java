package com.nguoisaigon.db;

import com.nguoisaigon.entity.SettingInfo;

import android.content.ContentValues;
import android.content.Context;

public class SettingDB extends DBHelper {

	public SettingDB(Context vContext) {
		super(vContext);
	}

	public static final String TABLE_NAME = "setting";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_APP_LINK = "applink";
	public static final String COLUMN_PARSE_APP_ID = "parseappid";
	public static final String COLUMN_SETTING_ID = "settingid";

	public Long insert(SettingInfo info) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_APP_LINK, info.getAppLink());
		values.put(COLUMN_PARSE_APP_ID, info.getParseAppId());
		values.put(COLUMN_SETTING_ID, info.getSettingId());
		return sqlite.insert(TABLE_NAME, null, values);
	}
	
	public Integer update(SettingInfo info) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_APP_LINK, info.getAppLink());
		values.put(COLUMN_PARSE_APP_ID, info.getParseAppId());
		values.put(COLUMN_SETTING_ID, info.getSettingId());
		
		String selection = COLUMN_ID + " = ?";
		String[] selectionArgs = { String.valueOf(1) };
		
		return sqlite.update(TABLE_NAME, values, selection, selectionArgs);
	}
	
	public Integer delete(Integer id) {
		String selection = COLUMN_ID + " = ?";
		String[] selectionArgs = { String.valueOf(1) };
		return sqlite.delete(TABLE_NAME, selection, selectionArgs);
	}
	
	
}
