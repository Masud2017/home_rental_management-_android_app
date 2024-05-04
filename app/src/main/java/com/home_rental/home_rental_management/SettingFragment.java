package com.home_rental.home_rental_management;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.home_rental.home_rental_management.custom_broadcasts.JwtTokenExpiryBroadcaster;

public class SettingFragment extends Fragment {
    private TextView logoutText = null;
    private TextView walletRecharge = null;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.logoutText = view.findViewById(R.id.profile_logout);
        this.walletRecharge = view.findViewById(R.id.wallet_recharge_setting);

        this.logoutText.setOnClickListener(view1 -> {
            SharedPreferences sharedPreferences = SettingFragment.this.getContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);

            if (sharedPreferences.getString("session","").equals("true")) {

                Intent jwtRemoveBroadcastInokerIntent = new Intent(SettingFragment.this.getContext(),JwtTokenExpiryBroadcaster.class);
                jwtRemoveBroadcastInokerIntent.setAction("JWT_REMOVE");
                getActivity().sendBroadcast(jwtRemoveBroadcastInokerIntent);

                Intent intent = new Intent(SettingFragment.this.getActivity(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                SettingFragment.this.getActivity().finish();
            }
        });

        this.walletRecharge.setOnClickListener(view2 -> {
            Intent intent = new Intent(SettingFragment.this.getContext(),WalletActivity.class);

            startActivity(intent);
        });
    }

}