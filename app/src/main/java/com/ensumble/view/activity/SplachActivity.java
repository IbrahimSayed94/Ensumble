package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.Model.BaseResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;

public class SplachActivity extends AppCompatActivity {


    //progress
    CustomDialogProgress progress;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);

        splash();

    } // function of onCreate

    private void  splash()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(PrefUser.getLogin(getApplicationContext()))
                {
                   getCartCount();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);
    } // function of splash

    private void getCartCount ()
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
        AndroidNetworking.post(Constant.BASE_URL + "CountBasket")
                .addBodyParameter("user_id", PrefUser.getUserId(getApplicationContext()))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>() {

                    @Override
                    public void onResponse(BaseResponse response) {
                        progress.dismiss();

                        if (response.getCode().equals("200")) {

                            PrefUser.setCartCount(getApplicationContext(),response.getCount());
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("flag","homeFragment");
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                    }
                });
    } // function of getCartCount

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    } // prevent touch from resize image

} // class of SplashActivity
