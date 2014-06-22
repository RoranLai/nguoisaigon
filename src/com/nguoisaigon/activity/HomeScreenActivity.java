package com.nguoisaigon.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguoisaigon.R;
import com.nguoisaigon.db.DBHelper;
import com.nguoisaigon.db.SettingDB;
import com.nguoisaigon.entity.SettingInfo;
import com.nguoisaigon.util.Emailplugin;

public class HomeScreenActivity extends Activity
{
	//ContactView
	FrameLayout contacView;
	TextView contactCafe;
	TextView contactNguoiSaiGon;
	TextView contactAddress1;
	TextView contactAddress2;
	TextView contactEmail;
	TextView contactPhone1;
	TextView contactPhone2;
	TextView contactFacebook;
	
	//- End ContactView
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
		
		contacView = (FrameLayout) findViewById(R.id.homeContactView);
		contactCafe = (TextView) findViewById(R.id.homeContactCafe);
		contactNguoiSaiGon = (TextView) findViewById(R.id.homeContactNguoiSaiGon);
		contactAddress1 = (TextView) findViewById(R.id.homeContactAddress1);
		contactAddress2 = (TextView) findViewById(R.id.homeContactAddress2);
		contactEmail = (TextView) findViewById(R.id.homeContactEmail);
		contactPhone1 = (TextView) findViewById(R.id.homeContactphone1);
		contactPhone2 = (TextView) findViewById(R.id.homeContactphone2);
		contactFacebook = (TextView) findViewById(R.id.homeContactFacebook);
		//Setup contact View
		setupContactView();
		
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
	}
	
	public void btnphone_click(View view) {
		contacView.setVisibility(View.VISIBLE);
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
	
	void setupContactView ()
	{
		 //Setup font label
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/wg_legacy_edition.ttf");
        contactCafe.setTypeface(tf);
        contactNguoiSaiGon.setTypeface(tf);
        contactAddress1.setTypeface(tf);
        contactAddress2.setTypeface(tf);
        contactEmail.setTypeface(tf);
        contactPhone1.setTypeface(tf);
        contactPhone2.setTypeface(tf);
        contactFacebook.setTypeface(tf);
	}
	
	public void phone1OnClick(View view)
	{
		String url = "tel:0932113103";
	    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
	    startActivity(intent);
	}
	
	public void phone2OnClick(View view)
	{
		String url = "tel:0932113183";
	    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
	    startActivity(intent);
	}
	
	public void emailOnClick(View view)
	{
		Emailplugin.SendEmailFromHomeView(this);
	}
	
	public void facebookOnClick(View view)
	{
		
	}
	
	public void hideContactOnClick(View view)
	{
		contacView.setVisibility(View.INVISIBLE);
	}
}
