package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.Model.ProductDetailsResponse;
import com.ensumble.Model.SignUpResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {




    String mobile="" , password="" ;

    @BindView(R.id.ed_mobile)
    EditText ed_mobile;

    @BindView(R.id.ed_password)
    EditText ed_password;

    //progress
    CustomDialogProgress progress;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);


    } // function of onCreate

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts


    @OnClick({R.id.bt_login,R.id.bt_signUp})
    public void handleClick(View view)
    {
        if(view.getId() == R.id.bt_login)
        {
            if(validateFields())
                login();
            else
                Toast.makeText(getApplicationContext(),getString(R.string.completeFields),Toast.LENGTH_LONG).show();
        }
        else if(view.getId() == R.id.bt_signUp)
        {
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(intent);
        }
    } // function of handleClick


    private void login()
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
        AndroidNetworking.post(Constant.BASE_URL+"login")
                .addBodyParameter("phone", mobile)
                .addBodyParameter("password", password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(SignUpResponse.class, new ParsedRequestListener<SignUpResponse>() {

                    @Override
                    public void onResponse(SignUpResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            PrefUser.setLogin(getApplicationContext(),true);
                            PrefUser.setUserId(getApplicationContext(),response.getUserdata().getId());
                            PrefUser.setEmail(getApplicationContext(),response.getUserdata().getEmail());
                            PrefUser.setAddress(getApplicationContext(),response.getUserdata().getAddress());
                            PrefUser.setUsername(getApplicationContext(),response.getUserdata().getName());
                            PrefUser.setMobile(getApplicationContext(),mobile);
                            PrefUser.setUserImage(getApplicationContext(),response.getUserdata().getImg());

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if(response.getCode().equals("300"))
                        {
                            Toast.makeText(getApplicationContext(),getString(R.string.mobileOrPasswordWrong),Toast.LENGTH_LONG).show();
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

    } // function of login

    private Boolean validateFields()
    {
        mobile = ed_mobile.getText().toString();
        password  = ed_password.getText().toString();

        if(mobile.equals(""))
            return false;
        else if (password.equals(""))
            return false;

        return true;
    } // function of validateFields


} // class of LoginActivity
