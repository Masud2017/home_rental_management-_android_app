package com.home_rental.home_rental_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.logging.Logger;


public class HomeActivity extends AppCompatActivity {

    private TabLayout tab = null;
    private ViewPager2 pager = null;

    private Logger logger = Logger.getLogger("HomeActivity.class");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        this.tab = findViewById(R.id.home_tab);
        this.tab.setTabGravity(TabLayout.GRAVITY_CENTER);
        this.pager = findViewById(R.id.pager);
        FragmentStateAdapter homeAndLoginFragmentAdapter = new HomeAndLoginFragmentAdapter(getSupportFragmentManager(),getLifecycle(),getApplicationContext());

        this.pager.setAdapter(homeAndLoginFragmentAdapter);
        new TabLayoutMediator(this.tab, this.pager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Home");
                tab.setIcon(R.drawable.baseline_home_24);

            } else if (position == 1) {
                tab.setText("User");
                tab.setIcon(R.drawable.baseline_account_circle_24);
            } else if (position == 2) {
                tab.setText("Setting");
                tab.setIcon(R.drawable.setting);
            }
        }).attach();

    }

}