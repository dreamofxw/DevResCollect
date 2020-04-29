package com.xwtiger.devrescollect.study.androidapi.draw.testdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class TestLinearLayout extends LinearLayout {

    public TestLinearLayout(Context context) {
        super(context);
    }

    public TestLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("testdrawprocess", "onMeasure: 二级测量大小");

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("testdrawprocess", "onLayout: 二级测量位置");

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.d("testdrawprocess", "draw: 二级绘制");

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d("testdrawprocess", "dispatchDraw: 二级分发绘制");

    }
}
