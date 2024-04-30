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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.HomeModelResponse;
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
    private ScrollView scrollView = null;


    public HomeFragment() {
        // Required empty public constructor
    }

    public void showHomeList(View view) throws IOException, ExecutionException, InterruptedException {
        AsyncApiCall asyncApiCall = new AsyncApiCall();
        @SuppressWarnings("deprecation")
        List<HomeModelResponse> homeModelResponseList = asyncApiCall.execute("").get();

        for (HomeModelResponse homeModelResponseItem : homeModelResponseList) {
            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(450,840);

            marginLayoutParams.setMargins(12,12,15,12);
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(marginLayoutParams);

            CardView cardView = new CardView(this.getContext());
            cardView.setLayoutParams(layoutParams);

            // setting up the basic card attribtues
            cardView.setRadius(30);

            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            cardView.addView(linearLayout);

            // portion for the image
            ViewGroup.LayoutParams imageLayoutParam = new ViewGroup.LayoutParams(250,250);
            ImageView imageView = new ImageView(this.getContext());

            imageView.setImageIcon(Icon.createWithResource(getContext(),R.drawable.logo));
            imageView.setLayoutParams(imageLayoutParam);

            linearLayout.addView(imageView);
            // section for the card image ended

            // section for the card header
            AttributeSet attributeSet =
            ViewGroup.MarginLayoutParams textMarginParam = new ViewGroup.MarginLayoutParams(getContext(),null);
            textMarginParam.setMargins(0,15,0,0);

            TextView text = new TextView(this.getContext());
            text.setLayoutParams(textMarginParam);

            text.setText(homeModelResponseItem.getName());
            text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            text.setTextSize(20);
            text.setTypeface(text.getTypeface(), Typeface.BOLD);
            linearLayout.addView(text);
            // section for the card header ended


            // adding this card to the grid view
            GridLayout grid = view.findViewById(R.id.home_grid);
            grid.addView(cardView);
        }

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