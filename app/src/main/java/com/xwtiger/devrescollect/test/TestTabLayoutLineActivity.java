package com.xwtiger.devrescollect.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.utils.TabLayoutUtils;
import com.xwtiger.devrescollect.utils.Utils;

/**
 * Created by Busap-112 on 2017/12/8.
 */

public class TestTabLayoutLineActivity extends BaseActivity {


    private TabLayout tablayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testtabline);
        init();
    }

    @Override
    public void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
    }

    @Override
    public void initData() {
        TabLayout.Tab tab = tablayout.newTab();
        tab.setText("北京");
        TabLayout.Tab tab1 = tablayout.newTab();
        tab1.setText("上海");
        tablayout.addTab(tab);
        tablayout.addTab(tab1);
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                int width = Utils.getScreenWidth(mContext)/2;
                float widthnew = (float) (width*0.8);
                int margin = (int)((width -widthnew)/2);
                TabLayoutUtils.setIndicator(tablayout,margin,margin,false);
            }
        });
    }

    @Override
    public void setListener() {

    }
}
