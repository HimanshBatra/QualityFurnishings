package com.example.qualityfurnishings.model;

public class SubCatrgory1Modal {
    public SubCatrgory1Modal(String imageView, String itemName) {
        this.image = imageView;
        name = itemName;
    }

     public SubCatrgory1Modal(){

    }

    public String getImageView() {
        return image;
    }

    public void setImageView(String imageView) {
        this.image = imageView;
    }

    public String getItemName() {
        return name;
    }

    public void setItemName(String itemName) {
        name = itemName;
    }

     public String image , name;

}
