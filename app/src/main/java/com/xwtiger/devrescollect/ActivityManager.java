package com.xwtiger.devrescollect;

import com.xwtiger.devrescollect.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xwadmin on 2018/4/10.
 */

public class ActivityManager {

    private static ActivityManager instance;

    private Map<String,BaseActivity> activityMap= new HashMap();

    private ActivityManager(){

    }

    public static ActivityManager getInstance(){
        if(instance == null){
            synchronized (ActivityManager.class){
                if(instance == null){
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    public synchronized void addActivity(BaseActivity baseActivity){
        activityMap.put(baseActivity.TAG,baseActivity);
    }

    public synchronized void deleteActivity(BaseActivity baseActivity){
       activityMap.remove(baseActivity.TAG);
    }
}
