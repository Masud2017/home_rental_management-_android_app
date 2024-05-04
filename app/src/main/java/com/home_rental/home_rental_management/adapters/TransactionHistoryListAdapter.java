package com.home_rental.home_rental_management.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.TransactionHistory;
import com.home_rental.home_rental_management.R;
import com.home_rental.home_rental_management.services.Api;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("deprecation")
public class TransactionHistoryListAdapter extends BaseAdapter {
    private Context context;
    private List<TransactionHistory> transactionHistoryList = null;
    private LayoutInflater layoutInflater = null;
    public TransactionHistoryListAdapter(Context context) throws ExecutionException, InterruptedException {
        this.layoutInflater = LayoutInflater.from(context);
        Api api = new Api();
        Api.GetTransactionListAsyncTask getTransactionListAsyncTask = api.new GetTransactionListAsyncTask().setContext(context);

        this.transactionHistoryList = getTransactionListAsyncTask.execute().get();
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.transactionHistoryList.size();
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
        TransactionHistory transactionHistoryItem = this.transactionHistoryList.get(i);

        view = this.layoutInflater.inflate(R.layout.activity_custom_transaction_list,null);
        TextView textView = view.findViewById(R.id.transaction_list_item);
        textView.setText(transactionHistoryItem.getMsg());

        return view;
    }
}
