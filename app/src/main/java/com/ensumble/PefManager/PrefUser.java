package com.ensumble.PefManager;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class PrefUser
{


    private static final String PREF_NAME = "user";


    private static final String LOGIN = "is_login" ;
    private static final String LANGUAGE = "language" ;
    private static final String USERNAME = "username" ;
    private static final String EMAIL = "email" ;
    private static final String MOBILE = "mobile" ;
    private static final String ADDRESS = "address" ;
    private static final String USER_ID = "userId" ;


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

    ///////////////////////////////////

    public static void setUserId(Context context,String lang)
    {
        getPref(context).edit().putString(USER_ID,lang).apply();
    }

    public static String getUserId(Context context)
    {
        return getPref(context).getString(USER_ID,"");
    }

    ///////////////////////////////////

    public static void setUsername(Context context,String lang)
    {
        getPref(context).edit().putString(USERNAME,lang).apply();
    }

    public static String getUsername(Context context)
    {
        return getPref(context).getString(USERNAME,"");
    }

    ///////////////////////////////////

    public static void setEmail(Context context,String lang)
    {
        getPref(context).edit().putString(EMAIL,lang).apply();
    }

    public static String getEmail(Context context)
    {
        return getPref(context).getString(EMAIL,"");
    }

    ///////////////////////////////////

    public static void setMobile(Context context,String lang)
    {
        getPref(context).edit().putString(MOBILE,lang).apply();
    }

    public static String getMobile(Context context)
    {
        return getPref(context).getString(MOBILE,"");
    }

    ///////////////////////////////////

    public static void setAddress(Context context,String lang)
    {
        getPref(context).edit().putString(ADDRESS,lang).apply();
    }

    public static String getAddress(Context context)
    {
        return getPref(context).getString(ADDRESS,"");
    }

}

