package com.nguoisaigon.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nguoisaigon.R;
import com.nguoisaigon.entity.NewsInfo;
import com.nguoisaigon.util.NewsPageFragment;
import com.nguoisaigon.util.WebService;
import com.nguoisaigon.util.WebService.WebServiceDelegate;

@SuppressLint("SimpleDateFormat")
public class NewsActivity extends FragmentActivity implements
		WebServiceDelegate {

	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next wizard steps.
	 */
	private ViewPager mPager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private PagerAdapter mPagerAdapter;

	private ArrayList<NewsInfo> listNews;

	private Calendar currentDate;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_layout);

		this.currentDate = Calendar.getInstance();
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/wg_legacy_edition.ttf");
		TextView tvPage = (TextView) findViewById(R.id.tvPage);
		TextView tvDate = (TextView) findViewById(R.id.tvDate);
		tvPage.setTypeface(tf);
		tvDate.setTypeface(tf);
		this.loadData();
	}

	@Override
	public void taskCompletionResult(JSONArray result) {
		Log.i("NewsActivity", (result == null) ? "null" : result.toString());
		if (result != null) {
			try {
				for (int i = 0; i < result.length(); i++) {
					JSONObject newsJSON = result.getJSONObject(i);
					NewsInfo news = new NewsInfo();
					String strDate = newsJSON.getString("createDate");
					SimpleDateFormat formater = new SimpleDateFormat(
							"yyyy-MM-dd'T'HH:mm:ss");
					Date createDate;
					createDate = formater.parse(strDate);
					news.setCreateDate(createDate);
					news.setNewsContent(newsJSON.getString("newsContent"));
					news.setNewsId(newsJSON.getString("newsId"));
					news.setOwnerInfo(newsJSON.getString("ownerInfo"));
					news.setTitle(newsJSON.getString("title"));
					this.listNews.add(news);
					Log.i("NewsActivity - this.listNews.size()",
							this.listNews.size() + "");
				}
			} catch (Exception e) {
				Log.i("NewsActivity", e.getMessage());
			}
		}
		updateData();
	}

	public void loadData() {
		this.listNews = new ArrayList<NewsInfo>();
		// Download data
		WebService ws = new WebService(this);
		ws.setGettingNewsData(this.currentDate.getTime());
		ws.execute();
	}

	public void updatePageNumView() {
		TextView tvPage = (TextView) findViewById(R.id.tvPage);
		String pageDisplay = "0/0";
		if (this.listNews.size() > 0) {
			pageDisplay = mPager.getCurrentItem() + "/"
					+ mPagerAdapter.getCount();
		}
		tvPage.setText(pageDisplay);
	}

	public void updateData() {
		
		TextView tvDate = (TextView) findViewById(R.id.tvDate);
		SimpleDateFormat formater = new SimpleDateFormat("'Th�ng' MM/yyyy");
		String dateDisplay = formater.format(currentDate.getTime());
		tvDate.setText(dateDisplay);

		List<Fragment> fragments = getFragments();
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(),
				fragments);
		mPager.setAdapter(mPagerAdapter);
		this.updatePageNumView();
	}

	private List<Fragment> getFragments() {
		Log.i("NewsActivity - getFragments", this.listNews.size() + "");
		List<Fragment> fList = new ArrayList<Fragment>();
		for (NewsInfo news : this.listNews) {
			fList.add(NewsPageFragment.newInstance(news));
		}
		return fList;
	}

	public void btnCloseClick(View view) {
		this.finish();
	}

	public void btnFacebookClick(View view) {

	}

	public void btnEmailClick(View view) {

	}

	public void btnPageNextClick(View view) {
		int currentItem = mPager.getCurrentItem();
		if (currentItem < mPager.getAdapter().getCount()) {
			mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
		}
	}

	public void btnPagePreviousClick(View view) {
		int currentItem = mPager.getCurrentItem();
		if (currentItem < 2) {
			mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
		}
	}

	public void btnDateNextClick(View view) {
		this.currentDate.add(Calendar.MONTH, 1);
		this.loadData();
	}

	public void btnDatePreviousClick(View view) {
		this.currentDate.add(Calendar.MONTH, -1);
		this.loadData();
	}

	/**
	 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects,
	 * in sequence.
	 */
	private class NewsPagerAdapter extends FragmentStatePagerAdapter {
		private List<Fragment> fragments;

		public NewsPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}
	}
}