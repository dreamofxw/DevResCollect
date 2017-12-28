package com.xwtiger.devrescollect.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.utils.CopressImageUtil;

/**
 * Created by Busap-112 on 2017/12/28.
 */

public class TestTransitonDitailActivity extends BaseActivity {

    private ImageView iv_ditail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transitionditail);

    }

    @Override
    public void initView() {
        iv_ditail = (ImageView) findViewById(R.id.iv_ditail);
        iv_ditail.setImageBitmap(CopressImageUtil.createBitmap(mContext,0.8f,0.8f));
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        iv_ditail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_ditail:
                finish();
                //overridePendingTransition(R.anim.enterac_back,R.anim.exitac_back);
                break;
        }
    }
}
