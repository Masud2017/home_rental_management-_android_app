package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class WalletRechargeActivity extends AppCompatActivity {
    private Spinner platFormList = null;
    private Integer platFormListItem = 0;
    private EditText rechargeAmount = null;
    private Button rechargeBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_recharge);
        this.platFormList = findViewById(R.id.platform_list);
        this.rechargeAmount = findViewById(R.id.recharge_amount);
        this.rechargeBtn = findViewById(R.id.recharge_wallet_btn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.recharge_platform,
                android.R.layout.simple_spinner_item
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        this.platFormList.setAdapter(adapter);

        this.platFormList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                WalletRechargeActivity.this.platFormListItem = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });
        String rechargeAmountStr = this.rechargeAmount.getText().toString();
        this.rechargeBtn.setOnClickListener(view2 -> {
            // send the http request
        });
    }




}