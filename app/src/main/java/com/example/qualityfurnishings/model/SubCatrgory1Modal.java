package com.example.qualityfurnishings.model;

public class SubCatrgory1Modal {

public SubCatrgory1Modal(){

}
    public SubCatrgory1Modal(String image, String name, String description, String quality, int quantity, int price, boolean sale,String discount,String id) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.quality = quality;
        this.quantity = quantity;
        this.price = price;
        this.sale = sale;
        this.discount=discount;
        Id = id;

    }

    public String image , name,description,quality;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }



    public  String Id,discount;

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
