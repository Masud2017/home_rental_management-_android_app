package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class WalletActivity extends AppCompatActivity {
    private Button rechargeWalletNavBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        this.rechargeWalletNavBtn = findViewById(R.id.recharge_wallet_nav_btn);

        this.rechargeWalletNavBtn.setOnClickListener(view2 -> {
            Intent intent = new Intent(this, WalletRechargeActivity.class);
            startActivity(intent);
        });
    }
}