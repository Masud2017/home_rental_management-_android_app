package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.home_rental.home_rental_management.Models.WalletHistory;
import com.home_rental.home_rental_management.Models.WalletInfo;
import com.home_rental.home_rental_management.services.Api;

import java.util.concurrent.ExecutionException;

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
               platFormListItem = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        this.rechargeBtn.setOnClickListener(view2 -> {
            String rechargeAmountStr = this.rechargeAmount.getText().toString();
            Api api = new Api();
            Api.UpdateWalletAsyncTask updateWalletAsyncTask = null;
            if (this.platFormListItem == 0) {
                updateWalletAsyncTask = api.new UpdateWalletAsyncTask().setContext(this).setRechargeAmount(rechargeAmountStr).setPlatFormName("bkash");
            } else if (this.platFormListItem == 1) {
                updateWalletAsyncTask = api.new UpdateWalletAsyncTask().setContext(this).setRechargeAmount(rechargeAmountStr).setPlatFormName("nagad");
            } else if (this.platFormListItem == 2) {
                updateWalletAsyncTask = api.new UpdateWalletAsyncTask().setContext(this).setRechargeAmount(rechargeAmountStr).setPlatFormName("dbl");
            }

            try {
                updateWalletAsyncTask.execute().get();

                SharedPreferences sharedPreferences = getSharedPreferences("user_session",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                Api.GetWalletInfo getWalletInfo = api.new GetWalletInfo().setContext(this);
                WalletInfo walletInfo = getWalletInfo.execute().get();


                editor.putString("balance",walletInfo.getBalance() + "");
                editor.commit();

                WalletHistory walletHistory = new WalletHistory();
                walletHistory.setMsg("Recharged " +rechargeAmountStr);
                walletHistory.setPayment_amount(rechargeAmountStr);
                if (platFormListItem == 0) {
                    walletHistory.setPayment_platform("bkash");
                } else if (platFormListItem == 1) {
                    walletHistory.setPayment_platform("nagad");
                } else if (platFormListItem == 2) {
                    walletHistory.setPayment_platform("dbl");
                }

                Api.AddWalletHistoryAsyncTask addWalletHistoryAsyncTask = api.new AddWalletHistoryAsyncTask().setWalletHistory(walletHistory).setContext(this);
                addWalletHistoryAsyncTask.execute().get();

                Intent intent = new Intent(this,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }




}