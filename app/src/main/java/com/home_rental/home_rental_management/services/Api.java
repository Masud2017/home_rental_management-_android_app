package com.home_rental.home_rental_management.services;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.home_rental.home_rental_management.Models.Home;
import com.home_rental.home_rental_management.Models.HomeModelResponse;
import com.home_rental.home_rental_management.Models.User;
import com.home_rental.home_rental_management.Models.UserAuthResponse;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Api {
    private Integer port = 4444;
    private String baseUrl = "https://9494-103-112-236-90.ngrok-free.app";
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

    public class AuthenticationTask extends AsyncTask<String,Void, UserAuthResponse> {
        private User user;

        public AuthenticationTask setCredential(User user) {
            this.user = user;
            return this;
        }

        @Override
        @SuppressWarnings("deprecation")
        protected UserAuthResponse doInBackground(String... strings) {
            MediaType JSON = MediaType.get("application/json");

            Gson gson = new Gson();
            String userJson = gson.toJson(this.user);

            RequestBody body = RequestBody.create(userJson, JSON);

            Request req = new Request.Builder()
                    .url(baseUrl + "/authenticate")
                    .post(body)
                    .build();

            try {
                Response res = client.newCall(req).execute();
                String resBody = res.body().string();
                UserAuthResponse userAuthResponse = gson.fromJson(resBody, UserAuthResponse.class);

                System.out.println("Printing the auth token : "+resBody);
                return userAuthResponse;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
