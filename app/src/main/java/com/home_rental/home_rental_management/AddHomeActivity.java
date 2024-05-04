package com.home_rental.home_rental_management;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;

import com.home_rental.home_rental_management.Models.Home;
import com.home_rental.home_rental_management.services.Api;

import java.util.concurrent.ExecutionException;

@SuppressWarnings("deprecation")
public class AddHomeActivity extends AppCompatActivity {
    private AppCompatImageView homeImage = null;
    private AppCompatButton addHomeImgCapBtn = null;
    private EditText addHomeName = null;
    private EditText addHomeDesc = null;
    private EditText addHomePrice = null;
    private EditText addHomeAddress= null;
    private EditText addHomeFlatCount = null;

    private Button addHomeBtn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_home);
        this.homeImage = findViewById(R.id.add_home_img);
        this.addHomeImgCapBtn = findViewById(R.id.add_home_img_cap_btn);
        this.addHomeBtn = findViewById(R.id.add_home_btn);
        this.addHomeName = findViewById(R.id.add_home_name);
        this.addHomeDesc = findViewById(R.id.add_home_desc);
        this.addHomePrice = findViewById(R.id.add_home_price);
        this.addHomeAddress = findViewById(R.id.add_home_addr);
        this.addHomeFlatCount = findViewById(R.id.add_home_flat_count);

        this.addHomeImgCapBtn.setOnClickListener(view2 -> {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions((Activity) this,
                        new String[] { Manifest.permission.CAMERA },
                        100);
            }


            Intent inten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(inten,100);

        });



        this.addHomeBtn.setOnClickListener(view2 -> {
            Home home = new Home();

            home.setName(this.addHomeName.getText().toString());
            home.setDesc(this.addHomeDesc.getText().toString());

            if (this.addHomePrice.getText().toString().isEmpty()) {
                home.setPrice(0);
            } else {
                home.setPrice(Integer.valueOf(this.addHomePrice.getText().toString()));
            }

            home.setAddress(this.addHomeAddress.getText().toString());
            home.setFlat_count(this.addHomeFlatCount.getText().toString());
            home.setHomeImage(this.homeImage);


            Api api = new Api();
            Api.AddHomeAsyncTask addHomeAsyncTask = api.new AddHomeAsyncTask().setHome(home).setContext(this);

            try {
                addHomeAsyncTask.execute().get();

                Intent intent = new Intent(this,HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                Bundle extras = data.getExtras();

                Bitmap image = (Bitmap) extras.get("data");
                homeImage.setImageBitmap(image);
            }
        }

    }

}