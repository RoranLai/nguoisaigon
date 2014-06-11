package com.nguoisaigon.util;

import android.annotation.SuppressLint;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class WebService 
{
	static String SERVER_URL = "http://rest.itsleek.vn";
	
	/*
	 * Get application setting from server 
	 * @return JSONObject This object contains all keys/values of setting.
	 * */
	public static JSONObject getAppSetting()
	{
		String url = SERVER_URL + "/api/Setting";
		return getDataFromUrl(url);
	}
	
	/*
	 * Get all news of specific date from server 
	 * @param date The selected date.
	 * @return JSONObject This object contains all keys/values of setting.
	 * */
	@SuppressLint("SimpleDateFormat") public static JSONObject getNewsData(Date date)
	{
		//Convert date to string with format yyyy-MM-dd
		SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = fmtOut.format(date);
		String url = SERVER_URL + "/api/news?selectedDate=" + dateStr;
		
		return getDataFromUrl(url);
	}
	
	/*
	 * Get all events from server 
	 * @return JSONObject This object contains all keys/values of event.
	 * */
	public static JSONObject getEventsData()
	{
		String url = SERVER_URL + "/api/events";
		return getDataFromUrl(url);
	}
	
	/*
	 * Send a HTML request to server
	 * @param url the HTML request string 
	 * @return JSONObject This object contains all keys/values of response.
	 * */
	static JSONObject getDataFromUrl(String url)
	{
		try {
			HttpClient httpclient = new DefaultHttpClient();
		    HttpResponse response;
			response = httpclient.execute(new HttpGet(url));
			StatusLine statusLine = response.getStatusLine();
		    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		        ByteArrayOutputStream out = new ByteArrayOutputStream();
		        response.getEntity().writeTo(out);
		        out.close();
		        JSONObject responseString = new JSONObject(out.toString());
		        return responseString;
		    } else{
		        //Closes the connection.
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
		return null;
	}
}
