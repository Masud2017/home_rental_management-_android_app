package com.home_rental.home_rental_management;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SearchView;


public class HomeFragment extends Fragment {
    public static String TITLE = "home";

    private SearchView searchBar = null;
    private ProgressBar progressBar = null;
    private ScrollView scrollView = null;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceData) {
        super.onViewCreated(view,savedInstanceData);

        this.searchBar = view.findViewById(R.id.search_bar);

        this.progressBar = view.findViewById(R.id.progress_bar);
        this.scrollView = view.findViewById(R.id.scroll_view);



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(ProgressBar.GONE);
                scrollView.setVisibility(ScrollView.VISIBLE);
            }

        }, 2000);

        this.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });

    }
}