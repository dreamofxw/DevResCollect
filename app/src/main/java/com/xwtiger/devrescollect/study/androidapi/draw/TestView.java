package com.xwtiger.devrescollect.study.androidapi.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Busap-112 on 2018/1/10.
 */

public class TestView extends View {


    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //context.obtainStyledAttributes(attrs, )
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        Log.d("testview","---------------onMeasure start----------------------");
        Log.d("testview","widthMeasureSpec = "+widthMeasureSpec);
        Log.d("testview","heightMeasureSpec = "+heightMeasureSpec);
        Log.d("testview","widthMode = "+widthMode);
        Log.d("testview","heightMode = "+heightMode);
        Log.d("testview","widthSize = "+widthSize);
        Log.d("testview","heightSize = "+heightSize);
        Log.d("testview"," EXACTLY= "+MeasureSpec.EXACTLY);
        Log.d("testview","AT_MOST = "+MeasureSpec.AT_MOST);
        Log.d("testview","UNSPECIFIED = "+MeasureSpec.UNSPECIFIED);




        Log.d("testview","---------------onMeasure end---------------------- ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d("testview","---------------onLayout start----------------------");
        Log.d("testview","---------------onLayout END----------------------");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("testview","---------------onDraw start----------------------");
        Log.d("testview","---------------onDraw END----------------------");
        canvas.drawText("test",20,20,new Paint());
    }
}
