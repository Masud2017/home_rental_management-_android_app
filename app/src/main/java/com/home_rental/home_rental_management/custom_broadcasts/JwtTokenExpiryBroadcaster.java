package com.home_rental.home_rental_management.custom_broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.home_rental.home_rental_management.HomeActivity;

class JwtTokenExpiryBroadcaster extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("session_user",Context.MODE_PRIVATE);

        if (intent.getAction().equals("JWT_REMOVE")) {
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
            sharedPreferencesEditor.remove("session");
            sharedPreferencesEditor.remove("access_token");
            sharedPreferencesEditor.remove("email");
            sharedPreferencesEditor.commit();


            Intent homeActivityIntent = new Intent(context, HomeActivity.class);
            homeActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(homeActivityIntent);
        }
    }
}