package com.home_rental.home_rental_management.services;

import com.home_rental.home_rental_management.Models.Home;

import java.util.List;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SearchService {
    private StringBuffer strBuffer;

    public SearchService() {
        this.strBuffer = new StringBuffer();
    }

    public List<Home> performSearch() {
        return null;
    }

    public void addSearchString(String string) {
        this.strBuffer.append(string);
        if (this.performSearch() == null) {

        } else {
            //
        }
    }
}
