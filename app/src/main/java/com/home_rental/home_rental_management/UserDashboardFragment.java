package com.home_rental.home_rental_management;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.MyProfile;
import com.home_rental.home_rental_management.adapters.CardListAdapter;
import com.home_rental.home_rental_management.adapters.InventoryListAdapter;
import com.home_rental.home_rental_management.services.Api;

import java.util.concurrent.ExecutionException;


public class UserDashboardFragment extends Fragment {
    private AppCompatImageView profileImage = null;
    private TextView profileName = null;
    private GridView inventoryList = null;

    private AppCompatImageView imageView = null;
    public UserDashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_dashboard, container, false);
    }


    @Override
    @SuppressWarnings("deprecation")
    public void onViewCreated (@NonNull View view, @NonNull Bundle savedInstanceData) {
        super.onViewCreated(view,savedInstanceData);


        this.profileImage = view.findViewById(R.id.rd_img);
        this.profileName = view.findViewById(R.id.rd_name);
        this.inventoryList = view.findViewById(R.id.user_inventory_list);

        try {
            InventoryListAdapter inventoryListAdapter = new InventoryListAdapter(getContext());

            this.inventoryList.setAdapter(inventoryListAdapter);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }






        Api api = new Api();
        Api.MyProfileAsyncTask myProfileAsyncTask = api.new MyProfileAsyncTask();
        myProfileAsyncTask.setContext(UserDashboardFragment.this.getContext());


        try {
            MyProfile myProfile = myProfileAsyncTask.execute().get();
            System.out.println("Profile name is : "+myProfile.getName());
            this.profileName.setText(myProfile.getName());
            String profileImageBase64String = myProfile.getImage();
            byte[] imgByteArray = Base64.decode(profileImageBase64String,Base64.DEFAULT);
            this.profileImage.setImageBitmap(BitmapFactory.decodeByteArray(imgByteArray,0,imgByteArray.length));


        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

}