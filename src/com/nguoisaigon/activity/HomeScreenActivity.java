package com.nguoisaigon.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nguoisaigon.R;
import com.nguoisaigon.db.DBHelper;

public class HomeScreenActivity extends Activity
{
	private DBHelper datahelper;
	
	private ImageView storeHelp;
	private ImageView eventHelp;
	private ImageView newsHelp;
	private ImageView musicHelp;
	

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        

        //setDatahelper(new DBHelper(this));
	}
	
	public void btnstore_click(View view) {
		Intent intent = new Intent(this, StoreMainActivity.class);
		startActivity(intent);
	}
	
	public void btnhelp_click(View view) {
        storeHelp = (ImageView) findViewById(R.id.homeStoreHelp);
		eventHelp = (ImageView) findViewById(R.id.homeCalendarHelp);
		newsHelp = (ImageView) findViewById(R.id.homeNewsHelp);
		musicHelp = (ImageView) findViewById(R.id.homeMusicHelp);
		
		storeHelp.setVisibility(ImageView.VISIBLE);
		eventHelp.setVisibility(ImageView.VISIBLE);
		newsHelp.setVisibility(ImageView.VISIBLE);
		musicHelp.setVisibility(ImageView.VISIBLE);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				storeHelp.setVisibility(ImageView.GONE);
				eventHelp.setVisibility(ImageView.GONE);
				newsHelp.setVisibility(ImageView.GONE);
				musicHelp.setVisibility(ImageView.GONE);
			}
		};
		
		timer.schedule(task, 0, 500);
		
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

	public DBHelper getDatahelper() {
		return datahelper;
	}

	public void setDatahelper(DBHelper datahelper) {
		this.datahelper = datahelper;
	}
	
	@Override
	public void onBackPressed() {
		//Exit application
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
}
