package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;

import com.home_rental.home_rental_management.utils.Util;

public class UserDashBoardActivity extends AppCompatActivity {
    private AppCompatImageView addHomeButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dash_board);

        // checking whether the jwt token is expired or not
        // if  the token is expired then it will send the broad cast event to the broadcast
        // receiver.
        if (Util.isJwtTokenExpired(this)) {
            Intent jwtRemoveBroadcastInokerIntent = new Intent("JWT_REMOVE");
            sendBroadcast(jwtRemoveBroadcastInokerIntent);
        }


        this.addHomeButton = findViewById(R.id.add_home_btn);

        this.addHomeButton.setOnClickListener(view -> {
            Intent openAddHomeActivity = new Intent(UserDashBoardActivity.this, AddHomeActivity.class);
            startActivity(openAddHomeActivity);
        });

    }
}