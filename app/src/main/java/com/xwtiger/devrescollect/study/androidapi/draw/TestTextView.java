package com.xwtiger.devrescollect.study.androidapi.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Busap-112 on 2018/1/2.
 */
public class TestTextView extends AppCompatTextView {

    public TestTextView(Context context) {
        super(context);
        Log.d("TestTextView","TestTextView(Context context)");
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("TestTextView","TestTextView(Context context, @Nullable AttributeSet attrs)");
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("TestTextView","TestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)");
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.d("TestTextView","---------------onMeasure start---------------------- ");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("TestTextView","onMeasure");
        Log.d("TestTextView","widthMeasureSpec ="+widthMeasureSpec);
        Log.d("TestTextView","heightMeasureSpec ="+heightMeasureSpec);
        Log.d("TestTextView","widthMode ="+widthMode);
        Log.d("TestTextView","heightMode ="+heightMode);
        Log.d("TestTextView","UNSPECIFIED ="+MeasureSpec.UNSPECIFIED);
        Log.d("TestTextView","EXACTLY ="+MeasureSpec.EXACTLY);
        Log.d("TestTextView","AT_MOST ="+MeasureSpec.AT_MOST);

        Log.d("TestTextView","widthSize ="+widthSize);
        Log.d("TestTextView","heightSize ="+heightSize);

        Log.d("TestTextView","onMeasure getWidth="+getWidth());
        Log.d("TestTextView","onMeasure getHeight="+getHeight());
        Log.d("TestTextView","onMeasure getMeasuredHeight="+getMeasuredHeight());
        Log.d("TestTextView","onMeasure getMeasuredWidth="+getMeasuredWidth());
        Log.d("TestTextView","onMeasure getMeasuredState="+getMeasuredState());
        Log.d("TestTextView","---------------onMeasure end---------------------- ");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        Log.d("TestTextView","----------onLayout start------------");
        Log.d("TestTextView","onLayout");
        Log.d("TestTextView","left ="+left);
        Log.d("TestTextView","top ="+top);
        Log.d("TestTextView","right ="+right);
        Log.d("TestTextView","bottom ="+bottom);
        Log.d("TestTextView","onLayout getWidth="+getWidth());
        Log.d("TestTextView","onLayout getHeight="+getHeight());
        Log.d("TestTextView","onLayout getMeasuredHeight="+getMeasuredHeight());
        Log.d("TestTextView","onLayout getMeasuredWidth="+getMeasuredWidth());
        Log.d("TestTextView","----------onLayout end------------");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Log.d("TestTextView","----------onDraw start------------");
        Log.d("TestTextView","onDraw");

        Log.d("TestTextView","----------onDraw end------------");

        super.onDraw(canvas);
    }
}
