package com.example.qrcodereadertest;

import java.util.ArrayList;

import android.app.ListActivity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ShowProductsActivity extends ListActivity {

	ListView myListView;
	MyAdapter adapter;
	ArrayList<Product> productList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);
		
		Bundle b = this.getIntent().getExtras();
		if (b != null) {
			productList = b.getParcelableArrayList("Products");
		}
		
		createList();
	}
	
	private void createList() {
		adapter = new MyAdapter();

		Log.d("MYTAAG2", "A");
		
		for (int i = 0; i < productList.size(); i++) {
			adapter.add(productList.get(i));
		}
		
		Log.d("MYTAAG2", "B");

		setListAdapter(adapter);
	}

	private class MyAdapter extends BaseAdapter {
		private ArrayList<Product> items = new ArrayList<Product>();

		@Override
		public int getCount() {
			return items.size();
		}

		public void add(Product product) {
			items.add(product);
			notifyDataSetChanged();
		}

		@Override
		public Object getItem(int position) {
			return items.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null)
				convertView = View.inflate(ShowProductsActivity.this, R.layout.list_item,
						null);
			final Product item = (Product) getItem(position);

		//	Typeface type = Typeface.createFromAsset(getAssets(),
			//		"fonts/sylfaen.ttf");

			TextView tv = (TextView) convertView.findViewById(R.id.text);
		//	tv.setTypeface(type);
			tv.setText(item.getName());
			tv.setTextColor(Color.RED);

			//String parsedLink = parsePictureLink(item.getDescription());

			//Drawable d = LoadImageFromWebOperations(parsedLink);

			//ImageView iv = (ImageView) convertView.findViewById(R.id.image);
		//	iv.setImageDrawable(d);

		//	TextView price = (TextView) convertView.findViewById(R.id.price);
		//	price.setTypeface(type);
		//	price.setText("   ფასი: " + item.getPrice() + " ლარი");

		//	TextView sold = (TextView) convertView.findViewById(R.id.sold);
		//	sold.setTypeface(type);
		//	sold.setText("   გაიყიდა: " + item.getSold() + " ვაუჩერი");

		//	Button button = (Button) convertView.findViewById(R.id.button);
		//	button.setOnClickListener(new OnClickListener() {

		//		@Override
		//		public void onClick(View v) {
					
		//		}
		//	});
		//	button.setTypeface(type);
		//	button.setText("წაშლა");

			return convertView;
		}
	}

}


