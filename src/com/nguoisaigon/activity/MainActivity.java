package com.nguoisaigon.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.nguoisaigon.R;
import com.nguoisaigon.util.SystemUiHider;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class MainActivity extends Activity {

	private AnimationDrawable animation;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ImageView welcomeIcon = (ImageView) findViewById(R.id.welcomeicon);
        welcomeIcon.setBackgroundResource(R.drawable.welcomeiconani);
        if(animation == null)
        {
        	animation = (AnimationDrawable) welcomeIcon.getBackground();
            animation.start();
        }
        animation.stop();
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }

}
