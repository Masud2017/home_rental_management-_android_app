package com.home_rental.home_rental_management.custom_broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.home_rental.home_rental_management.HomeActivity;

public class JwtTokenExpiryBroadcaster extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_session",Context.MODE_PRIVATE);

        if (sharedPreferences.getString("session","").equals("true")) {
            if (intent.getAction().equals("JWT_REMOVE")) {
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                sharedPreferencesEditor.remove("session");
                sharedPreferencesEditor.remove("access_token");
                sharedPreferencesEditor.remove("email");
                sharedPreferencesEditor.remove("role");
                sharedPreferencesEditor.commit();

            }
        } else {
            System.out.println("User session is not true : ");
        }
    }
}