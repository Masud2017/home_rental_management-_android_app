package com.home_rental.home_rental_management.services;

import android.os.AsyncTask;

import com.home_rental.home_rental_management.Models.HomeModelResponse;

import java.io.IOException;
import java.util.List;

public class AsyncApiCall extends AsyncTask<String,Void, List<HomeModelResponse>> {
    @Override
    @SuppressWarnings("deprecation")
    protected List<HomeModelResponse> doInBackground(String... strings) {
        Api api = new Api();
        try {
            return api.getHomeList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
