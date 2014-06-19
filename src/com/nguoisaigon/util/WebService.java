package com.nguoisaigon.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

public class WebService extends AsyncTask<String, Void, JSONArray> {
	public interface WebServiceDelegate {
		public void taskCompletionResult(JSONArray result);
	}

	public enum productCategory {
		cat_fashion_man("cat_fashion_man", 0), cat_fashion_woman(
				"cat_fashion_woman", 1), cat_fashion_kid("cat_fashion_kid", 2), cat_cos_man(
				"cat_cos_man", 3), cat_cos_woman("cat_cos_woman", 4), cat_food(
				"cat_food", 5), cat_lifeStyle("cat_lifeStyle", 6);

		private String name;
		private int value;

		private productCategory(String vname, int value) {
			setName(vname);
			setIntValue(value);
		}

		public int getIntValue() {
			return value;
		}

		public void setIntValue(int intValue) {
			this.value = intValue;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public enum productSearchType {
		search_for_client("search_for_client", 0), search_for_admin(
				"search_for_admin", 1);

		private String name;
		private int value;

		private productSearchType(String vname, int value) {
			setName(vname);
			setIntValue(value);
		}

		public int getIntValue() {
			return value;
		}

		public void setIntValue(int intValue) {
			this.value = intValue;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	private final String SERVER_URL = "http://rest.itsleek.vn";
	private final String STR_MAIN_USER_AGENT = "Fiddler";
	private final String STR_MAIN_HOST_INFO = "rest.itsleek.vn";
	private String url;
	private WebServiceDelegate delegate;

	protected JSONObject params;

	/**
	 * true if the request that sends to server is a Post request. Default is
	 * false.
	 */
	public Boolean isPostRequest = false;

	/**
	 * Constructor with delegate
	 */
	public WebService(WebServiceDelegate vdelegate) {
		setDelegate(vdelegate);
	}

	/**
	 * Get application setting from server
	 * 
	 * @return JSONObject This object contains all keys/values of setting.
	 * */
	public void setGettingAppSetting() {
		url = SERVER_URL + "/api/Setting";
		this.isPostRequest = false;
	}

	/**
	 * Get all news of specific date from server
	 * 
	 * @param date
	 *            The selected date.
	 * @return JSONObject This object contains all keys/values of setting.
	 * */
	@SuppressLint("SimpleDateFormat")
	public void setGettingNewsData(Date date) {
		// Convert date to string with format yyyy-MM-dd
		SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = fmtOut.format(date);
		url = SERVER_URL + "/api/news?selectedDate=" + dateStr;
		this.isPostRequest = false;

	}

	/**
	 * Get all events from server
	 * 
	 * @return JSONObject This object contains all keys/values of event.
	 * */
	public void setGettingEventsData() {
		url = SERVER_URL + "/api/events";
		this.isPostRequest = false;
	}

	/**
	 * Get all products from server by category and search type
	 * 
	 * @param category
	 *            The product category.
	 * @param searchType
	 *            Type of user
	 * @return JSONObject This object contains all keys/values of event.
	 * */
	public void setGettingProducts(productCategory category,
			productSearchType searchType) {
		// http://rest.itsleek.vn/api/product?catId=%i&search_type=%i
		url = SERVER_URL + "/api/product?catId=" + category.getIntValue()
				+ "&search_type=" + searchType.getIntValue();
		this.isPostRequest = false;
	}

	/**
	 * Send Transaction Details to server
	 * 
	 * @return JSONObject This object contains all keys/values of event.
	 * */
	public void setTransactionDetailRequest(TransactionDetailInfo info) {
		// http://rest.itsleek.vn/api/TransactionDetail
		try {
			url = SERVER_URL + "/api/TransactionDetail";
			params = new JSONObject(info.toString());
			Log.i("WebService", "setTransactionDetail" + info.toString());
			this.isPostRequest = true;
		} catch (JSONException e) {
			Log.e("WebService", e.getMessage());
		}
	}

	/**
	 * Send a HTML request to server (POST)
	 */
	private void postDataToServer() {
		Log.i("WebService", "WebService: postDataToServer " + url);

		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();

			HttpPost httppost = new HttpPost(url);

			httppost.addHeader("User-Agent", STR_MAIN_USER_AGENT);
			httppost.addHeader("Host", STR_MAIN_USER_AGENT);
			httppost.addHeader("Content-Type", STR_MAIN_USER_AGENT);
			httppost.addHeader("Content-Length", STR_MAIN_USER_AGENT);

			ByteArrayEntity entity;

			entity = new ByteArrayEntity(params.toString().getBytes("UTF8"));
			httppost.setEntity(entity);

			httpclient.execute(httppost);

		} catch (ClientProtocolException e) {
			Log.e("WebService", e.getMessage());
		} catch (IOException e) {
			Log.e("WebService", e.getMessage());
		}
	}

	/**
	 * Send a HTML request to server (GET)
	 * */
	private JSONArray getDataFromServer() {
		Log.i("WebService", "WebService: getDataFromServer " + url);
		JSONArray responseString = new JSONArray();
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;
			response = httpclient.execute(new HttpGet(url));
			StatusLine statusLine = response.getStatusLine();
			Log.i("WebService",
					"WebService: getDataFromUrl " + response.getStatusLine());
			if (statusLine.getStatusCode() == HttpStatus.SC_ACCEPTED) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				Log.i("RoranLai", "WebService: response " + out.toString());
				if (out.toString().startsWith("{")) {
					JSONObject jsonObject = new JSONObject(out.toString());
					responseString.put(jsonObject);
				} else {
					responseString = new JSONArray(out.toString());
				}
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			Log.e("WebService", e.getMessage());
			responseString = null;
		} catch (IOException e) {
			Log.e("WebService", e.getMessage());
			responseString = null;
		} catch (JSONException e) {
			Log.e("WebService", e.getMessage());
			responseString = null;
		}
		url = null;
		return responseString;

	}

	@Override
	protected JSONArray doInBackground(String... arg0) {
		if (url != null) {
			if (this.isPostRequest) {
				postDataToServer();
			} else {
				return getDataFromServer();
			}
		}
		return null;
	}

	@Override
	protected void onPostExecute(JSONArray result) {
		super.onPostExecute(result);
		delegate.taskCompletionResult(result);
	}

	public WebServiceDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(WebServiceDelegate delegate) {
		this.delegate = delegate;
	}
}