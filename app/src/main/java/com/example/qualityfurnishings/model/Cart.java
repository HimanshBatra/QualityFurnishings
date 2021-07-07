package com.example.qualityfurnishings.model;

public class Cart {


    public Cart(String productName, String image, int finalPrice, int quantity, String category, String subcategory) {
        this.ProductName = productName;
        this.image = image;
        this.finalPrice = finalPrice;
        this.quantity = quantity;
        this.category = category;
        this.subcategory = subcategory;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
         finalPrice = finalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        quantity = quantity;
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

    String ProductName,image,category,subcategory,Uid;
    int quantity,finalPrice;


}
