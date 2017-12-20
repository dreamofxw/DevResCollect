package com.xwtiger.devrescollect.study.androidapi.img.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xwtiger.devrescollect.R;

/**
 * Created by Busap-112 on 2017/12/20.
 */

public class TestColorFilterView extends View {

       /**
     * 每个取值范围是0-255
     */
    private final static float[] MATRIX = new float[] {
            0.5f, 0, 0, 0, 0,
            0, 0.5f, 0, 0, 0,
            0, 0, 0.5f, 0, 0,
            0, 0, 0, 1, 0 };

    public TestColorFilterView(Context context) {
        super(context);
    }

    public TestColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawView3(canvas);
    }

    /**
     *
     * @param canvas
     */
    public void drawView(Canvas canvas){

        /*Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.icon_fengjin);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(0.2f,0.2f);
        Bitmap bitmapnew = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);*/

        Bitmap bitmapnew = createBitmap();

        Paint paint = new Paint();
        canvas.drawBitmap(bitmapnew, 100, 10, paint);

        //MATRIX中的值是0.5使得R,G,B通道的值都减半了，所以第二张图看起来暗了很多
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(MATRIX);
        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmapnew, 100, bitmapnew.getHeight()+20, paint);

        //饱和度设置为了0，这样一来就变为了黑白图片
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bitmapnew, 100, bitmapnew.getHeight()*2+30, paint);
    }

    /**
     * mul代表multiply,也就是乘法
        add代表加法，也就是颜色偏移量
     那么这里将怎么计算颜色值呢，其实跟上面矩阵的方式差不多。
     这里两个参数都是int类型，所以每8位被分别分解为RGB对应的值来做乘法和加法
     * @param canvas
     */
    public void drawView2(Canvas canvas){

        Bitmap bitmapnew = createBitmap();
        Paint paint = new Paint();
        canvas.drawBitmap(bitmapnew, 100, 10, paint);

        /**
         *  mul代表multiply,也就是乘法
         add代表加法，也就是颜色偏移量
         Given a source color RGB, the resulting R'G'B' color is computed thusly:
         * <pre>
         * R' = R * colorMultiply.R + colorAdd.R
         * G' = G * colorMultiply.G + colorAdd.G
         * B' = B * colorMultiply.B + colorAdd.B
         * </pre>
         */
        LightingColorFilter filter = new LightingColorFilter(0x888888, 0x000000);
        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmapnew, 100, bitmapnew.getHeight()+20, paint);

        LightingColorFilter filter2 = new LightingColorFilter(0x888888, 0x555555);
        paint.setColorFilter(filter2);
        canvas.drawBitmap(bitmapnew, 100, bitmapnew.getHeight()*2+30, paint);

    }


    public void drawView3(Canvas canvas){
        Bitmap bitmapnew = createBitmap();

        Paint paint = new Paint();
        canvas.drawBitmap(bitmapnew, 100, 10, paint);

        PorterDuffColorFilter filter = new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmapnew, 100, bitmapnew.getHeight()+20, paint);

    }


    public Bitmap createBitmap(){
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.icon_fengjin);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(0.2f,0.2f);
        Bitmap bitmapnew = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        return bitmapnew;
    }

}
