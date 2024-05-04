package com.home_rental.home_rental_management.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.home_rental.home_rental_management.Models.HomeInventory;
import com.home_rental.home_rental_management.R;
import com.home_rental.home_rental_management.services.Api;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("deprecation")
public class InventoryListAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater = null;
    private List<HomeInventory> homeInventoryList = null;

    public InventoryListAdapter(Context context) throws ExecutionException, InterruptedException {
        this.layoutInflater = LayoutInflater.from(context);
        Api api = new Api();
        Api.HomeInventoryList homeInventoryListResponse = api.new HomeInventoryList().setContext(context);
        this.homeInventoryList = homeInventoryListResponse.execute().get();
    }

    @Override
    public int getCount() {
        return this.homeInventoryList.size();
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
        HomeInventory homeInventoryItem = this.homeInventoryList.get(i);
        view = this.layoutInflater.inflate(R.layout.activity_custom_inventory_list,null);
        AppCompatImageView img = view.findViewById(R.id.inven_img);
        TextView name = view.findViewById(R.id.inven_name);
        TextView flatcount = view.findViewById(R.id.inven_flat_count);
        Button cancelBtn = view.findViewById(R.id.inven_cancel_btn);

        String profileImageBase64String = homeInventoryItem.getImage();
        byte[] imgByteArray = Base64.decode(profileImageBase64String,Base64.DEFAULT);
        img.setImageBitmap(BitmapFactory.decodeByteArray(imgByteArray,0,imgByteArray.length));

        name.setText(homeInventoryItem.getName());
        flatcount.setText("Flat count : "+homeInventoryItem.getFlat_count());
        cancelBtn.setOnClickListener(view2 -> {
            //
        });

        return view;
    }
}
