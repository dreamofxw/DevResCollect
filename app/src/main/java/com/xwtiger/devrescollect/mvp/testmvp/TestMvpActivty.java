package com.xwtiger.devrescollect.mvp.testmvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.utils.FragmentUtils;

/**
 * Created by xwadmin on 2018/4/17.
 */

public class TestMvpActivty extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mvp);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        TestMvpFragment testMvpFragment = new TestMvpFragment();
        TestPresenter testPresenter = new TestPresenter(testMvpFragment);
        FragmentUtils.addActivityToFragment(supportFragmentManager,testMvpFragment,R.id.fl_container);
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
}
