package com.xwtiger.devrescollect.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

import com.xwtiger.devrescollect.view.progressview.ProgressLayout ;

/**
 * @author : 胥文
 * @date : 2020/8/29 14:18
 * @desc :
 */

public class TestProgressActivity extends BaseActivity {
    private ProgressLayout progressLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testprogress);
        progressLayout = (ProgressLayout) findViewById(R.id.progressLayout);
        progressLayout.start();
    }

    @Override
    public void initView() {

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

    @Override
    protected void onStop() {
        super.onStop();
        progressLayout.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressLayout.cancel();
    }
}
