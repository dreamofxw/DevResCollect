package com.xwtiger.devrescollect.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Busap-112 on 2017/12/8.
 */
//public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener{
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected Activity mContext;
    public static String TAG ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        TAG = this.getClass().getSimpleName();
        com.xwtiger.devrescollect.ActivityManager.getInstance().addActivity(this);
    }

    protected void init(){
        initView();
        initData();
        setListener();
    }


    public abstract void initView();

    public abstract void initData();

    public abstract void setListener();

    @Override
    protected void onDestroy() {
        com.xwtiger.devrescollect.ActivityManager.getInstance().deleteActivity(this);
        super.onDestroy();
    }
}
