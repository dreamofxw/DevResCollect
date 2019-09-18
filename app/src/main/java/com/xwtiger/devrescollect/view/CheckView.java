package com.xwtiger.devrescollect.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xwtiger.devrescollect.R;


/**
 * Created by songzhf on 2018/3/28.
 */

public class CheckView extends View {
    Paint paint;
    Paint textPaint;
    private static String TEXTCOLOR_C5 = "#ffffff";
    private static String TEXTCOLOR_C1 = "#2d2d2c";

    private String currentTextColor = TEXTCOLOR_C1;

    private int height = (int) getResources().getDimension(R.dimen.x68);
    private int width = (int) getResources().getDimension(R.dimen.x160);

    private int top = (int) getResources().getDimension(R.dimen.x8);
    private int right = (int) getResources().getDimension(R.dimen.x8);

    private int r = (int) getResources().getDimension(R.dimen.x60);//圆弧直径
    private int rectL = (int) getResources().getDimension(R.dimen.x36);//圆弧直径

    private int textSize = (int)getResources().getDimension(R.dimen.x26);

    Paint bitmapPaint;
    Bitmap bitmap;

    private boolean disableClick = false;

    private boolean isSelect = false;

    String text = "哈哈";

    private String selectColor;

    public CheckView(Context context) {
        super(context);
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.parseColor("#ff0000"));//#f7f7f7

        bitmapPaint = new Paint();
        bitmapPaint.setAlpha(255);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pay_icon_alipay);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width+right,height);
    }

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }


    public String getColor1() {
        return currentTextColor;
    }

    public void setColor1(String color) {
        this.currentTextColor = color;
        textPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    private int fade;

    public int getFade() {
        return fade;
    }

    public void setFade(int fade) {
        this.fade = fade;
        bitmapPaint.setAlpha(fade);
        invalidate();
    }

    public boolean isSelected(){
        return isSelect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawColor(0x0000ff);
        RectF oval = new RectF(0, top, r, height);// 画一个椭圆
//        paint.setColor(Color.parseColor("#0000ff"));
//        canvas.drawRect(oval,paint);
//        paint.setColor(Color.parseColor("#ff0000"));

        canvas.drawArc(oval, 90, 180, false, paint);//画左边的半圆
        //canvas.drawArc(oval, 90, 180, false, paint);//画左边的半圆
        //canvas.drawRect(oval,paint);
        //角度是顺时针（0，90，180，360），画弧度是顺时针画（）

        canvas.drawRect(r/2,top, width-r/2, height, paint);

        RectF oval1 = new RectF(width-r, top, width, height);// 画一个椭圆
        canvas.drawArc(oval1, 270, 180, false, paint);//画右边的半圆

        textPaint = new Paint();
        textPaint.setColor(Color.parseColor(currentTextColor));
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.FILL);
        //该方法即为设置基线上那个点到底是left,center,还是right  这里我设置为center
        textPaint.setTextAlign(Paint.Align.CENTER);
//
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top1 = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        int baseLineY = (int) (height/2 - top1/2 - bottom/2+top/2);//基线中间点的y轴计算公式
        canvas.drawText(text,width/2,baseLineY,textPaint);
        Rect rect = new Rect(width+right - rectL ,0,width+right,rectL);
        canvas.drawBitmap(bitmap, null,rect, bitmapPaint);
    }

    public void setText(String text){
        this.text = text;
        invalidate();
    }

    public void setSelectColor(String selectColor){
        this.selectColor = selectColor;
    }

}
