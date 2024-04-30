package com.home_rental.home_rental_management.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.home_rental.home_rental_management.Models.Home;
import com.home_rental.home_rental_management.Models.HomeModelResponse;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Api {
    private Integer port = 4444;
    private String baseUrl = "https://7c7e-103-112-236-87.ngrok-free.app";
    private OkHttpClient client = null;

    public Api() {
        this.client = new OkHttpClient();
    }

    public List<HomeModelResponse> getHomeList() throws IOException {
        Request req = new Request.Builder()
                .url(this.baseUrl + "/gethomes")
                .build();

        Response response = this.client.newCall(req).execute();
        ResponseBody responseBody = response.body();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        String jsonString = responseBody.string();

        Type listType = new TypeToken<List<HomeModelResponse>>() {}.getType();


        List<HomeModelResponse> homeModelResponseList = gson.fromJson(jsonString, listType);


        return homeModelResponseList;
    }
}
