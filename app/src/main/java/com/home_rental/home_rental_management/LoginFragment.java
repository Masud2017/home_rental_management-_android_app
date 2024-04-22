package com.home_rental.home_rental_management;

import static android.content.Context.MODE_PRIVATE;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class LoginFragment extends Fragment {

    private AppCompatButton login_btn = null;
    private EditText username = null;
    private EditText password = null;
    private SharedPreferences sessionStorage = null;
    private AlertDialog.Builder alertDialogBuilder = null;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceData) {
        super.onViewCreated(view,savedInstanceData);

        this.login_btn = view.findViewById(R.id.login_btn);
        this.alertDialogBuilder = new AlertDialog.Builder(this.getContext());

        this.login_btn.setOnClickListener( vi -> {
            username = view.findViewById(R.id.username);
            password = view.findViewById(R.id.password);

            String usernameString = username.getText().toString();
            String passwordString = password.getText().toString();

            if (usernameString.toLowerCase().equals("user") && passwordString.equals("user")) {
                Intent intent = new Intent(LoginFragment.this.getContext(), UserDashBoardActivity.class);
                startActivity(intent);
                getActivity().finish();
            } else if (usernameString.toLowerCase().equals("admin") && passwordString.equals("admin")) {
                Intent intent = new Intent(LoginFragment.this.getContext(), AdminDashBoardActivity.class);
                startActivity(intent);
                getActivity().finish();
            } else {
                alertDialogBuilder.setTitle("Invalid login credential");
                alertDialogBuilder.setMessage("Sorry the credential that you provided is not valid. Please enter right credentials.");
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                alertDialogBuilder.show();
            }


            sessionStorage = getActivity().getSharedPreferences("user_session", MODE_PRIVATE);

            SharedPreferences.Editor sessionStorageEditor = sessionStorage.edit();
            sessionStorageEditor.putString("session", "true");
            sessionStorageEditor.putString("name", "Rifat Muntasir");
        });
    }
}