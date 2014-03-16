package com.example.qrcodereadertest;

import java.util.ArrayList;

import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask; 

public class ProductListGetter extends AsyncTask<String, Integer, ArrayList<Product>> { 
	
	private static final String URL_TEMPLATE = 
			"http://barcodeshopping.net46.net/wishList.php/?emeiCode=";
	
	@Override 
	protected ArrayList<Product> doInBackground(String... params) {
		
		String emeiCode = params[0]; 
		
		ArrayList<Product> productList = new ArrayList<Product>();
		HttpGet get = null;
		
		try { 

			String URL =  URL_TEMPLATE + emeiCode;
			
			DefaultHttpClient client = new DefaultHttpClient(); 
			get = new HttpGet(URL);
			get.setHeader("Content-type", "application/json");
			
			HttpResponse response = client.execute(get); 
			HttpEntity entity = response.getEntity(); 
			
		    fillProduct(productList, EntityUtils.toString(entity));
		} catch (Exception e) {} 
		
		get.abort(); 
		return productList; 
	} 
	
	private void fillProduct(ArrayList<Product> products, String resToParse) throws JSONException {
		
		int index = resToParse.indexOf("<!-- Hosting24 Analytics Code -->");
		
		String jsonString = resToParse.substring(0, index);		
		
		JSONArray jArray = new JSONArray(jsonString);
		
		for(int i = 0; i < jArray.length(); i++) {
			
			JSONObject element = jArray.getJSONObject(i);
			
			Product product = new Product();
			
			product.setID(element.getInt("ID"));
			product.setStockID(element.getInt("STOCK_ID"));
			product.setName(element.getString("name"));
			product.setDescription(element.getString("description"));
			product.setImage(element.getString("image"));
			product.setPrice(element.getInt("price"));
			product.setUSER_ID(element.getInt("USER_ID"));
			product.setPRODUCT_ID(element.getInt("PRODUCT_ID"));
			products.add(product);
			
		}
		
	}
}






