package com.example.qualityfurnishings.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {

    public Cart(){

    }




    public Cart(String productName, String image, String category, String subcategory, int quantity, int finalPrice,String id,String userid,int productPrice,int itemCount) {
        this.productName = productName;
        this.image = image;
        this.category = category;
        this.subcategory = subcategory;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
        this.id=id;
        this.userid = userid;
        this.productPrice=productPrice;
        this.itemCount=itemCount;
    }

    protected Cart(Parcel in) {
        productName = in.readString();
        image = in.readString();
        category = in.readString();
        subcategory = in.readString();
        if (in.readByte() == 0) {
            productPrice = null;
        } else {
            productPrice = in.readInt();
        }
        userid = in.readString();
        id = in.readString();
        quantity = in.readInt();
        finalPrice = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String productName;
    public String image;
    public String category;
    public String subcategory;

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer productPrice;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }



    public String userid;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String id;








    public int quantity,finalPrice;

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int itemCount;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeString(image);
        dest.writeString(category);
        dest.writeString(subcategory);
        if (productPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(productPrice);
        }
        dest.writeString(userid);
        dest.writeString(id);
        dest.writeInt(quantity);
        dest.writeInt(finalPrice);
    }
}
