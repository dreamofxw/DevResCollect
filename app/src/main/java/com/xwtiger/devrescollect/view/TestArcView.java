package com.xwtiger.devrescollect.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xwtiger.devrescollect.utils.Utils;

public class TestArcView extends View{

    private Context mContext;
    private Paint mPaint;

    public TestArcView(Context context) {
        super(context);
        init(context);
    }

    public TestArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public TestArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }


    public void init(Context context){
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#90ff0000"));
        mPaint.setAntiAlias(true);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //mPaint.setColor(Color.parseColor("#0000ff"));

        RectF rectF1 = new RectF(0,0, Utils.getScreenWidth(mContext),300);
        canvas.drawRect(rectF1,mPaint);

        //mPaint.setColor(Color.parseColor("#90ff0000"));

        RectF rectF = new RectF(-300,0, Utils.getScreenWidth(mContext)+300,600);
        canvas.drawArc(rectF,0,180,true,mPaint);//顺时针

        //canvas.drawColor(Color.parseColor("#ff0000"));

    }


    public void changBackground(String colorstr,float alpha){
        mPaint.setColor(Color.parseColor(colorstr));
        mPaint.setAlpha((int)(255*alpha));
        invalidate();
    }


}
