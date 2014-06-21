package com.nguoisaigon.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;

public class FacebookPlugin
{
	Context context;
	
	String description;
	String link;
	String caption;
	
	void FaceBookPlugin(Context context)
	{
		this.context = context;
	}
	
	public void showDialog(String desc, String appLink, String cap)
	{
		description = desc;
		link = appLink;
		caption = cap;
		
		postToWall();
	}
	
	void postToWall()
	{
		Bundle postParams = new Bundle();
		postParams.putString("description",description);
        postParams.putString("caption",caption);
        postParams.putString("link", link);
        WebDialog feedDialog = new WebDialog.FeedDialogBuilder(context, Session.getActiveSession(), postParams)
        .setOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(Bundle values, FacebookException error) {
                if(error==null)
                {
                    final String postId=values.getString("post_id");
                    if(postId!=null)
                        Toast.makeText(context.getApplicationContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context.getApplicationContext(), "Post canceled", Toast.LENGTH_SHORT).show();
                }
                else
                    if(error instanceof FacebookOperationCanceledException)
                        Toast.makeText(context.getApplicationContext(), "Publish canceled",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(context.getApplicationContext(), "connection error", Toast.LENGTH_SHORT).show();
            }
        }).build();
        feedDialog.show();
	}
}

