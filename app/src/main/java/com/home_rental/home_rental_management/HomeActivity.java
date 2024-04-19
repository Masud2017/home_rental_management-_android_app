package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {
    private AppCompatButton loginBtn = null;
    private AppCompatButton getLoginBtn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.loginBtn = findViewById(R.id.login);

        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }
}