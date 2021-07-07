package com.example.qualityfurnishings.model;

public class Cart {

    public Cart(){

    }


    public Cart(String productName, String image, String category, String subcategory, int quantity, int finalPrice) {
        this.productName = productName;
        this.image = image;
        this.category = category;
        this.subcategory = subcategory;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }

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

    public String productName,image,category,subcategory;
    public int quantity,finalPrice;


}
