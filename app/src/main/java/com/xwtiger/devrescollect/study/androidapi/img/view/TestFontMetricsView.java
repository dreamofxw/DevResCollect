package com.xwtiger.devrescollect.study.androidapi.img.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Busap-112 on 2017/12/20.
 */

public class TestFontMetricsView extends View {
    public TestFontMetricsView(Context context) {
        super(context);
    }

    public TestFontMetricsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestFontMetricsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawView(canvas);

    }

    /**
     * 证明drawText 中x y坐标为内容的左下角
     * @param canvas
     */
    public void drawView(Canvas canvas){
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);

        canvas.drawText("abcdefghijk",150,150,mPaint);

        mPaint.setColor(Color.parseColor("#23AC3B"));
        canvas.drawLine(150,150,getWidth(),150,mPaint);

    }


}
