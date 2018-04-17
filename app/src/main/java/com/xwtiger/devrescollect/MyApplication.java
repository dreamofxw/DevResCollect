package com.xwtiger.devrescollect;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;


/**
 * Created by Busap-112 on 2018/1/9.
 */

public class MyApplication extends Application {

    public String tag = "MyApplicationLog";

    public static Context context;

    public boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplicationLog","onCreate");
        context = this;
        //LeakCanary.install(this);

        if(isDebug){
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }


    }


    public static Context getContext(){
        return context;
    }
}
