package com.home_rental.home_rental_management;

import android.graphics.Typeface;
import android.graphics.drawable.Icon;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.HomeModelResponse;
import com.home_rental.home_rental_management.adapters.CardListAdapter;
import com.home_rental.home_rental_management.services.Api;
import com.home_rental.home_rental_management.services.AsyncApiCall;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.jar.Attributes;


public class HomeFragment extends Fragment {
    public static String TITLE = "home";

    private SearchView searchBar = null;
    private ProgressBar progressBar = null;
    private GridView scrollView = null;


    public HomeFragment() {
        // Required empty public constructor
    }

    public void showHomeList(View view) throws IOException, ExecutionException, InterruptedException {
        GridView gridview = view.findViewById(R.id.home_grid);
        CardListAdapter cardListAdapter = new CardListAdapter(getContext());

        gridview.setAdapter(cardListAdapter);
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
        this.scrollView = view.findViewById(R.id.home_grid);

        try {
            this.showHomeList(view);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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