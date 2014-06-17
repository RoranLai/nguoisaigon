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
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

public class WebService extends AsyncTask<String, Void, JSONObject> {
	public interface WebServiceDelegate {
		public void taskCompletionResult(JSONObject result);
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
	private void getDataFromUrl() {
		Log.i("RoranLai", "WebService: getDataFromUrl " + url);
		JSONObject responseString = null;
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;
			response = httpclient.execute(new HttpGet(url));
			StatusLine statusLine = response.getStatusLine();

			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				Log.i("RoranLai", "WebService: response " + out.toString());
				responseString = new JSONObject(out.toString());
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		url = null;
		delegate.taskCompletionResult(responseString);
	}

	@Override
	protected JSONObject doInBackground(String... arg0) {
		if (url != null) {
			getDataFromUrl();
		}
		return null;
	}

	public WebServiceDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(WebServiceDelegate delegate) {
		this.delegate = delegate;
	}
}
