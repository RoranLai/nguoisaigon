package com.nguoisaigon.db;

import android.content.ContentValues;
import android.content.Context;

import com.nguoisaigon.entity.UserInfo;

public class UserDB extends DBHelper {

	public UserDB(Context vContext) {
		super(vContext);
	}

	public static final String TABLE_NAME = "user";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_USER_ID = "userid";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_CONTACT_PHONE = "contactphone";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_ERNED_POINT = "ernedpoint";
	
	public Long insert(UserInfo info) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_USER_ID, info.getUserId());
		values.put(COLUMN_NAME, info.getName());
		values.put(COLUMN_EMAIL, info.getEmail());
		values.put(COLUMN_CONTACT_PHONE, info.getContactPhone());
		values.put(COLUMN_ADDRESS, info.getAddress());
		values.put(COLUMN_ERNED_POINT, info.getErnedPoint());
		return sqlite.insert(TABLE_NAME, null, values);
	}
}
