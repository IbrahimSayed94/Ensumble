package com.ensumble.AppConfig;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ensumble.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApp extends Application
{
    protected SharedPreferences sharedPreferences;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApp.context = getApplicationContext();

        Fresco.initialize(getApplicationContext());
        initLanguage();


    } // function of onCreate


    private void initLanguage() {

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);


        if (sharedPreferences.getString("language", "ar").equals("ar")) {

            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/ElMessiri-Regular.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()
            );

        }
        else if (sharedPreferences.getString("language", "ar").equals("en")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()
            );

        }
    } // function of checkLanguage

    public static Context getAppContext() {
        return MyApp.context;
    }

} // class of MyApp

