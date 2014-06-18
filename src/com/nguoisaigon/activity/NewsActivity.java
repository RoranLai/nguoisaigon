package com.nguoisaigon.activity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.nguoisaigon.R;
import com.nguoisaigon.entity.NewsInfo;
import com.nguoisaigon.util.WebService;
import com.nguoisaigon.util.WebService.WebServiceDelegate;

public class NewsActivity extends Activity implements WebServiceDelegate {
	ArrayList<NewsInfo> listNews;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_layout);
		this.listNews = new ArrayList<NewsInfo>();
		String jsonData = "[{'createDate':'2014-03-24T18:37:01',"
				+ "'newsContent':'tin tức mới nhất',"
				+ "'newsId':'e8f3ac76-1a23-4fbc-91fe-1a293a5b723b';"
				+ "'ownerInfo':'abc';" + "'title':'nguoi sai gon';"
				+ "},{ 'createDate':'2014-03-18T01:44:41';"
				+ "'newsContent':'test test';"
				+ "'newsId':'d854144f-49b8-4cd0-8732-b5123679e88d';"
				+ "'ownerInfo':'nnn';" + "'title':'Tin moi';}]";
		try {

			JSONArray listNewsJSON = new JSONArray(jsonData);
			for (int i = 0; i < listNewsJSON.length(); i++) {
				JSONObject newsJSON = listNewsJSON.getJSONObject(i);
				NewsInfo news = new NewsInfo();
				news.setCreateDate((Date) newsJSON.get("createDate"));
				news.setNewsContent(newsJSON.getString("newsContent"));
				news.setNewsId(newsJSON.getString("newsId"));
				news.setOwnerInfo(newsJSON.getString("ownerInfo"));
				news.setTitle(newsJSON.getString("title"));
				this.listNews.add(news);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

//		TextView tvCreateDate = (TextView) findViewById(R.id.tvCreateDate);
//		TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
//		TextView tvContent = (TextView) findViewById(R.id.tvContent);
//		NewsInfo news = this.listNews.get(0);
//		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
//		String createDate = formater.format(news.getCreateDate());
//		tvCreateDate.setText(createDate);
//		tvTitle.setText(news.getTitle());
//		tvContent.setText(news.getNewsContent());

		// Download data
		WebService ws = new WebService(this);
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		ws.setGettingNewsData(cal.getTime());
		ws.execute();
		
		

	}

	@Override
	public void taskCompletionResult(JSONArray result) {
		Log.i("NewsActivity", (result == null)?"null":result.toString());
		System.out.println(result.toString());
//		for (int i = 0; i < result.length(); i++) {
//			JSONObject newsJSON = result.getJSONObject(i)
//			NewsInfo news = new NewsInfo();
//			news.setCreateDate((Date) newsJSON.get("createDate"));
//			news.setNewsContent(newsJSON.getString("newsContent"));
//			news.setNewsId(newsJSON.getString("newsId"));
//			news.setOwnerInfo(newsJSON.getString("ownerInfo"));
//			news.setTitle(newsJSON.getString("title"));
//			this.listNews.add(news);
//		}
	}

	public void btnCloseClick() {
		this.onBackPressed();
	}
}
