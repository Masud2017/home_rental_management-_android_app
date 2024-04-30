package com.home_rental.home_rental_management.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.home_rental.home_rental_management.Models.HomeModelResponse;
import com.home_rental.home_rental_management.R;
import com.home_rental.home_rental_management.services.AsyncApiCall;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardListAdapter extends BaseAdapter {
    private List<HomeModelResponse> homeModelResponseList;
    private LayoutInflater layoutInflater = null;

    @SuppressWarnings("deprecation")
    public  CardListAdapter(Context ctx) throws ExecutionException, InterruptedException {
        this.homeModelResponseList = new AsyncApiCall().execute("").get();
        this.layoutInflater = LayoutInflater.from(ctx);
    }
    @Override
    public int getCount() {
        return this.homeModelResponseList.size();
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
        view = this.layoutInflater.inflate(R.layout.activity_custom_card_view,null);

        return view;
    }
}
