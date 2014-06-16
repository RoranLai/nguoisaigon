package com.nguoisaigon.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nguoisaigon.R;
import com.nguoisaigon.db.SettingDB;
import com.nguoisaigon.entity.SettingInfo;
import com.nguoisaigon.util.SystemUiHider;
import com.nguoisaigon.util.WebService;
import com.nguoisaigon.util.WebService.WebServiceDelegate;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class MainActivity extends Activity implements WebServiceDelegate {

	private AnimationDrawable animation;
	private ProgressBar indicator;
	TextView loadingLabel;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //Setup UI view
        ImageView welcomeIcon = (ImageView) findViewById(R.id.welcomeicon);
        welcomeIcon.setBackgroundResource(R.drawable.welcomeiconani);
        indicator = (ProgressBar) findViewById(R.id.welcome_indicator);
        loadingLabel = (TextView) findViewById(R.id.welcom_loadinglabel);
        
        //Set indicator color
        indicator.getIndeterminateDrawable().setColorFilter(R.color.welcome_loading_color, 
        		android.graphics.PorterDuff.Mode.MULTIPLY);
        
        //Start animations
        if(animation == null)
        {
        	animation = (AnimationDrawable) welcomeIcon.getBackground();
            animation.start();
            animation.stop();
        }
        
        //Download data
        WebService ws = new WebService(this);
        ws.setGettingAppSetting();
        ws.execute();
        
        indicator.setVisibility(View.VISIBLE);
        indicator.setVisibility(View.VISIBLE);
        new AsyncTask<Void,Void,Void>(){

        //The variables you need to set go here

            @Override
            protected Void doInBackground(final Void... params){
                 // Do your loading here. Don't touch any views from here, and then return null
                 return null;
            }


            @Override
            protected void onPostExecute(final Void result){
                 // Update your views here
            	indicator.setVisibility(View.GONE);
            }
        }.execute();
        
        //Setup loading label
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/wg_legacy_edition.ttf");
        loadingLabel.setTypeface(tf);
        loadingLabel.setVisibility(View.VISIBLE);
    }

	@Override
	public void taskCompletionResult(JSONObject result)
	{
		JSONObject appSettingObject = result;
        if(appSettingObject != null)
        {
        	//Insert database
        	SettingDB settingDB = new SettingDB(this);
        	SettingInfo info = new SettingInfo();
        	if(info != null)
        	{
        		try {
					info.setAppLink(appSettingObject.getString(this.getString(R.string.setting_applink)));
					info.setParseAppId(appSettingObject.getString(this.getString(R.string.setting_parseappid)));
	        		info.setSettingId(appSettingObject.getString(this.getString(R.string.setting_settingid)));
	        		settingDB.insert(info);
				} catch (JSONException e) {
					e.printStackTrace();
				}
        	}
        }
        
        //Call the home page
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
	}

}
