package com.home_rental.home_rental_management;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.home_rental.home_rental_management.Models.MyProfile;
import com.home_rental.home_rental_management.custom_broadcasts.JwtTokenExpiryBroadcaster;
import com.home_rental.home_rental_management.services.Api;

import java.util.concurrent.ExecutionException;

@SuppressWarnings("deprecation")
public class SettingFragment extends Fragment {
    private TextView logoutText = null;
    private TextView walletRecharge = null;
    private TextView transactionHistory = null;
    private TextView walletHistory = null;
    private AppCompatImageView settingProfilePic = null;
    private TextView settingName = null;
    private TextView settingBalance = null;
    private TextView phoneNumber = null;

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
        this.transactionHistory = view.findViewById(R.id.transaction_history_setting);
        this.walletHistory = view.findViewById(R.id.recharge_history);
        this.settingProfilePic = view.findViewById(R.id.setting_profile_pic);
        this.settingName = view.findViewById(R.id.setting_name);
        this.settingBalance = view.findViewById(R.id.setting_balance);
        this.phoneNumber = view.findViewById(R.id.phone_number_setting);

        Api api = new Api();
        Api.MyProfileAsyncTask myProfileAsyncTask = api.new MyProfileAsyncTask().setContext(getContext());
        try {
            MyProfile myProfile = myProfileAsyncTask.execute().get();

            this.settingName.setText(myProfile.getName());

            String profileImageBase64String = myProfile.getImage();
            byte[] imgByteArray = Base64.decode(profileImageBase64String,Base64.DEFAULT);
            this.settingProfilePic.setImageBitmap(BitmapFactory.decodeByteArray(imgByteArray,0,imgByteArray.length));

            this.phoneNumber.setText(myProfile.getPhone_number());

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        SharedPreferences sharedPreferencesBalance = getContext().getSharedPreferences("user_session",Context.MODE_PRIVATE);

        if (sharedPreferencesBalance.getString("role","").equals("seller")) {
            this.walletHistory.setVisibility(View.GONE);
            this.walletRecharge.setVisibility(View.GONE);
        }

        String balance = sharedPreferencesBalance.getString("balance","");

        this.settingBalance.setText(balance + " BDT");

        this.transactionHistory.setOnClickListener(view2 -> {
            Intent transactionIntent = new Intent(getContext(), TransactionHistoryActivity.class);
            transactionIntent.putExtra("user_id",1);
            startActivity(transactionIntent);
        });

        this.walletHistory.setOnClickListener(view2 -> {
            Intent walletHistoryIntent = new Intent(getContext(),RechargeHistoryActivity.class);
            startActivity(walletHistoryIntent);
        });

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