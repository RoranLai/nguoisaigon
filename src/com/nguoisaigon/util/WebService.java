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

	private String SERVER_URL = "http://rest.itsleek.vn";
	private String url;
	private WebServiceDelegate delegate;

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

	}

	/**
	 * Get all events from server
	 * 
	 * @return JSONObject This object contains all keys/values of event.
	 * */
	public void setGettingEventsData() {
		url = SERVER_URL + "/api/events";
	}

	/**
	 * Send a HTML request to server
	 * 
	 * @param url
	 *            the HTML request string
	 * @return JSONObject This object contains all keys/values of response.
	 * */
	private JSONArray getDataFromUrl() {
		Log.i("WebService", "WebService: getDataFromUrl " + url);
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
					JSONObject object = new JSONObject(out.toString());
					responseString.put(object);
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
		} catch (IOException e) {
			Log.e("WebService", e.getMessage());
		} catch (JSONException e) {
			Log.e("WebService", e.getMessage());
		}
		url = null;
		return responseString;

	}

	@Override
	protected JSONArray doInBackground(String... arg0) {
		if (url != null) {
			return getDataFromUrl();
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
