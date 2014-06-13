package com.nguoisaigon.util;

import android.content.ContentValues;

public class SettingDB extends DBHelper{

	public static final String TABLE_NAME = "setting";
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
}
