package com.example.qrcodereadertest;

import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask; 

public class ServerInfoGetter extends AsyncTask<String, Integer, String> { 
	
	@Override 
	protected String doInBackground(String... params) {
		
		String URL = params[0]; 
		
		HttpGet get = null;
		HttpEntity entity = null;
		
		try { 

			DefaultHttpClient client = new DefaultHttpClient(); 
			
			get = new HttpGet(URL);
			get.setHeader("Content-type", "application/json");
			
			HttpResponse response = client.execute(get);
			
			entity = response.getEntity();
			
		} catch (Exception e) {} 
		
		get.abort(); 
		
		try {
			return EntityUtils.toString(entity);
		} catch (Exception e) {}
		
		return ""; 
	} 
	
}






