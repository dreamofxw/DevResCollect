package com.xwtiger.devrescollect.study.androidapi.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Busap-112 on 2018/1/10.
 */

public class TestViewGroup extends ViewGroup {


    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.d("testview","---------------onMeasure parent start----------------------");
        Log.d("testview","widthMeasureSpec = "+widthMeasureSpec);
        Log.d("testview","heightMeasureSpec = "+heightMeasureSpec);
        Log.d("testview","widthMode = "+widthMode);
        Log.d("testview","heightMode = "+heightMode);
        Log.d("testview","widthSize = "+widthSize);
        Log.d("testview","heightSize = "+heightSize);
        Log.d("testview"," EXACTLY= "+MeasureSpec.EXACTLY);
        Log.d("testview","AT_MOST = "+MeasureSpec.AT_MOST);
        Log.d("testview","UNSPECIFIED = "+MeasureSpec.UNSPECIFIED);

        Log.d("testview","---------------onMeasure parent end---------------------- ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("testview","---------------onLayout parent start----------------------");
        Log.d("testview","---------------onLayout parent END----------------------");
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childAt = getChildAt(i);
//            childAt.layout(l,t,r,b);
//        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Log.d("testview","---------------onDraw parent start----------------------");
        Log.d("testview","---------------onDraw parent END----------------------");

        //super.onDraw(canvas);
    }
}
