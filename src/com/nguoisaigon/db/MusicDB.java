package com.nguoisaigon.db;

import com.nguoisaigon.entity.MusicInfo;
import android.content.ContentValues;

public class MusicDB extends DBHelper {

	public static final String TABLE_NAME = "music";
	public static final String COLUMN_OWNER_INFO = "ownerinfo";
	public static final String COLUMN_PLAYLIST_ID = "playlistid";
	public static final String COLUMN_PLAY_URL = "playurl";
	public static final String COLUMN_SINGER = "singer";
	public static final String COLUMN_TITLE = "title";

	public Long insert(MusicInfo info) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_OWNER_INFO, info.getOwnerInfo());
		values.put(COLUMN_PLAYLIST_ID, info.getPlayListId());
		values.put(COLUMN_PLAY_URL, info.getPlayUrl());
		values.put(COLUMN_SINGER, info.getSinger());
		values.put(COLUMN_TITLE, info.getTitle());
		return sqlite.insert(TABLE_NAME, null, values);
	}
}
