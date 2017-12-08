package com.xwtiger.devrescollect.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Busap-112 on 2017/12/8.
 */

public abstract class BaseActivity extends Activity {

    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

    }

    protected void init(){
        initView();
        initData();
        setListener();
    }


    public abstract void initView();

    public abstract void initData();

    public abstract void setListener();
}
