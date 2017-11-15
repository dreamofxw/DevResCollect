package com.xwtiger.devrescollect.study.androidapi.img;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.xwtiger.devrescollect.R;

/**
 * Created by Busap-112 on 2017/11/14.
 * setTranslate(float dx,float dy)：控制Matrix进行位移。
 setSkew(float kx,float ky)：控制Matrix进行倾斜，kx、ky为X、Y方向上的比例。
 setSkew(float kx,float ky,float px,float py)：控制Matrix以px、py为轴心进行倾斜，kx、ky为X、Y方向上的倾斜比例。
 setRotate(float degrees)：控制Matrix进行depress角度的旋转，轴心为（0,0）。
 setRotate(float degrees,float px,float py)：控制Matrix进行depress角度的旋转，轴心为(px,py)。
 setScale(float sx,float sy)：设置Matrix进行缩放，sx、sy为X、Y方向上的缩放比例。
 setScale(float sx,float sy,float px,float py)：设置Matrix以(px,py)为轴心进行缩放，sx、sy为X、Y方向上的缩放比例。
 *
 *
 *
 * 图片在内存中存放的就是一个一个的像素点，
 * 而对于图片的变换主要是处理图片的每个像素点，
 * 对每个像素点进行相应的变换，即可完成对图像的变换
 *
 * Matrix调用一系列set,pre,post方法时,可视为将这些方法插入到一个队列.当然,按照队列中从头至尾的顺序调用执行.
 其中pre表示在队头插入一个方法,post表示在队尾插入一个方法.
 而set表示把当前队列清空,并且总是位于队列的最中间位置.
 当执行了一次set后:pre方法总是插入到set前部的队列的最前面,post方法总是插入到set后部的队列的最后面


 *
 */

public class MatrixStudy {


    public static void bitmapScale(ImageView iv , float x, float y) {

//        BitmapDrawable bimapDrawable = (BitmapDrawable) iv.getDrawable();
//        Bitmap baseBitmap = bimapDrawable.getBitmap();

        Bitmap baseBitmap = BitmapFactory.decodeResource(iv.getResources(), R.mipmap.icon_exchangegold_bg);



        // 因为要将图片放大，所以要根据放大的尺寸重新创建Bitmap
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() * x),
                (int) (baseBitmap.getWidth() * x), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);

        canvas.drawColor(Color.parseColor("#ff0000"));
        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 根据传入的参数设置缩放比例

        //matrix.setRotate(10);
        matrix.setScale(x, y);
        //matrix.preScale(0.5f,0.5f);
        // 根据缩放比例，把图片draw到Canvas上
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setStrokeWidth(20);

        Paint paint1 = new Paint();
        paint1.setColor(Color.parseColor("#00ff00"));
        paint1.setAntiAlias(true);

        Matrix matrix1 = new Matrix();



        matrix1.setScale(x,x);


        BitmapShader bitmapShader = new BitmapShader(baseBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        bitmapShader.setLocalMatrix(matrix1);

        paint1.setShader(bitmapShader);

        float fact = 5;

        float fr = (baseBitmap.getWidth() * x)-fact;

        RectF rect = new RectF(fact,fact,fr,fr);

        //canvas.drawCircle((baseBitmap.getWidth() * x)/2,(baseBitmap.getWidth() * x)/2,(baseBitmap.getWidth() * x)/2,paint);
        canvas.drawRoundRect(rect,fr/2,fr/2,paint1);


        //canvas.drawBitmap(baseBitmap, matrix, paint);
        iv.setImageBitmap(afterBitmap);

    }


    private Bitmap drawableToBitamp(Drawable drawable)
    {
        if (drawable instanceof BitmapDrawable)
        {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }






}
