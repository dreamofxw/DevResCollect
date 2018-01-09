package com.xwtiger.devrescollect.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Busap-112 on 2018/1/9.
 */

public class SharedPreferencesUtils {

    public static String FILENAME = "devrescollet";
    public static String KEY_NIGHTMODE = "key_nightmode";


    public static void saveNightMode(Context context,boolean isNightmode){
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(KEY_NIGHTMODE, isNightmode);
        edit.apply();
    }

    public static boolean getNightMode(Context context){
        SharedPreferences sp = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_NIGHTMODE,false);
    }


}
