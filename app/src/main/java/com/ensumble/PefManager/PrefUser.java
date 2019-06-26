package com.ensumble.PefManager;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class PrefUser
{

    private static final String LOGIN = "is_login" ;
    private static final String LANGUAGE = "language" ;
    private static final String PREF_NAME = "user";



    public static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void setLogin(Context context,boolean isLogin)
    {
        getPref(context).edit().putBoolean(LOGIN,isLogin).apply();
    }

    public static Boolean getLogin(Context context)
    {
        return getPref(context).getBoolean(LOGIN,false);
    }

    ///////////////////////////////////

    public static void setLanguage(Context context,String lang)
    {
        getPref(context).edit().putString(LANGUAGE,lang).apply();
    }

    public static String getLanguage(Context context)
    {
        return getPref(context).getString(LANGUAGE,"ar");
    }

}

