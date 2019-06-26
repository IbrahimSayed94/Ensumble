package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.Model.ProductDetailsResponse;
import com.ensumble.Model.SignUpResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignupActivity extends AppCompatActivity {



    @BindView(R.id.user_image)
    ImageView user_image;

    @BindView(R.id.username)
    EditText ed_username;
    @BindView(R.id.user_email)
    EditText ed_user_email;
    @BindView(R.id.user_password)
    EditText ed_user_password;
    @BindView(R.id.user_mobile)
    EditText ed_user_mobile;
    @BindView(R.id.user_adderess)
    EditText ed_user_adderess;

    String username="" , email="" , password="" , mobile="" , address="";

    //progress
    CustomDialogProgress progress;
    Handler handler;


    // upload image
    private static final int GET_FROM_GALLERY = 10;
    private static final int REQUEST_CAMERA = 1888;
    String imagePath="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

    } // function of onCreate


    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts


    @OnClick({R.id.bt_login,R.id.bt_signUp,R.id.user_image})
    public void handleClick(View view)
    {
        if(view.getId() == R.id.bt_login)
        {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.bt_signUp)
        {
            if(validateFields())
                signUp();
            else
                Toast.makeText(getApplicationContext(),getString(R.string.completeFields),Toast.LENGTH_LONG).show();
        }
        else if(view.getId() == R.id.user_image)
        {
            int PERMISSION_REQUEST_CODE = 1;
            if (Build.VERSION.SDK_INT >= 23) {
                //do your check here
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                // ask user for permission to access storage // allow - deni
            }

            startActivityForResult(new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    , GET_FROM_GALLERY);

        }
    } // function of handleClick


    private void signUp()
    {
        progress = new CustomDialogProgress();
        progress.init(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.upload(Constant.BASE_URL+"registration")
                .addMultipartFile("",new File(imagePath))
                .addMultipartParameter("name",username)
                .addMultipartParameter("email",email)
                .addMultipartParameter("password",password)
                .addMultipartParameter("phone",mobile)
                .addMultipartParameter("address",address)
                .addMultipartParameter("admin","0")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                    }
                })
                .getAsObject(SignUpResponse.class, new ParsedRequestListener<SignUpResponse>() {

                    @Override
                    public void onResponse(SignUpResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            PrefUser.setLogin(getApplicationContext(),true);
                            PrefUser.setUserId(getApplicationContext(),response.getUserdata().getId());
                            PrefUser.setEmail(getApplicationContext(),email);
                            PrefUser.setAddress(getApplicationContext(),address);
                            PrefUser.setUsername(getApplicationContext(),username);
                            PrefUser.setMobile(getApplicationContext(),mobile);

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                        else if(response.getCode().equals("300"))
                        {
                            Toast.makeText(getApplicationContext(),getString(R.string.userRegisterdBefore),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function of signUp

    private Boolean validateFields()
    {
        username = ed_username.getText().toString();
        email = ed_user_email.getText().toString();
        password = ed_user_password.getText().toString();
        mobile = ed_user_mobile.getText().toString();
        address = ed_user_adderess.getText().toString();

        if(username.equals(""))
            return false;
        else if(email.equals(""))
            return false;
        else if(password.equals(""))
            return false;
        else if(mobile.equals(""))
            return false;
        else if(address.equals(""))
            return false;
        else if(imagePath.equals(""))
            return false;

        return  true;
    } // function of validateFields

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }// function of getRealPathFromURI

    private void onSelectFromGalleryResult(Intent data) {
        if (data != null) {

            Uri imageUri = data.getData();

            user_image.setImageURI(imageUri);

            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                imagePath = getRealPathFromURI(imageUri);

            }
        }
    } // onSelectFromGalleryResult

    private void onCaptureImageResult(Intent data) {
        if (data != null) {
            Uri imageUri = data.getData();
             user_image.setImageURI(imageUri);
            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.ACTION_IMAGE_CAPTURE};
            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                imagePath = getRealPathFromURI(imageUri);
            }
            Log.i("QP", imagePath);
        }
    } // onCaptureImageResult

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GET_FROM_GALLERY) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    } // on activity result fucntion



} // class of SignupAvtivity
