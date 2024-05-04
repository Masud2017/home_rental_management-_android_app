package com.home_rental.home_rental_management.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import androidx.annotation.AnyThread;
import androidx.camera.core.internal.ByteBufferOutputStream;
import androidx.room.Delete;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.home_rental.home_rental_management.Models.Home;
import com.home_rental.home_rental_management.Models.HomeModelResponse;
import com.home_rental.home_rental_management.Models.MyProfile;
import com.home_rental.home_rental_management.Models.Role;
import com.home_rental.home_rental_management.Models.User;
import com.home_rental.home_rental_management.Models.UserAuthResponse;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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


    public class HomeById extends AsyncTask<String,Void,HomeModelResponse> {
        private String homeId;

        public HomeById setHomeId(String homeId) {
            this.homeId = homeId;
            return this;
        }

        @Override
        @SuppressWarnings("deprecation")
        protected HomeModelResponse doInBackground(String... strings) {
            Request req = new Request.Builder()
                    .url(baseUrl + "/gethome/"+this.homeId)
                    .build();

            Response response = null;
            try {
                response = client.newCall(req).execute();
                ResponseBody responseBody = response.body();
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();

                String jsonString = responseBody.string();

                HomeModelResponse homeModelResponse = gson.fromJson(jsonString, HomeModelResponse.class);

                return homeModelResponse;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class DeleteHomeById extends AsyncTask<String,Void,Void> {
        private Context context;
        private String homeId;

        public DeleteHomeById setHomeId(String homeId) {
            this.homeId = homeId;
            return this;
        }

        public DeleteHomeById setContext(Context context) {
            this.context = context;
            return this;
        }

        @Override
        @SuppressWarnings("deprecation")
        protected Void doInBackground(String... strings) {
            SharedPreferences sharedPreferences = this.context.getSharedPreferences("user_session",Context.MODE_PRIVATE);
            String jwtToken = sharedPreferences.getString("access_token","");

            Request req = new Request.Builder()
                    .delete()
                    .url(baseUrl + "/deletehome/"+this.homeId)
                    .header("Authorization","Bearer "+jwtToken)
                    .build();

            try {
                client.newCall(req).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    public class MyRoleAsyncTask extends AsyncTask<String, Void, Role> {
        private Context context;

        public MyRoleAsyncTask setContext(Context context) {
            this.context = context;
            return this;
        }

        /**
         * This method should be invoked only after the user got the jwt token otherwise it won't work.
         * */
        @Override
        @SuppressWarnings("deprecation")
        protected Role doInBackground(String... strings) {
            SharedPreferences sharedPreferences = this.context.getSharedPreferences("user_session",Context.MODE_PRIVATE);
            String jwtToken = sharedPreferences.getString("access_token","");

            Request req = new Request.Builder()
                    .url(baseUrl + "/getmyrole")
                    .header("Authorization","Bearer "+jwtToken)
                    .build();

            try {
                Response res = client.newCall(req).execute();
                String responseJson = res.body().string();
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setPrettyPrinting();
                Gson gson = gsonBuilder.create();
                Role role = gson.fromJson(responseJson,Role.class);

                System.out.println("Printing from Api : role : "+role.getRole());
                System.out.println("and json Printing from Api : role : "+responseJson);
                System.out.println("and jwt token Printing from Api : role : "+jwtToken);

                return role;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressWarnings("deprecation")
    public class SignupUserAsyncTask extends AsyncTask<String, Void, Void> {
        private User user = null;
        private String role = null;

        public SignupUserAsyncTask setUser(User user) {
            this.user = user;
            return this;
        }

        public SignupUserAsyncTask setRole(String role){
            this.role = role;
            return this;
        }

        @Override
        protected Void doInBackground(String... strings) {
            MediaType JSON = MediaType.get("application/json");
            Gson gson = new Gson();

            RequestBody body = RequestBody.create(JSON,gson.toJson(this.user));
            Request req = null;

            switch (this.role) {
                case "user":
                    req = new Request.Builder()
                        .post(body)
                        .url(baseUrl + "/adduser")
                        .build();
                    break;

                case "seller":
                    req = new Request.Builder()
                        .post(body)
                        .url(baseUrl + "/addselleruser")
                        .build();
                    break;
                case "root":
                    req = new Request.Builder()
                        .post(body)
                        .url(baseUrl + "/addrootuser")
                        .build();
                    break;
            }

            try {
                client.newCall(req).execute();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public class AddProfileImageAsyncTask extends AsyncTask<String, Void,Void> {
        private ImageView imageView;
        private String email;

        public AddProfileImageAsyncTask setEmail(String email) {
            this.email = email;
            return this;
        }

        public AddProfileImageAsyncTask setImageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }


        @Override
        protected Void doInBackground(String... strings) {
            ByteArrayOutputStream barrout = new ByteArrayOutputStream();
            Bitmap image = ((BitmapDrawable)this.imageView.getDrawable()).getBitmap();
            image.compress(Bitmap.CompressFormat.JPEG,100,barrout);

            byte[] byteArray = barrout.toByteArray();

            Random random = new Random();
            String filename = String.valueOf(random.nextInt());


            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image",filename,RequestBody.create(MediaType.parse("image/*"),byteArray))
                    .addFormDataPart("email",this.email)
                    .build();

            Request request = new Request.Builder()
                    .post(requestBody)
                    .url(baseUrl + "/upload_profile_pic")
                    .build();

            try {
                client.newCall(request).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return null;
        }
    }

    @SuppressWarnings("deprecation")
    // need to change
    public class MyProfileAsyncTask extends AsyncTask<String,Void, MyProfile> {
        private Context context;

        public MyProfileAsyncTask setContext(Context context) {
            this.context = context;
            return this;
        }

        @Override
        protected MyProfile doInBackground(String... strings) {
            SharedPreferences sharedPreferences = this.context.getSharedPreferences("user_session",Context.MODE_PRIVATE);
            String access_token = sharedPreferences.getString("access_token","");

            Request request = new Request.Builder()
                    .header("Authorization","Bearer "+access_token)
                    .url(baseUrl + "/myprofile")
                    .build();

            try {
                Response res = client.newCall(request).execute();
                String resStr = res.body().string();

                Gson gson = new Gson();
                System.out.println("Printing the value of profile : "+resStr);
                MyProfile myProfile = gson.fromJson(resStr,MyProfile.class);

                return myProfile;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @SuppressWarnings("deprecation")
    public class UpdateWalletAsyncTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            return null;
        }
    }

    @SuppressWarnings("deprecation")
    public class AddRechargeHistoryAsyncTask extends  AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            return null;
        }
    }
}
