package com.xwtiger.devrescollect.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.LeadingMarginSpan;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
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


    public static String actionToString(int action){
        String result = "";
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                result = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                result = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                result = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                result = "ACTION_CANCEL";
                break;
            default:
                result = action+"";
                break;
        }
        return result;
    }


    public static SpannableString getSpannableString( String description,int marginSpanSize) {
        SpannableString spannableString = new SpannableString(description);
        LeadingMarginSpan leadingMarginSpan = new LeadingMarginSpan.Standard(marginSpanSize, 0);//仅首行缩进
        spannableString.setSpan(leadingMarginSpan, 0, description.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
