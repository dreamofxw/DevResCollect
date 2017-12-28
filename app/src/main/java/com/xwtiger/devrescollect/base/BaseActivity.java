package com.xwtiger.devrescollect.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Busap-112 on 2017/12/8.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener{

    protected Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        init();
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
