package com.home_rental.home_rental_management.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.sql.Time;
import java.sql.Timestamp;

public class Util {
    public static boolean isJwtTokenExpired (Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_session",Context.MODE_PRIVATE);
        if (sharedPreferences.getString("session","").equals("true")) {
            String expiryTimeInMillisecond = sharedPreferences.getString("exp","");
            Timestamp expTimeStamp = new Timestamp(Integer.valueOf(expiryTimeInMillisecond));
            Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());

            if (expTimeStamp.before(currentTimeStamp)) {
                return true;
            }

        }
        return false;
    }
}
