package com.xwtiger.devrescollect.study.androidapi.img.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.study.androidapi.img.BitmapStudy;

/**
 * Created by Busap-112 on 2017/12/18.
 */

public class BitmapActivity extends BaseActivity {

    private ImageView iv_dest;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_testbitmap);
        init();
    }

    @Override
    public void initView() {
        iv_dest = (ImageView) findViewById(R.id.iv_dest);
    }

    @Override
    public void initData() {

        iv_dest.setImageBitmap(BitmapStudy.getReflectedBitmap(mContext));
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
