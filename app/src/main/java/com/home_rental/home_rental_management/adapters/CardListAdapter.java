package com.home_rental.home_rental_management.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import com.home_rental.home_rental_management.HomeInfoActivity;
import com.home_rental.home_rental_management.Models.HomeModelResponse;
import com.home_rental.home_rental_management.R;
import com.home_rental.home_rental_management.services.Api;
import com.home_rental.home_rental_management.services.AsyncApiCall;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CardListAdapter extends BaseAdapter implements View.OnClickListener {
    private Boolean forUser = false;
    private Boolean forInven = false;
    private Boolean forSeller = false;
    private List<HomeModelResponse> homeModelResponseList;
    private LayoutInflater layoutInflater = null;

    public CardListAdapter setUserFlag(Boolean forUser){
        this.forUser = forUser;
        return this;
    }

    public CardListAdapter setForSeller(Boolean forSeller){
        this.forSeller = forSeller;
        return this;
    }

    public CardListAdapter setForInven(Boolean forInven){
        this.forInven = forInven;
        return this;
    }

    @SuppressWarnings("deprecation")
    public  CardListAdapter(Context ctx) throws ExecutionException, InterruptedException {
        if (this.forUser == false) {
            this.homeModelResponseList = new AsyncApiCall().execute("").get();

        } else if (this.forUser && this.forInven == false && forSeller) {
            Api api = new Api();
            Api.MyHomeListAsyncTask homeListAsyncTask = api.new MyHomeListAsyncTask().setContext(ctx);
            this.homeModelResponseList = homeListAsyncTask.execute().get();

        } else if (this.forUser && this.forInven) {

        }
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

        AppCompatImageView cardImage = view.findViewById(R.id.card_image);
        String profileImageBase64String = homeModelResponseItem.getImage();
        byte[] imgByteArray = Base64.decode(profileImageBase64String,Base64.DEFAULT);
        cardImage.setImageBitmap(BitmapFactory.decodeByteArray(imgByteArray,0,imgByteArray.length));


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
        if (forSeller) {
            intent.putExtra("seller", "true");
        }

        view.getContext().startActivity(intent);
    }
}
