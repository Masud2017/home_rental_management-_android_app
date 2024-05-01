package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.home_rental.home_rental_management.utils.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // checking whether the jwt token is expired or not
        // if  the token is expired then it will send the broad cast event to the broadcast
        // receiver.
        if (Util.isJwtTokenExpired(this)) {
            Intent jwtRemoveBroadcastInokerIntent = new Intent("JWT_REMOVE");
            sendBroadcast(jwtRemoveBroadcastInokerIntent);

            Intent refreshIntent = new Intent(this,MainActivity.class);
            startActivity(refreshIntent);
            finish();
        }


        Handler handler = new Handler();

        handler.postDelayed((Runnable) () -> {
            Intent intent = null;
                intent = new Intent(this, HomeActivity.class);

                startActivity(intent);
                finish();

        },1500);
    }
}