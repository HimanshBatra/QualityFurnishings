package com.example.qualityfurnishings.model;

public class SubCatrgory1Modal  {

public SubCatrgory1Modal(){

}
    public SubCatrgory1Modal(String image, String name, String description, String quality, int quantity, int price, boolean sale,Integer discount,String id,String category, String subCategory) {
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

}
