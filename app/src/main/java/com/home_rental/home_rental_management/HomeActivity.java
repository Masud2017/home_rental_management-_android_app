package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.home_rental.home_rental_management.Models.Home;

public class HomeActivity extends AppCompatActivity {
    private AppCompatButton loginBtn = null;
    private AppCompatButton signupButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.loginBtn = findViewById(R.id.login);
        this.signupButton = findViewById(R.id.signup);

        this.loginBtn.setOnClickListener(view -> {
            Intent loginIntent = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });

        this.signupButton.setOnClickListener((View view)-> {
            Intent intent = new Intent(HomeActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });
    }
}