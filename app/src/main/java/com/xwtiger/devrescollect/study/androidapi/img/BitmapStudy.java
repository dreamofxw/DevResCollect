package com.xwtiger.devrescollect.study.androidapi.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.utils.CopressImageUtil;

/**
 * Created by Busap-112 on 2017/12/18.
 */

public class BitmapStudy {


    /**
     * 缩放
     * @return
     */
    public static Bitmap getScaleBitmap(Context context) {
        BitmapDrawable mBitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap mBitmap = mBitmapDrawable.getBitmap();
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(0.75f, 0.75f);
        Bitmap mScaleBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, true);

        return mScaleBitmap;
    }

    /**
     * 为图像添加倒影效果之后，图像看起来会有立体感，更有真实感，
     * 在Android中使用Matrix类可以很容易实现图像的倒影效果。
     * 主要是Matrix的preScale方法的使用，
     * 给它设置负数缩放比例，图像就会进行反转。然后通过设置Shader添加渐变效果。
     *
     * @param context
     * @return
     */
    public static Bitmap getReflectedBitmap(Context context) {
        BitmapDrawable mBitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap mBitmap = mBitmapDrawable.getBitmap();
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        Log.d("getReflectedBitmap","width ="+width);
        Log.d("getReflectedBitmap","height ="+height);
        Matrix matrix = new Matrix();
        // 图片缩放，x轴变为原来的1倍，y轴为-1倍,实现图片的反转
        matrix.preScale(1, -1);

        //创建反转后的图片Bitmap对象，图片高是原图的一半。
        //Bitmap mInverseBitmap = Bitmap.createBitmap(mBitmap, 0, height/2, width, height/2, matrix, false);
        //创建标准的Bitmap对象，宽和原图一致，高是原图的1.5倍。
        //注意两种createBitmap的不同
        //Bitmap mReflectedBitmap = Bitmap.createBitmap(width, height*3/2, Config.ARGB_8888);

        Bitmap mInverseBitmap = Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
        Bitmap mReflectedBitmap = Bitmap.createBitmap(width, height*2, Bitmap.Config.ARGB_8888);

        // 把新建的位图作为画板
        Canvas mCanvas = new Canvas(mReflectedBitmap);
        //绘制图片
        mCanvas.drawBitmap(mBitmap, 0, 0, null);
        mCanvas.drawBitmap(mInverseBitmap, 0, height, null);

        //添加倒影的渐变效果
        Paint mPaint = new Paint();
//        Shader mShader = new LinearGradient(0, height, 0, mReflectedBitmap.getHeight(), 0x70ffffff, 0x00ffffff, Shader.TileMode.MIRROR);
//        mPaint.setShader(mShader);
        //设置叠加模式
        //mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制遮罩效果
        mPaint.setColor(Color.parseColor("#ff0000"));
        mCanvas.drawRect(0, height, width, mReflectedBitmap.getHeight(), mPaint);

        return mReflectedBitmap;
    }



    public static Bitmap test(Context context){
       /* BitmapDrawable mBitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.mipmap.ic_launcher);
        Bitmap mBitmap = mBitmapDrawable.getBitmap();*/
        Bitmap mBitmap = CopressImageUtil.createBitmap(context, 0.2f, 0.2f);
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        int stride = width+10;

        int[] pixels = new int[(width/2)*height];
        mBitmap.getPixels(pixels,0,width/2,0,0,width/2,height/2);
        Bitmap bitmapnew = Bitmap.createBitmap(pixels, width/2 , height/2 , Bitmap.Config.ARGB_8888);
        Bitmap bitmap = Bitmap.createBitmap( width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.parseColor("#ffffff"));
        canvas.drawBitmap(bitmapnew,0,0,null);

        return bitmap;
    }




}
