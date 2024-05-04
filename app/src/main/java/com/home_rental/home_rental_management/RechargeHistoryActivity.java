package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.home_rental.home_rental_management.adapters.RechargeHistoryListAdapter;

import java.util.concurrent.ExecutionException;

public class RechargeHistoryActivity extends AppCompatActivity {
    private GridView rechargeWalletHistoryGridView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_history);
        this.rechargeWalletHistoryGridView = findViewById(R.id.reharge_history_grid);
        try {
            RechargeHistoryListAdapter adapter = new RechargeHistoryListAdapter(this);
            this.rechargeWalletHistoryGridView.setAdapter(adapter);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}