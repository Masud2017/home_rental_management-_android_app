package com.home_rental.home_rental_management.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.TransactionHistory;
import com.home_rental.home_rental_management.Models.WalletHistory;
import com.home_rental.home_rental_management.R;
import com.home_rental.home_rental_management.RechargeHistoryActivity;
import com.home_rental.home_rental_management.services.Api;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RechargeHistoryListAdapter extends BaseAdapter {
    private Context context;
    private List<WalletHistory> rechargeHistoryList = null;
    private LayoutInflater layoutInflater = null;

    public RechargeHistoryListAdapter(Context context) throws ExecutionException, InterruptedException {
        this.layoutInflater = LayoutInflater.from(context);
        Api api = new Api();

        Api.GetRechargeHistoryList getRechargeHistoryList = api.new GetRechargeHistoryList().setContext(context);

        this.rechargeHistoryList = getRechargeHistoryList.execute().get();
        this.context = context;
    }
    @Override
    public int getCount() {
        return this.rechargeHistoryList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        WalletHistory walletHistoryItem = this.rechargeHistoryList.get(i);
        view = this.layoutInflater.inflate(R.layout.activity_custom_transaction_list,null);
        TextView textView = view.findViewById(R.id.transaction_list_item);
        textView.setText(walletHistoryItem.getMsg());
        return view;
    }
}
