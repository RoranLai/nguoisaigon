package com.nguoisaigon;

import com.nguoisaigon.util.DatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeScreenActivity extends Activity
{
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        DatabaseHelper datahelper = new DatabaseHelper();
	}
	
	public void btnstore_click(View view) {
		Intent intent = new Intent(this, StoreMainActivity.class);
		startActivity(intent);
	}
	
	public void btnhelp_click(View view) {
		//Intent intent = new Intent(this, .class);
		//startActivity(intent);
	}
	
	public void btnfacebook_click(View view) {
//		Intent intent = new Intent(this, StoreMainActivity.class);
//		startActivity(intent);
	}
	
	public void btnphone_click(View view) {
//		Intent intent = new Intent(this, StoreMainActivity.class);
//		startActivity(intent);
	}
	
	public void btnmusic_click(View view) {
		Intent intent = new Intent(this, MusicActivity.class);
		startActivity(intent);
	}
	
	public void btnevents_click(View view) {
		Intent intent = new Intent(this, EventsActivity.class);
		startActivity(intent);
	}
	
	public void btntv_click(View view) {
		Intent intent = new Intent(this, NewsActivity.class);
		startActivity(intent);
	}
}
