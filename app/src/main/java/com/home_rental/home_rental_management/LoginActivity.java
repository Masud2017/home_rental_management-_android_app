package com.home_rental.home_rental_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private AppCompatButton login_btn = null;
    private EditText username = null;
    private EditText password = null;
    private SharedPreferences sessionStorage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.login_btn = findViewById(R.id.login_btn);

        this.login_btn.setOnClickListener(view -> {
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);

            String usernameString = username.getText().toString();
            String passwordString = password.getText().toString();

            if (usernameString.toLowerCase().equals("user") && passwordString.equals("user")) {
                Intent intent = new Intent(LoginActivity.this, UserDashBoardActivity.class);
                startActivity(intent);
                finish();
            } else if (usernameString.toLowerCase().equals("admin") && passwordString.equals("admin")) {
                Intent intent = new Intent(LoginActivity.this, AdminDashBoardActivity.class);
                startActivity(intent);
                finish();
            } else {

            }

//            TextView textview = findViewById(R.id.text_show);
//            textview.setText("Hello world here is the text that I want to show : \n");
//            textview.append("User name : "+usernameString+"\n");
//            textview.append("Password : "+ passwordString);


//            sessionStorage = getSharedPreferences("user_session", MODE_PRIVATE);



        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}