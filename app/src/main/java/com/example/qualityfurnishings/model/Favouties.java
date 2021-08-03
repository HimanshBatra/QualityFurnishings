package com.example.qualityfurnishings.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Favouties implements Parcelable {

public Favouties(){

}

    public Favouties(String image, String name, String description, String quality, int quantity, int price, boolean sale, Integer discount, String id, String category, String subCategory, String userid, boolean favourity) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.quality = quality;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
        this.discount = discount;
        this.id = id;
        this.category = category;
        this.subCategory = subCategory;
        this.userid = userid;
        this.favourity = favourity;
    }

    protected Favouties(Parcel in) {
        image = in.readString();
        name = in.readString();
        description = in.readString();
        quality = in.readString();
        quantity = in.readInt();
        price = in.readInt();
        sale = in.readByte() != 0;
        if (in.readByte() == 0) {
            discount = null;
        } else {
            discount = in.readInt();
        }
        id = in.readString();
        category = in.readString();
        subCategory = in.readString();
        userid = in.readString();
        favourity = in.readByte() != 0;
    }

    public static final Creator<Favouties> CREATOR = new Creator<Favouties>() {
        @Override
        public Favouties createFromParcel(Parcel in) {
            return new Favouties(in);
        }

        @Override
        public Favouties[] newArray(int size) {
            return new Favouties[size];
        }
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isFavourity() {
        return favourity;
    }

    public void setFavourity(boolean favourity) {
        this.favourity = favourity;
    }

    String image;
    String name;
    String description;
    String quality;
    int quantity;
    int price;
    boolean sale;
    Integer discount;
    String id;
    String category;
    String subCategory;
    String userid;
    boolean favourity;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(quality);
        dest.writeInt(quantity);
        dest.writeInt(price);
        dest.writeByte((byte) (sale ? 1 : 0));
        if (discount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(discount);
        }
        dest.writeString(id);
        dest.writeString(category);
        dest.writeString(subCategory);
        dest.writeString(userid);
        dest.writeByte((byte) (favourity ? 1 : 0));
    }
}
