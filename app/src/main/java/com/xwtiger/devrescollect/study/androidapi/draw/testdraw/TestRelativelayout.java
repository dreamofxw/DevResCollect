package com.xwtiger.devrescollect.study.androidapi.draw.testdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class TestRelativelayout extends RelativeLayout {


    public TestRelativelayout(Context context) {
        super(context);
    }

    public TestRelativelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("testdrawprocess", "onMeasure: 一级测量大小");

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("testdrawprocess", "onLayout: 一级测量位置");

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.d("testdrawprocess", "draw: 一级绘制");

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d("testdrawprocess", "dispatchDraw: 一级分发绘制");

    }
}
