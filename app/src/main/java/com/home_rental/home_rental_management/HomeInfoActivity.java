package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.HomeModelResponse;
import com.home_rental.home_rental_management.services.Api;

import java.util.concurrent.ExecutionException;

public class HomeInfoActivity extends AppCompatActivity {
    private TextView homeInfoHeader = null;
    private TextView homeInfoDesc = null;
    private TextView homeInfoPrice = null;
    private AppCompatButton deleteButton = null;
    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_info);

        Api api = new Api();

        this.homeInfoHeader = findViewById(R.id.home_info_header);
        this.homeInfoDesc = findViewById(R.id.home_info_desc);
        this.homeInfoPrice = findViewById(R.id.home_info_price);
        this.deleteButton = findViewById(R.id.home_delete_btn);

        Bundle extras = getIntent().getExtras();
        String home_id = extras.getString("home_id");

        Api.HomeById homeByIdAsyncCall = api.new HomeById().setHomeId(home_id);
        try {
            HomeModelResponse homeModelResponse = homeByIdAsyncCall.execute().get();

            this.homeInfoHeader.setText(homeModelResponse.getName());
            this.homeInfoDesc.setText(homeModelResponse.getDesc());
//            this.homeInfoPrice.setText(homeModelResponse.getPrice().toString());


            this.deleteButton.setOnClickListener(view2 -> {
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

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}