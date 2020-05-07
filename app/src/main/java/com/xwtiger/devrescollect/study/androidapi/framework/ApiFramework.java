package com.xwtiger.devrescollect.study.androidapi.framework;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;

public class ApiFramework {


    /**
     * activitymanager相关
     * @param context
     */
    public void getActivityManager(Context context){

        //ActivityManager
        ActivityManager systemService = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);


    }


    /**
     * windowmanager相关
     * @param context
     */
    public void getWindowManager(Context context){

        //
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);


    }


    /**
     * layoutinflatermanager相关
     * @param context
     */
    public void getLayoutInflaterManager(Context context){

        //
        LayoutInflater windowManager = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    /**
     *
     * @param context
     */
    public void getLocationManager(Context context){

        //
        LocationManager locationManager= (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

    }


    /**
     *
     * @param context
     */
    public void getNotificationManager(Context context){

        //
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

    }


    /**
     *
     * @param context
     */
    public void getTelephonyManager(Context context){

        //
        TelephonyManager telephonyManager= (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

    }


    /**
     *
     * @param context
     */
    public void getPackageManager(Context context){

        //
        PackageManager packageManager = context.getPackageManager();
    }


    



}
