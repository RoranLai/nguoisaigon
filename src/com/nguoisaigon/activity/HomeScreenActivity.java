package com.nguoisaigon.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.nguoisaigon.R;
import com.nguoisaigon.db.DBHelper;
import com.nguoisaigon.db.SettingDB;
import com.nguoisaigon.entity.SettingInfo;

public class HomeScreenActivity extends Activity
{
	private DBHelper datahelper;
	
	private ImageView storeHelp;
	private ImageView eventHelp;
	private ImageView newsHelp;
	private ImageView musicHelp;
	Handler handler = new Handler();
	
	Runnable hideHelp = new Runnable() {
		
		@Override
		public void run() {
			storeHelp.setVisibility(ImageView.GONE);
			eventHelp.setVisibility(ImageView.GONE);
			newsHelp.setVisibility(ImageView.GONE);
			musicHelp.setVisibility(ImageView.GONE);
		}
	};

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        storeHelp = (ImageView) findViewById(R.id.homeStoreHelp);
		eventHelp = (ImageView) findViewById(R.id.homeCalendarHelp);
		newsHelp = (ImageView) findViewById(R.id.homeNewsHelp);
		musicHelp = (ImageView) findViewById(R.id.homeMusicHelp);
		
		this.showHelp();
		
		ImageView tvImage = (ImageView) findViewById(R.id.homeTVAnimation);
		tvImage.setBackgroundResource(R.drawable.tvanimation);
		AnimationDrawable tvAnimation = (AnimationDrawable) tvImage.getBackground();
		tvAnimation.start();
		
		ImageView tvLight = (ImageView) findViewById(R.id.homeTVLight);
		tvLight.setBackgroundResource(R.drawable.tvlightanimation);
		AnimationDrawable tvLightAnimation = (AnimationDrawable) tvLight.getBackground();
		tvLightAnimation.start();
		
		ImageView musicImage = (ImageView) findViewById(R.id.homeMusic);
		musicImage.setBackgroundResource(R.drawable.musicanimation);
		AnimationDrawable musicAnimation = (AnimationDrawable) musicImage.getBackground();
		musicAnimation.start();
		
		ImageView phoneImage = (ImageView) findViewById(R.id.homePhone);
		phoneImage.setBackgroundResource(R.drawable.phoneanimation);
		AnimationDrawable phoneAnimation = (AnimationDrawable) phoneImage.getBackground();
		phoneAnimation.start();
        //setDatahelper(new DBHelper(this));
	}
	
	public void btnstore_click(View view) {
		Intent intent = new Intent(this, StoreMainActivity.class);
		startActivity(intent);
	}
	
	public void btnhelp_click(View view) {
		this.showHelp();
	}
	
	public void btnfacebook_click(View view) {
		SettingDB settingDB = new SettingDB(this);
		SettingInfo info = settingDB.getSetting();
		
		Intent intent = new Intent(this, FacebookPlugin.class);
		Bundle bundle = new Bundle();
		bundle.putString("link", info.getAppLink());
		bundle.putString("description", "Dialog description");
		bundle.putString("caption", "Dialog Caption");
		
		intent.putExtras(bundle);
		startActivity(intent);
		FacebookPlugin fbPlugin = new FacebookPlugin("Dialog description", info.getAppLink(), "Dialog Caption");
		fbPlugin.postToWall();
		fbPlugin.showDialog("Dialog description", info.getAppLink(), "Dialog Caption");
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
	
	void showHelp()
	{
		storeHelp.setVisibility(ImageView.VISIBLE);
		eventHelp.setVisibility(ImageView.VISIBLE);
		newsHelp.setVisibility(ImageView.VISIBLE);
		musicHelp.setVisibility(ImageView.VISIBLE);
		
		handler.postDelayed(hideHelp, 5000);
	}
}
