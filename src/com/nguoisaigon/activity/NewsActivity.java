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
import android.widget.ImageView;
import android.widget.ProgressBar;
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
		TextView tvMonth = (TextView) findViewById(R.id.tvMonth);
		TextView tvDate = (TextView) findViewById(R.id.tvDate);
		TextView tvLoading = (TextView) findViewById(R.id.tvNewsLoading);
		TextView tvNoNews = (TextView) findViewById(R.id.noNews);
		tvPage.setTypeface(tf);
		tvMonth.setTypeface(tf);
		tvDate.setTypeface(tf);
		tvLoading.setTypeface(tf);
		tvNoNews.setTypeface(tf);
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
			pageDisplay = mPager.getCurrentItem() + 1 + "/"
					+ mPagerAdapter.getCount();
		}
		tvPage.setText(pageDisplay);

		ImageView btnPagePrevious = (ImageView) findViewById(R.id.btnPagePrevious);
		ImageView btnPageNext = (ImageView) findViewById(R.id.btnPageNext);
		Log.i("NewsActivity - image alpha", btnPagePrevious.getImageAlpha()
				+ "");

		if (mPagerAdapter.getCount() > 1) {
			if (mPager.getCurrentItem() == 0) {
				btnPagePrevious.setImageAlpha(70);
				btnPageNext.setImageAlpha(1000);
			} else if (mPager.getCurrentItem() == (mPagerAdapter.getCount() - 1)) {
				btnPageNext.setImageAlpha(70);
				btnPagePrevious.setImageAlpha(1000);
			} else {
				btnPagePrevious.setImageAlpha(1000);
			}
		} else {
			btnPageNext.setImageAlpha(70);
			btnPagePrevious.setImageAlpha(70);
		}

	}

	public void updateData() {

		TextView tvDate = (TextView) findViewById(R.id.tvDate);
		SimpleDateFormat formater = new SimpleDateFormat("MM/yyyy");
		String dateDisplay = formater.format(currentDate.getTime());
		tvDate.setText(dateDisplay);

		List<Fragment> fragments = getFragments();
		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.pager);
		mPagerAdapter = new NewsPagerAdapter(getSupportFragmentManager(),
				fragments);
		mPager.setAdapter(mPagerAdapter);
		this.updatePageNumView();

		TextView tvNoNews = (TextView) findViewById(R.id.noNews);
		if (mPagerAdapter.getCount() < 1) {
			tvNoNews.setVisibility(TextView.VISIBLE);
		} else {
			tvNoNews.setVisibility(TextView.GONE);
		}

		ProgressBar indicator = (ProgressBar) findViewById(R.id.newsIndicator);
		indicator.setVisibility(ProgressBar.GONE);
		TextView tvLoading = (TextView) findViewById(R.id.tvNewsLoading);
		tvLoading.setVisibility(TextView.GONE);

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
			this.updatePageNumView();
		}
	}

	public void btnPagePreviousClick(View view) {
		int currentItem = mPager.getCurrentItem();
		if (currentItem > 0) {
			mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
			this.updatePageNumView();
		}
	}

	public void btnDateNextClick(View view) {
		this.currentDate.add(Calendar.MONTH, 1);
		ProgressBar indicator = (ProgressBar) findViewById(R.id.newsIndicator);
		indicator.setVisibility(ProgressBar.VISIBLE);
		TextView tvLoading = (TextView) findViewById(R.id.tvNewsLoading);
		tvLoading.setVisibility(TextView.VISIBLE);
		this.loadData();
	}

	public void btnDatePreviousClick(View view) {
		this.currentDate.add(Calendar.MONTH, -1);
		ProgressBar indicator = (ProgressBar) findViewById(R.id.newsIndicator);
		indicator.setVisibility(ProgressBar.VISIBLE);
		TextView tvLoading = (TextView) findViewById(R.id.tvNewsLoading);
		tvLoading.setVisibility(TextView.VISIBLE);
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
