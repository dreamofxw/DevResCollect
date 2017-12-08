package com.xwtiger.devrescollect.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Busap-112 on 2017/12/8.
 */

public class Utils {

    public static DisplayMetrics getDisplayMetrics(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public static int getScreenWidth(Context context){
        return getDisplayMetrics(context).widthPixels;
    }

}
