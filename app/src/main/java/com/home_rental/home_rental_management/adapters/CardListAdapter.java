package com.home_rental.home_rental_management.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.home_rental.home_rental_management.HomeInfoActivity;
import com.home_rental.home_rental_management.Models.HomeModelResponse;
import com.home_rental.home_rental_management.R;
import com.home_rental.home_rental_management.services.AsyncApiCall;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardListAdapter extends BaseAdapter implements View.OnClickListener {
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
        HomeModelResponse homeModelResponseItem = this.homeModelResponseList.get(i);
        view = this.layoutInflater.inflate(R.layout.activity_custom_card_view,null);
        view.setId(Integer.parseInt(String.valueOf(homeModelResponseItem.getId())));

        TextView cardHeader = view.findViewById(R.id.card_header);
        cardHeader.setText(homeModelResponseItem.getName());

        TextView cardPrice = view.findViewById(R.id.card_price);
        cardPrice.setText(homeModelResponseItem.getPrice().toString());

        TextView cardDesc = view.findViewById(R.id.card_desc);
        cardDesc.setText(homeModelResponseItem.getDesc());

        // setting up the on click listener
        view.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), HomeInfoActivity.class);

        System.out.println("VAlue of view id is : "+view.getId());

        intent.putExtra("home_id",String.valueOf(view.getId()));

        view.getContext().startActivity(intent);
    }
}
