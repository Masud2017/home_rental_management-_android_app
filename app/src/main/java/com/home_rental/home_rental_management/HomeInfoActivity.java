package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.camera.core.processing.SurfaceProcessorNode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.HomeModelResponse;
import com.home_rental.home_rental_management.services.Api;

import java.util.concurrent.ExecutionException;

public class HomeInfoActivity extends AppCompatActivity {
    private TextView homeInfoHeader = null;
    private TextView homeInfoDesc = null;
    private TextView homeInfoPrice = null;
    private AppCompatButton buyButton = null;
    private AppCompatButton deleteBtn = null;
    private TextView homeInfoFlatCount = null;
    private ImageView homeInfoImg = null;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_info);

        Api api = new Api();

        this.homeInfoHeader = findViewById(R.id.home_info_header);
        this.homeInfoDesc = findViewById(R.id.home_info_desc);
        this.homeInfoPrice = findViewById(R.id.home_info_price);
        this.buyButton = findViewById(R.id.home_buy_btn);
        this.deleteBtn = findViewById(R.id.home_delete_btn);
        this.homeInfoFlatCount = findViewById(R.id.home_info_flat_count);
        this.homeInfoImg = findViewById(R.id.home_info_img);

        Bundle extras = getIntent().getExtras();
        String home_id = extras.getString("home_id");

        Api.HomeById homeByIdAsyncCall = api.new HomeById().setHomeId(home_id);
        try {
            HomeModelResponse homeModelResponse = homeByIdAsyncCall.execute().get();


            String profileImageBase64String = homeModelResponse.getImage();
            System.out.println(profileImageBase64String);
            byte[] imgByteArray = Base64.decode(profileImageBase64String,Base64.DEFAULT);
            this.homeInfoImg.setImageBitmap(BitmapFactory.decodeByteArray(imgByteArray,0,imgByteArray.length));

            this.homeInfoHeader.setText(homeModelResponse.getName());
            this.homeInfoDesc.setText(homeModelResponse.getDesc());
            System.out.println(homeModelResponse.getPrice());
            this.homeInfoPrice.setText(homeModelResponse.getPrice() + " BDT");
            this.homeInfoFlatCount.setText("Flat count : "+homeModelResponse.getFlat_count());


            SharedPreferences sharedPreferences = getSharedPreferences("user_session",MODE_PRIVATE);
            System.out.println("Printing the role of the user : "+sharedPreferences.getString("role",""));
            if (sharedPreferences.getString("role","").equals("user")) {
                this.buyButton.setVisibility(View.VISIBLE);

                this.buyButton.setOnClickListener(view2 -> {
                    Api.BuyHome buyHomeAsyncTask = api.new BuyHome().setContext(this).setHomeId(home_id);
                    try {
                        buyHomeAsyncTask.execute().get();
                        Intent afterBuyHome = new Intent(this,HomeActivity.class);
                        startActivity(afterBuyHome);
                        finish();
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                });


            }

            Bundle intentInfo = getIntent().getExtras();
            if (intentInfo.get("seller")!= null ) {
                if (((String)intentInfo.get("seller")).equals("true")){
                    this.deleteBtn.setVisibility(View.VISIBLE);
                    this.deleteBtn.setOnClickListener(view2 -> {
                        Api.DeleteHomeById deleteHomeByIdAsyncTask = api.new DeleteHomeById().setHomeId(home_id).setContext(this);
                        try {
                            deleteHomeByIdAsyncTask.execute().get();
                            Intent intent = new Intent(this,HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // pulling that already running activity instead of creating a new one to the activity stack
                            startActivity(intent);
                            finish();
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }

            }

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}