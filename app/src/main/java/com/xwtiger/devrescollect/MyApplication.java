package com.xwtiger.devrescollect;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

//import com.bumptech.glide.annotation.GlideModule;
import com.qiniu.pili.droid.streaming.StreamingEnv;
import com.xwtiger.devrescollect.statistics.YouShuStatistics;


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
        //YouShuStatistics.getInstance().startCheck();
        StreamingEnv.init(getApplicationContext());

        int oldcode = getCode(context);
        int newcode = getVersionCode(context);
        if(newcode>oldcode){
            Log.d("testversioncode ", "onCreate: newcode ="+newcode);
            Log.d("testversioncode ", "onCreate: oldcode ="+oldcode);
        }

        saveCode(context,newcode);

        int a = 4004000;
        Log.d("testversioncode", "onCreate: a="+a);
        Log.d("testversioncode", "onCreate: a="+Integer.MAX_VALUE);

    }


    public static Context getContext(){
        return context;
    }


    public static synchronized int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            MyException.printStr(e);
        }
        return 0;
    }

    public void saveCode(Context context,int versioncode){
        SharedPreferences savecode = context.getSharedPreferences("savecode", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = savecode.edit();
        edit.putInt("versioncode",versioncode);
        edit.commit();
    }


    public int getCode(Context context){
        SharedPreferences savecode = context.getSharedPreferences("savecode", Context.MODE_PRIVATE);
        int versioncode = savecode.getInt("versioncode",0);
        return versioncode;
    }


}
