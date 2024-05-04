package com.home_rental.home_rental_management;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;
import com.home_rental.home_rental_management.Models.User;
import com.home_rental.home_rental_management.services.Api;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;

public class SignupActivity extends AppCompatActivity{
    private AppCompatButton cameraButton = null;
    private AppCompatImageView imageView = null;
    private EditText name = null;
    private EditText email = null;
    private EditText password = null;
    private EditText retypePassword = null;
    private RadioGroup roleRadioGroup = null;
    private RadioButton roleButton = null;
    private AppCompatButton signUpBtn = null;
    private AlertDialog.Builder alertDialogBuilder = null;

    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    @SuppressWarnings("deprecation")
    public void uploadImageToTheServer(String email) throws ExecutionException, InterruptedException {
        Api api = new Api();

        Api.AddProfileImageAsyncTask addProfileImageAsyncTask = api.new AddProfileImageAsyncTask();
        addProfileImageAsyncTask.setImageView(this.imageView);
        addProfileImageAsyncTask.setEmail(email);
        addProfileImageAsyncTask.execute().get();
    }


    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        this.cameraButton = findViewById(R.id.camera_button);
        this.imageView = findViewById(R.id.cap_image);
        this.name = findViewById(R.id.name_signup);
        this.email = findViewById(R.id.username_signup);
        this.password = findViewById(R.id.password_signup);
        this.retypePassword = findViewById(R.id.retype_password_signup);
        this.roleRadioGroup = findViewById(R.id.role_signup);
        this.signUpBtn = findViewById(R.id.singup_btn);
        this.alertDialogBuilder = new AlertDialog.Builder(this);

        this.roleRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                roleButton = findViewById(i);
                System.out.println("Selected role is : "+roleButton.getText().toString());
            }
        });

        Api api = new Api();

        this.signUpBtn.setOnClickListener(view2 -> {
            System.out.println("Password : "+this.password.getText());
            System.out.println("Retype Password : "+this.retypePassword.getText());

            if (this.password.getText().toString().equals(this.retypePassword.getText().toString())) {


                if (this.roleButton.getText().toString().equals("user")) {
                    Api.SignupUserAsyncTask signupUserAsyncTask = api.new SignupUserAsyncTask();
                    User user = new User();
                    user.setEmail(this.email.getText().toString());
                    user.setPassword(this.password.getText().toString());
                    user.setName(this.name.getText().toString());

                    signupUserAsyncTask.setRole("user");
                    signupUserAsyncTask.setUser(user);

                    try {
                        signupUserAsyncTask.execute().get();

                        this.uploadImageToTheServer(user.getEmail());

                        Intent intent = new Intent(this,HomeActivity.class);
                        startActivity(intent);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SignupActivity.this,"User registration success full",Toast.LENGTH_LONG);
                            }
                        },1000);

                        finish();

                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (this.roleButton.getText().toString().equals("seller")) {
                    Api.SignupUserAsyncTask signupUserAsyncTask = api.new SignupUserAsyncTask();
                    User user = new User();
                    user.setEmail(this.email.getText().toString());
                    user.setPassword(this.password.getText().toString());
                    user.setName(this.name.getText().toString());

                    signupUserAsyncTask.setRole("seller");
                    signupUserAsyncTask.setUser(user);

                    try {
                        signupUserAsyncTask.execute().get();
                        this.uploadImageToTheServer(user.getEmail());

                        Intent intent = new Intent(this,HomeActivity.class);
                        startActivity(intent);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SignupActivity.this,"User registration success full",Toast.LENGTH_LONG);
                            }
                        },1000);

                        finish();

                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (this.roleButton.getText().toString().equals("root")) {
                    Api.SignupUserAsyncTask signupUserAsyncTask = api.new SignupUserAsyncTask();
                    User user = new User();
                    user.setEmail(this.email.getText().toString());
                    user.setPassword(this.password.getText().toString());
                    user.setName(this.name.getText().toString());

                    signupUserAsyncTask.setRole("root");
                    signupUserAsyncTask.setUser(user);

                    try {
                        signupUserAsyncTask.execute().get();
                        this.uploadImageToTheServer(user.getEmail());


                        Intent intent = new Intent(this,HomeActivity.class);
                        startActivity(intent);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(SignupActivity.this,"User registration success full",Toast.LENGTH_LONG);
                            }
                        },1000);

                        finish();
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {

                alertDialogBuilder.setTitle("Invalid login credential");
                alertDialogBuilder.setMessage("Password did not matched.");
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

            }
        });

        cameraProviderFuture = ProcessCameraProvider.getInstance(this);


        this.cameraButton.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED)
            {
                // Permission is not granted

                ActivityCompat.requestPermissions((Activity) this,
                        new String[] { Manifest.permission.CAMERA },
                        100);
            }

//            registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
//                @Override
//                public void onActivityResult(Bitmap result) {
//                    imageView.setImageBitmap(result);
//                }
//            });

            Intent inten = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(inten,100);

//            cameraProviderFuture.addListener(() -> {
//                try {
//                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
//                    bindPreview(cameraProvider);
//                } catch (ExecutionException | InterruptedException e) {
//                    // No errors need to be handled for this Future.
//                    // This should never be reached.
//                }
//            }, ContextCompat.getMainExecutor(this));
//







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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                Bundle extras = data.getExtras();

                Bitmap image = (Bitmap) extras.get("data");
                imageView.setImageBitmap(image);

            }
        }

    }


    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
//        Preview preview = new Preview.Builder()
//                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        ImageCapture imageCapture = new ImageCapture.Builder().build();

//        preview.setSurfaceProvider(imageView.getSurfaceProvider());

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, imageCapture);

        imageCapture.takePicture(ContextCompat.getMainExecutor(this), new ImageCapture.OnImageCapturedCallback() {
            @Override
            public void onCaptureSuccess(@NonNull ImageProxy image) {
                @ExperimentalGetImage Image img = image.getImage();
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] bytes = new byte[buffer.capacity()];
                buffer.get(bytes);
                Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);
                imageView.setImageBitmap(bitmapImage);
                image.close();
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {

            }

        });


    }
}