package com.example.qualityfurnishings.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModal implements Parcelable {

public ProductModal(){

}
    public ProductModal(String image, String name, String description, String quality, int quantity, int price, boolean sale, Integer discount, String id, String category, String subCategory) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.quality = quality;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
        this.discount=discount;
        this.id = id;
        this.category = category;
        this.subCategory = subCategory;

    }

    public String image , name,description,quality;

    protected ProductModal(Parcel in) {
        image = in.readString();
        name = in.readString();
        description = in.readString();
        quality = in.readString();
        id = in.readString();
        discount = in.readInt();
        category = in.readString();
        subCategory = in.readString();
        quantity = in.readInt();
        price = in.readInt();
        sale = in.readByte() != 0;
    }

    public static final Creator<ProductModal> CREATOR = new Creator<ProductModal>() {
        @Override
        public ProductModal createFromParcel(Parcel in) {
            return new ProductModal(in);
        }

        @Override
        public ProductModal[] newArray(int size) {
            return new ProductModal[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }



    public  String id;
    public  int discount;
    public String category;



    public String subCategory;

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

    int quantity,price;
     boolean sale;

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
        dest.writeString(id);
        dest.writeInt(discount);
        dest.writeString(category);
        dest.writeString(subCategory);
        dest.writeInt(quantity);
        dest.writeInt(price);
        dest.writeByte((byte) (sale ? 1 : 0));
    }
}
