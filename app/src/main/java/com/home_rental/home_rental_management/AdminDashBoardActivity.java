package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;

public class AdminDashBoardActivity extends AppCompatActivity {
    private AppCompatImageView addHomeButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);

        this.addHomeButton = findViewById(R.id.add_home_btn);

        this.addHomeButton.setOnClickListener(view -> {
            Intent openAddHomeActivity = new Intent(AdminDashBoardActivity.this, AddHomeActivity.class);
            startActivity(openAddHomeActivity);
        });
    }
}