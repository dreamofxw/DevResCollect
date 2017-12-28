package com.xwtiger.devrescollect.study.androidapi.img.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Busap-112 on 2017/12/20.
 *
 *  ascent = ascent线的y坐标 - baseline线的y坐标；//负数
    descent = descent线的y坐标 - baseline线的y坐标；//正数
    top = top线的y坐标 - baseline线的y坐标；//负数
    bottom = bottom线的y坐标 - baseline线的y坐标；//正数
    leading = top线的y坐标 - ascent线的y坐标；//负数
    FontMetrics的这几个变量的值都是以baseLine为基准的，
    对于ascent来说，baseline线在ascent线之下，
    所以必然baseline的y值要大于ascent线的y值，
    所以ascent变量的值是负的。其他几个同理。
 *
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
        drawView3(canvas);

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
        canvas.drawLine(150,150,getWidth()-10,150,mPaint);

    }

    /**
     * 绘制想x,y坐标
     * @param canvas
     */
    public void drawView2(Canvas canvas){
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);

        canvas.drawText("abcdefghijk",150,150,mPaint);
        mPaint.setColor(Color.parseColor("#23AC3B"));
        canvas.drawLine(0,150,getWidth(),150,mPaint);
        canvas.drawLine(150,0,150,getHeight(),mPaint);
    }

    /**
     *
     * @param canvas
     */
    public void drawView3(Canvas canvas){
        int baseLineY = 200;
        Paint mPaint = new Paint();
        mPaint.setTextSize(50);
        String string = "abcdefghijkl's";

        canvas.drawText(string, 100, baseLineY, mPaint);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        Log.d("fontMetrics_attr","fontMetrics.top = "+fontMetrics.top);
        Log.d("fontMetrics_attr","fontMetrics.ascent = "+fontMetrics.ascent);
        Log.d("fontMetrics_attr","fontMetrics.descent = "+fontMetrics.descent);
        Log.d("fontMetrics_attr","fontMetrics.bottom = "+fontMetrics.bottom);
        float top = fontMetrics.top + baseLineY;
        float ascent = fontMetrics.ascent + baseLineY;
        float descent = fontMetrics.descent + baseLineY;
        float bottom = fontMetrics.bottom + baseLineY;

        //绘制基线
        mPaint.setColor(Color.parseColor("#FF1493"));
        canvas.drawLine(0, baseLineY, getWidth(), baseLineY, mPaint);

        //绘制top直线
        mPaint.setColor(Color.parseColor("#FFB90F"));
        canvas.drawLine(0, top, getWidth(), top, mPaint);

        //绘制ascent直线
        mPaint.setColor(Color.parseColor("#b03060"));
        canvas.drawLine(0, ascent, getWidth(), ascent, mPaint);

        //绘制descent直线
        mPaint.setColor(Color.parseColor("#912cee"));
        canvas.drawLine(0, descent, getWidth(), descent, mPaint);

        //绘制bottom直线
        mPaint.setColor(Color.parseColor("#1E90FF"));
        canvas.drawLine(0, bottom, getWidth(), bottom, mPaint);

        mPaint.setColor(Color.parseColor("#230000ff"));
        float width = mPaint.measureText(string);

        Rect recttest = new Rect();
        mPaint.getTextBounds(string,0,string.length(),recttest);

        //canvas.drawRect(100,ascent,100+width,descent,mPaint);
        canvas.drawRect(100,ascent,100+recttest.right -recttest.left,(ascent+recttest.bottom -recttest.top),mPaint);

        Rect rect = new Rect();
        mPaint.getTextBounds(string,0,string.length(),rect);
        Log.d("fontMetrics_attr","getTextBounds height= "+(rect.bottom -rect.top));
        Log.d("fontMetrics_attr","getTextBounds width= "+(rect.right -rect.left));

        float fontwidht = mPaint.measureText(string);
        Log.d("fontMetrics_attr","measureText width= "+fontwidht);
        Log.d("fontMetrics_attr","descent -ascent= "+(descent -ascent));

    }


    public void drawView4(Canvas canvas){
        int count = 5;
        int offset = 15;
        int startx = 40;
        int starty = 40;
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.parseColor("#ff0020"));
        float[] f = new float[5*2];
        for(int i =0;i<count;i++){
            f[i*2] = startx +i*offset;
            f[i*2+1] = starty;
        }
        canvas.drawPoints(f,0,count<<1,paint);
        canvas.translate(0,15);
        paint.setColor(Color.parseColor("#0000ff"));
        canvas.drawPoints(f,2,6,paint);
    }



}
