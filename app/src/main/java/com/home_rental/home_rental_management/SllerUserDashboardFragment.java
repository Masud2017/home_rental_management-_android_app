package com.home_rental.home_rental_management;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.MyProfile;
import com.home_rental.home_rental_management.adapters.CardListAdapter;
import com.home_rental.home_rental_management.services.Api;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;


public class SllerUserDashboardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private AppCompatImageView profileImage = null;
    private GridView gridView = null;
    private SwipeRefreshLayout swipeRefreshLayout = null;
    private AppCompatImageView addHomeBtn = null;
    private TextView profileName = null;


    public SllerUserDashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sller_user_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance) {
        super.onViewCreated(view,savedInstance);
        this.gridView = view.findViewById(R.id.seller_user_home_list);
        this.swipeRefreshLayout = view.findViewById(R.id.seller_user_swiper);
        this.addHomeBtn = view.findViewById(R.id.add_home_btn);
        this.profileImage = view.findViewById(R.id.seller_user_profile_img);
        this.profileName = view.findViewById(R.id.seller_user_name);

        Api api = new Api();
        Api.MyProfileAsyncTask myProfileAsyncTask = api.new MyProfileAsyncTask().setContext(getContext());
        MyProfile myProfile = null;
        try {
            myProfile = myProfileAsyncTask.execute().get();
            this.profileName.setText(myProfile.getName());
            String profileImageBase64String = myProfile.getImage();
            byte[] imgByteArray = Base64.decode(profileImageBase64String,Base64.DEFAULT);
            this.profileImage.setImageBitmap(BitmapFactory.decodeByteArray(imgByteArray,0,imgByteArray.length));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }




        this.addHomeBtn.setOnClickListener(view2  -> {
            Intent intent = new Intent(SllerUserDashboardFragment.this.getContext(),AddHomeActivity.class);
            startActivity(intent);
        });

        try {
            CardListAdapter adapter = new CardListAdapter(getContext()).setUserFlag(true).setForSeller(true);
            this.gridView.setAdapter(adapter);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.swipeRefreshLayout.setOnRefreshListener(this);


    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    CardListAdapter adapter = new CardListAdapter(SllerUserDashboardFragment.this.getContext()).setUserFlag(true);
                    gridView.setAdapter(adapter);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                swipeRefreshLayout.setRefreshing(false);
            }
        },1000);
    }
}