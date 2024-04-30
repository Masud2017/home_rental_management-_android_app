package com.home_rental.home_rental_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.widget.EditText;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.User;
import com.home_rental.home_rental_management.Models.UserAuthResponse;
import com.home_rental.home_rental_management.services.Api;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class LoginActivity extends AppCompatActivity {
    private AppCompatButton login_btn = null;
    private EditText username = null;
    private EditText password = null;
    private SharedPreferences sessionStorage = null;
    private AlertDialog.Builder alertDialogBuilder = null;
    private Logger logger = Logger.getLogger("LoginActivity.class");

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Api api = new Api();

        this.login_btn = findViewById(R.id.login_btn);
        this.alertDialogBuilder = new AlertDialog.Builder(this);

        this.login_btn.setOnClickListener(view -> {
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);

            String usernameString = username.getText().toString();
            String passwordString = password.getText().toString();

//            if (usernameString.toLowerCase().equals("user") && passwordString.equals("user")) {
//                Intent intent = new Intent(LoginActivity.this, UserDashBoardActivity.class);
//                startActivity(intent);
//                finish();
//            } else if (usernameString.toLowerCase().equals("admin") && passwordString.equals("admin")) {
//                Intent intent = new Intent(LoginActivity.this, AdminDashBoardActivity.class);
//                startActivity(intent);
//                finish();
//            } else {
//                alertDialogBuilder.setTitle("Invalid login credential");
//                alertDialogBuilder.setMessage("Sorry the credential that you provided is not valid. Please enter right credentials.");
//                alertDialogBuilder.setCancelable(false);
//                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//
//                alertDialogBuilder.show();
//            }

            User user = new User();
            user.setEmail(usernameString);
            user.setPassword(passwordString);


            Api.AuthenticationTask authenticationTask = api.new AuthenticationTask().setCredential(user);
            try {
                UserAuthResponse userAuthResponse = authenticationTask.execute().get();

                LoginActivity.this.logger.info("Auth token is ; "+userAuthResponse.getAccess_token());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            sessionStorage = getSharedPreferences("user_session", MODE_PRIVATE);

            SharedPreferences.Editor sessionStorageEditor = sessionStorage.edit();
            sessionStorageEditor.putString("session","true");
            sessionStorageEditor.putString("name","Rifat Muntasir");

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