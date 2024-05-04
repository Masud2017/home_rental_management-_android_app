package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.home_rental.home_rental_management.adapters.TransactionHistoryListAdapter;

import java.util.concurrent.ExecutionException;

public class TransactionHistoryActivity extends AppCompatActivity {
    private GridView transactionHistoryGrid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        this.transactionHistoryGrid = findViewById(R.id.transaction_history_grid);

        try {
            TransactionHistoryListAdapter transactionHistoryListAdapter = new TransactionHistoryListAdapter(this);
            this.transactionHistoryGrid.setAdapter(transactionHistoryListAdapter);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}