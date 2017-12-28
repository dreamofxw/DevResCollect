package com.xwtiger.devrescollect.study.androidapi.img.activity;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
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
    private TextView tv_testpaint;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testfontmetrics);
    }

    @Override
    public void initView() {
        fontMetricsView = (TestFontMetricsView) findViewById(R.id.tv_testfont);
        tv_testpaint = (TextView) findViewById(R.id.tv_testpaint);
    }

    @Override
    public void initData() {

        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dimen_100);
        Log.d("TestFontMetricsattr","dimensionPixelSize ="+dimensionPixelSize);
        String str ="testpaint";
        tv_testpaint.setText(str);
        Paint paint = tv_testpaint.getPaint();

        Rect rect = new Rect();
        paint.getTextBounds(str,0,str.length(),rect);
        Log.d("TestFontMetricsattr","rect.top ="+rect.top);
        Log.d("TestFontMetricsattr","rect.left ="+rect.left);
        Log.d("TestFontMetricsattr","rect.right ="+rect.right);
        Log.d("TestFontMetricsattr","rect.bottom ="+rect.bottom);


        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        Log.d("TestFontMetricsattr","top ="+fontMetrics.top);
        Log.d("TestFontMetricsattr","ascent ="+fontMetrics.ascent);
        Log.d("TestFontMetricsattr","descent ="+fontMetrics.descent);
        Log.d("TestFontMetricsattr","bottom ="+fontMetrics.bottom);


        ViewTreeObserver viewTreeObserver = tv_testpaint.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = tv_testpaint.getWidth();
                int height = tv_testpaint.getHeight();
                Log.d("TestFontMetricsattr","width ="+width);
                Log.d("TestFontMetricsattr","height ="+height);
                Log.d("TestFontMetricsattr","getPaddingLeft ="+tv_testpaint.getPaddingLeft());
                Log.d("TestFontMetricsattr","getPaddingBottom ="+tv_testpaint.getPaddingBottom());
                Log.d("TestFontMetricsattr","getPaddingTop ="+tv_testpaint.getPaddingTop());
                Log.d("TestFontMetricsattr","getPaddingRight ="+tv_testpaint.getPaddingRight());
                tv_testpaint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
