package com.home_rental.home_rental_management.Models;

import android.widget.ImageView;

import lombok.Getter;

public class Home {
    private String name;
    private String desc;
    private Integer price;
    private String address;
    private String flat_count;
    private ImageView homeImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFlat_count() {
        return flat_count;
    }

    public void setFlat_count(String flat_count) {
        this.flat_count = flat_count;
    }

    public ImageView getHomeImage() {
        return homeImage;
    }

    public void setHomeImage(ImageView homeImage) {
        this.homeImage = homeImage;
    }
}
