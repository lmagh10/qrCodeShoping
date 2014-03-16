package com.example.qrcodereadertest;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {

	private int ID;
	private int StockID;
	private int price;
	private int USER_ID;
	private int PRODUCT_ID;
	
	private String name;
	private String description;
	private String image;
	private String saleDescription;
	
	public Product(){}
	
	@Override
	public String toString() {
		return "Product: ID = " + this.ID + ", STOCK_ID = " + this.StockID +", name = " +
					this.name + ", description = " + this.description + ", image = " + this.image
					+ ", price = " + this.price + ", USER_ID = " + this.USER_ID + ", PRODUCT_ID = "
					+ this.PRODUCT_ID + "; ";
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getStockID() {
		return StockID;
	}

	public void setStockID(int stockID) {
		StockID = stockID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(int uSER_ID) {
		USER_ID = uSER_ID;
	}

	public int getPRODUCT_ID() {
		return PRODUCT_ID;
	}

	public void setPRODUCT_ID(int pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}
	
	public Product(Parcel in) {
		readFromParcel(in);
	}
	
	private void readFromParcel(Parcel in) {
		ID = in.readInt();
		StockID = in.readInt();
		USER_ID = in.readInt();
		PRODUCT_ID = in.readInt();
		price = in.readInt();
		
		name = in.readString();
		description = in.readString();
		image = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeInt(ID);
		arg0.writeInt(StockID);
		arg0.writeInt(USER_ID);
		arg0.writeInt(PRODUCT_ID);
		arg0.writeInt(price);
		arg0.writeString(name);
		arg0.writeString(description);	
		arg0.writeString(image);
	}
	
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
  
		public Product createFromParcel(Parcel in) {
			/*Product mProduct = new Product();
			mProduct.ID = in.readInt();
			mProduct.StockID = in.readInt();
			mProduct.USER_ID = in.readInt();
			mProduct.PRODUCT_ID = in.readInt();
			mProduct.price = in.readInt();
			mProduct.name = in.readString();
			mProduct.description = in.readString();
			mProduct.image = in.readString();*/
			return new Product(in);
		}

		public Product[] newArray(int size) {
			return new Product[size];
		}
	};

	public String getSaleDescription() {
		return saleDescription;
	}

	public void setSaleDescription(String saleDescription) {
		this.saleDescription = saleDescription;
	}

}

