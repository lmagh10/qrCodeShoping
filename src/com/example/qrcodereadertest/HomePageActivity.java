package com.example.qrcodereadertest;

//import com.google.android.gms.common.GooglePlayServicesUtil;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
//import com.google.android.gms.drive.Drive;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePageActivity extends Activity{
	
	private static final String USER_ENTERS_APP_URL = "http://barcodeshopping.net46.net/userRegistration.php/?telephoneNumber=";
	private static final String USER_PRODUCTS_URL = "http://barcodeshopping.net46.net/wishList.php/?emeiCode=";
	
	Button addProduct, viewProducts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
//		int isGooglePlayServiceAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
		
	//	GoogleApiClient apiClient = new GoogleApiClient.Builder(this)
	 //   .addApi(Drive.API)
	 //   .addScope(Drive.SCOPE_FILE)
	  //  .build();
		
	//	apiClient.connect();
	//	apiClient.registerConnectionCallbacks(new ConnectionCallbacks() {
			
		//	@Override
		//	public void onConnectionSuspended(int arg0) {
		//		
		//	}
			
		//	@Override
		//	public void onConnected(Bundle arg0) {
				
		//	}
		//});
	//	setContentView(R.layout.);
		
	//	
	//	TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		
	//	String phoneNumber = mngr.getDeviceId();
	//	ServerInfoGetter reqSender = new ServerInfoGetter();
	//	String result = null;
		
	//	try {
	//		result = (reqSender.execute(USER_ENTERS_APP_URL + "ThieryHenry")).get();
	//	} catch (Exception e) {}
		
	//	resultParser(result);
		
	//	Log.d("MYTAAG", result);
		
		this.addProduct = (Button)findViewById(R.id.addProduct);
		this.viewProducts = (Button)findViewById(R.id.viewMyroducts);
		
		this.addProduct.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
				startActivityForResult(intent, 0);
			}
		});
		
		this.viewProducts.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				ServerInfoGetter getProductList = new ServerInfoGetter();
				
				try {
					ArrayList<Product> productList = new ArrayList<Product>();
					String productListStringUnparsed = getProductList.execute(USER_PRODUCTS_URL + "646111").get();
					
					String productListJSON = resultParser(productListStringUnparsed);
					
					parseJSONFillProducts(productList, productListJSON);
					
					Intent showProducts = new Intent(HomePageActivity.this, ShowProductsActivity.class);
					Bundle b = new Bundle();
					b.putParcelableArrayList("Products", (ArrayList<? extends Parcelable>) productList);
					showProducts.putExtras(b);
					HomePageActivity.this.startActivity(showProducts);
					
				} catch (Exception e) {}
			
			}
		});
		
	}
	
	private String resultParser(String output) {
		int index = output.indexOf("<!-- Hosting24 Analytics Code -->");
		return output.substring(0, index);	
	}
	
	private void parseJSONFillProducts(ArrayList<Product> listToFill, String jSONString) throws JSONException {
		JSONArray jArray = new JSONArray(jSONString);
		
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
			listToFill.add(product);
		}
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
//				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				System.out.println(contents);
			}
		}
	}
}
