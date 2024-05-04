package com.home_rental.home_rental_management.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HomeModelResponse {
    @Getter
    private long id;
    private String name;
    private String desc;
    private Integer price;
    private String address;
    private String flat_count;
    private Boolean is_soled;
    private String image;



    public long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Boolean getIs_soled() {
        return is_soled;
    }

    public void setIs_soled(Boolean is_soled) {
        this.is_soled = is_soled;
    }
}
