package com.ensumble.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.Model.SignUpResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.view.activity.LoginActivity;
import com.ensumble.view.activity.MainActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment extends Fragment
{
    
    
    private Context context;


    @BindView(R.id.user_image)
    ImageView user_image;

    @BindView(R.id.username)
    EditText ed_username;
    @BindView(R.id.user_email)
    EditText ed_user_email;
    @BindView(R.id.user_mobile)
    EditText ed_user_mobile;
    @BindView(R.id.user_adderess)
    EditText ed_user_adderess;

    String username="" , email="" , mobile="" , address="";

    //progress
    CustomDialogProgress progress;
    Handler handler;


    // upload image
    private static final int GET_FROM_GALLERY = 10;
    private static final int REQUEST_CAMERA = 1888;
    String imagePath="";
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);


        setProfileData();


        return view;
    } // function of onCreateView

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    } // function of onAattach

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    } // function of onCreate

    @OnClick({R.id.bt_updateProfile,R.id.user_image})
    public void handleClick(View view)
    {
         if(view.getId() == R.id.bt_updateProfile)
        {
            if(validateFields())
                updateProfile();
            else
                Toast.makeText(context,getString(R.string.completeFields),Toast.LENGTH_LONG).show();
        }
        else if(view.getId() == R.id.user_image)
        {
            int PERMISSION_REQUEST_CODE = 1;
            if (Build.VERSION.SDK_INT >= 23) {
                //do your check here
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                // ask user for permission to access storage // allow - deni
            }

            startActivityForResult(new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    , GET_FROM_GALLERY);

        }
    } // function of handleClick


    private void updateProfile()
    {
        progress = new CustomDialogProgress();
        progress.init(context);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.upload(Constant.BASE_URL+"")
                .addMultipartFile("",new File(imagePath))
                .addMultipartParameter("name",username)
                .addMultipartParameter("email",email)
                .addMultipartParameter("phone",mobile)
                .addMultipartParameter("address",address)
                .addMultipartParameter("roles","3")
                .addMultipartParameter("userId",PrefUser.getUserId(context))
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
                            PrefUser.setLogin(context,true);
                            PrefUser.setUserId(context,response.getUserdata().getId());
                            PrefUser.setEmail(context,email);
                            PrefUser.setAddress(context,address);
                            PrefUser.setUsername(context,username);
                            PrefUser.setMobile(context,mobile);
                            PrefUser.setUserImage(context,response.getUserdata().getImg());

                            Toast.makeText(context,getString(R.string.successfullyDone),Toast.LENGTH_LONG).show();
                        }
                        else if(response.getCode().equals("300"))
                        {
                            Toast.makeText(context,getString(R.string.userRegisterdBefore),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(context,getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(context,getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function of signUp

    private Boolean validateFields()
    {
        username = ed_username.getText().toString();
        email = ed_user_email.getText().toString();
        mobile = ed_user_mobile.getText().toString();
        address = ed_user_adderess.getText().toString();

        if(username.equals(""))
            return false;
        else if(email.equals(""))
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
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
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

            Cursor cursor = context.getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);

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
            Cursor cursor = context.getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
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
        if (resultCode == -1) {
            if (requestCode == GET_FROM_GALLERY) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    } // on activity result fucntion


    private void setProfileData()
    {
        Glide.with(context)
                .load(Constant.USER_IMAGE_URL+PrefUser.getUserImage(context))
                .placeholder(context.getDrawable(R.drawable.profile))
                .into(user_image);

        ed_user_adderess.setText(PrefUser.getAddress(context));
        ed_user_email.setText(PrefUser.getEmail(context));
        ed_username.setText(PrefUser.getUsername(context));
        ed_user_mobile.setText(PrefUser.getMobile(context));
    } // function of setProfileData
}
