package com.xwtiger.devrescollect.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import com.xwtiger.devrescollect.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class CopressImageUtil {
    public Bitmap getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;
        float ww = 480f;
        //缩放比，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = compressImage(BitmapFactory.decodeFile(srcPath, newOpts));
        return bitmap;//压缩好比例大小后再进行质量压缩
    }

    public Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 300) {    //循环判断如果压缩后图片是否大于300kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static Bitmap createBitmap(Context context,float sx ,float sy){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_fengjin);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(sx,sy);//0.2f,0.2f
        Bitmap bitmapnew = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        return bitmapnew;
    }





    //test
    public static Bitmap getimage1(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空
        Log.d("testcompressUtils", "getimage1: bitmap==null?"+(bitmap==null));

        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        Log.d("testcompressUtils", "getimage1原始大小: w="+w+",h="+h);
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 1024f;
        float ww = 768f;
        //缩放比，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;

        newOpts.inJustDecodeBounds = false;

        //newOpts.inSampleSize = be;//设置缩放比例
        Log.d("testcompressUtils", "getimage1: be="+be);
        newOpts.inSampleSize = be;//设置缩放比例


        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = compressImage1(BitmapFactory.decodeFile(srcPath, newOpts));
        return bitmap;//压缩好比例大小后再进行质量压缩
    }

    public static Bitmap compressImage1(Bitmap image) {

        int byteCount1 = image.getByteCount()/1024;
        Log.d("testcompressUtils", "compressImage1: enter byteCount1 ="+byteCount1);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 90, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

        Log.d("testcompressUtils", "compressImage1: 压缩前的的大小"+baos.toByteArray().length / 1024);
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {    //循环判断如果压缩后图片是否大于300kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        Log.d("testcompressUtils", "compressImage1: options ="+options);
        Log.d("testcompressUtils", "compressImage1: 压缩后的的大小"+baos.toByteArray().length / 1024);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        Log.d("testcompressUtils", "compressImage1: getWidth ="+bitmap.getWidth());
        Log.d("testcompressUtils", "compressImage1: getHeight ="+bitmap.getHeight());
        int byteCount = bitmap.getByteCount()/1024;
        Log.d("testcompressUtils", "compressImage1: 结果byteCount ="+byteCount);

        return bitmap;
    }




    public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }


    /**
     * 等比缩放
     * @param file
     * @return
     */
    public static Bitmap compressImageOfSample(File file){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(file.getAbsolutePath(),options);

        int outWidth = options.outWidth;
        int outHeight = options.outHeight;


        options.inScaled = true;
        options.inDensity = outWidth;
        options.inTargetDensity = 768;
        //1024X768

        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);


        Log.d("testcompressUtils", "compressImageOfSample: getWidth ="+bitmap.getWidth());
        Log.d("testcompressUtils", "compressImageOfSample: getHeight ="+bitmap.getHeight());
        return bitmap;
    }




}