package com.xwtiger.devrescollect.study.androidapi.draw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

/**
 * Created by Busap-112 on 2018/1/2.
 */

public class TestRequestAndInvalidActivity extends BaseActivity {

    private TextView tv_req;
    private TextView tv_inv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_testreqandinv);
    }

    @Override
    public void initView() {
        tv_req = (TextView) findViewById(R.id.tv_req);
        tv_inv = (TextView) findViewById(R.id.tv_inv);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        tv_req.setOnClickListener(this);
        tv_inv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_req:
                tv_req.requestLayout();
                break;
            case R.id.tv_inv:
                tv_inv.invalidate();
                break;
        }
    }
}
