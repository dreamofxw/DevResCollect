package com.xwtiger.devrescollect.study.androidapi.img.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.study.androidapi.img.view.TestColorFilterView;

/**
 * Created by Busap-112 on 2017/12/20.
 */

public class TestColorFilterActivity extends BaseActivity {


    private TestColorFilterView mTestColorFilterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_testcolorfilter);
        init();

    }

    @Override
    public void initView() {
        mTestColorFilterView = (TestColorFilterView) findViewById(R.id.colorfilterview);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
