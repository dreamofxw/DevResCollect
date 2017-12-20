package com.xwtiger.devrescollect.study.androidapi.img.activity;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.study.androidapi.img.view.TestFontMetricsView;

/**
 *
 * Created by Busap-112 on 2017/12/20.
 *
 */

public class TestFontMetricsActivity extends BaseActivity {

    private TestFontMetricsView fontMetricsView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testfontmetrics);
        init();
    }

    @Override
    public void initView() {
        fontMetricsView = (TestFontMetricsView) findViewById(R.id.tv_testfont);

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
