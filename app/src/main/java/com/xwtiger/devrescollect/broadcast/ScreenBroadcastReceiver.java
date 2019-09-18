package com.xwtiger.devrescollect.broadcast;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.xwtiger.devrescollect.MyApplication;


public class ScreenBroadcastReceiver extends BroadcastReceiver {

    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    public int state = 0;//
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("testlockscreen","广播Action = " + action);
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Log.e("testlockscreen","锁屏");
        } else if (action.equals(Intent.ACTION_SCREEN_ON)) {
            Log.e("testlockscreen","解锁");
        }else if(action.equals(Intent.ACTION_USER_PRESENT)&&isForeground(MyApplication.getContext())){
            Log.e("testlockscreen","开屏");
        }else if(action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
            if (TextUtils.equals(SYSTEM_DIALOG_REASON_HOME_KEY, reason)&&state !=1) {
                state =1;
                Log.e("testlockscreen","home健");
            }
        }
    }


    private boolean isForeground(Context context) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
            String currentPackageName = cn.getPackageName();
            if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
                Log.d("testlockscreen", "isForeground: 前台");
                return true;
            }
            Log.d("testlockscreen", "isForeground: 后台");
            return false;
        }else{
            Log.d("testlockscreen", "isForeground: null 后台");

        }
        return false;
    }

}