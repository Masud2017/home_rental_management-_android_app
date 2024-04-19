package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();

        handler.postDelayed((Runnable) () -> {
            Intent intent = null;
                intent = new Intent(this, HomeActivity.class);

                startActivity(intent);
                finish();

        },1500);
    }
}