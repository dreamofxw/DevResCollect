package com.xwtiger.devrescollect.study.androidapi.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by Busap-112 on 2018/1/9.
 */

public class TestDrawLinealayout extends LinearLayout {


    public TestDrawLinealayout(Context context) {
        super(context);
        Log.d("TestTextView"," ----------------parent TestDrawLinealayout(Context context)---------------------");
    }

    public TestDrawLinealayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("TestTextView"," ----------------parent TestDrawLinealayout(Context context, @Nullable AttributeSet attrs)---------------------");
    }

    public TestDrawLinealayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("TestTextView"," ----------------parent TestDrawLinealayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr)---------------------");
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d("TestTextView"," ----------------parent onMeasure start----------------------");


        Log.d("TestTextView","widthMeasureSpec ="+widthMeasureSpec);
        Log.d("TestTextView","heightMeasureSpec ="+heightMeasureSpec);
        Log.d("TestTextView","getWidth ="+getWidth());
        Log.d("TestTextView","getHeight ="+getHeight());
        Log.d("TestTextView","getMeasuredWidth ="+getMeasuredWidth());
        Log.d("TestTextView","getMeasuredHeight ="+getMeasuredHeight());


        Log.d("TestTextView"," ----------------parent onMeasure end----------------------");
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);


        Log.d("TestTextView"," ----------------parent onLayout start----------------------");
        Log.d("TestTextView","left ="+l);
        Log.d("TestTextView","right ="+r);
        Log.d("TestTextView","top ="+t);
        Log.d("TestTextView","bottomer ="+b);

        Log.d("TestTextView","getWidth ="+getWidth());
        Log.d("TestTextView","getHeight ="+getHeight());
        Log.d("TestTextView","getMeasuredWidth ="+getMeasuredWidth());
        Log.d("TestTextView","getMeasuredHeight ="+getMeasuredHeight());
        Log.d("TestTextView"," ----------------parent onLayout end----------------------");


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("TestTextView"," ----------------parent onDraw start----------------------");
        Log.d("TestTextView"," ----------------parent onDraw end----------------------");
    }
}
