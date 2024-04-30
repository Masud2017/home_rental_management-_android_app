package com.home_rental.home_rental_management;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.home_rental.home_rental_management.Models.Home;

import java.util.logging.Logger;

public class HomeAndLoginFragmentAdapter extends FragmentStateAdapter {
    private final Integer TAB_COUNT = 2;
    private Logger logger = Logger.getLogger("HomeAndLoginFragmentAdapter.class");
    private Context context;
    public HomeAndLoginFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, Context context) {
        super(fragmentManager, lifecycle);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("user_session",Context.MODE_PRIVATE);


        this.logger.info("Value of the position is : "+position);

        if(position ==0) {
            Fragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putString(HomeFragment.TITLE,"Tab "+(position+1));
            fragment.setArguments(args);

            return fragment;
        } else if (position == 1) {
            Fragment fragment;
            if (sharedPreferences.getString("session","").equals("true")) {
                fragment= new UserDashBoardFragment();
                Bundle args = new Bundle();
                args.putString("Login","Tab "+(position+1));
                fragment.setArguments(args);

            } else {
                fragment= new LoginFragment();
                Bundle args = new Bundle();
                args.putString("Login","Tab "+(position+1));
                fragment.setArguments(args);
            }


            return fragment;
        } else if (position == 2) {
            Fragment fragment= new SettingFragment();
            Bundle args = new Bundle();
            args.putString("Login","Tab "+(position+1));
            fragment.setArguments(args);
            return fragment;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("user_session",Context.MODE_PRIVATE);
        if (sharedPreferences.getString("session","").equals("true")) {
            return TAB_COUNT+1;
        } else{
            return this.TAB_COUNT;
        }
    }
}
