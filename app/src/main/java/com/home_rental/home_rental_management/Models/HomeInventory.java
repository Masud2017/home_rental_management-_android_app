package com.home_rental.home_rental_management.Models;

import java.util.Date;

public class HomeInventory {
    private Integer id;
//    private String payment_date;
    private Integer flat_count;
    private String image;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

//    public String getPayment_date() {
//        return payment_date;
//    }
//
//    public void setPayment_date(String payment_date) {
//        this.payment_date = payment_date;
//    }

    public Integer getFlat_count() {
        return flat_count;
    }

    public void setFlat_count(Integer flat_count) {
        this.flat_count = flat_count;
    }
}
