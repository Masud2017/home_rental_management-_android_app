package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SearchView;

import java.util.logging.Logger;


public class HomeActivity extends AppCompatActivity {
    private AppCompatButton loginBtn = null;
    private AppCompatButton signupButton = null;
    private SearchView searchBar = null;
    private ProgressBar progressBar = null;
    private ScrollView scrollView = null;

    private Logger logger = Logger.getLogger("HomeActivity.class");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.loginBtn = findViewById(R.id.login);
        this.signupButton = findViewById(R.id.signup);
        this.searchBar = findViewById(R.id.search_bar);
        this.progressBar = findViewById(R.id.progress_bar);
        this.scrollView = findViewById(R.id.scroll_view);


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

        this.loginBtn.setOnClickListener(view -> {
            Intent loginIntent = new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });

        this.signupButton.setOnClickListener((View view)-> {
            Intent intent = new Intent(HomeActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();
        });
    }

}