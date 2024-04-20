package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();  // optional depending on your needs
    }
}