package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class WalletActivity extends AppCompatActivity {
    private Button rechargeWalletNavBtn = null;
    private TextView walletPagebalance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        this.rechargeWalletNavBtn = findViewById(R.id.recharge_wallet_nav_btn);
        this.walletPagebalance = findViewById(R.id.wallet_page_balance);

        SharedPreferences sharedPreferences = this.getSharedPreferences("user_session",MODE_PRIVATE);
        this.walletPagebalance.setText(sharedPreferences.getString("balance","") + " BDT");

        this.rechargeWalletNavBtn.setOnClickListener(view2 -> {
            Intent intent = new Intent(this, WalletRechargeActivity.class);
            startActivity(intent);
        });
    }
}