package com.xwtiger.devrescollect.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Switch;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

/**
 * Created by Busap-112 on 2017/12/25.
 */

public class SwitchActivity extends BaseActivity {


    private Switch mSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_switch);


    }

    @Override
    public void initView() {
        mSwitch = (Switch) findViewById(R.id.switchtoggle);
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
