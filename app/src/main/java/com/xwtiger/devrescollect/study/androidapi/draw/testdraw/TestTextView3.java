package com.xwtiger.devrescollect.study.androidapi.draw.testdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class TestTextView3 extends TextView {


    private  String tag = "textview3";

    public TestTextView3(Context context) {
        super(context);
    }

    public TestTextView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTextView3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("testdrawprocess", "onMeasure: "+tag+"测量大小");

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("testdrawprocess", "onLayout: "+tag+"测量位置");

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.d("testdrawprocess", "draw: "+tag+"绘制");

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d("testdrawprocess", "dispatchDraw: "+tag+"分发绘制");

    }
}
