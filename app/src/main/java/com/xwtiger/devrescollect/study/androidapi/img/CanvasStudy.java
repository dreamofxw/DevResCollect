package com.xwtiger.devrescollect.study.androidapi.img;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 *android绘画底层用的是skia函数库；
 *skia是个2D向量图形处理函数库，包含字型、坐标转换，以及点阵图都有高效能且简洁的表现。
 * 不仅用于Google Chrome浏览器，
 * 新兴的Android开放手机平台也采用skia作为绘图处理，
 * 搭配OpenGL/ES与特定的硬件特征，强化显示的效果；
 * 作为Google Android平台的图形引擎
 *
 * paints不保存内部状态的堆栈（也就是没有save/restore），
 * paint是轻量级的，所以用户可以为每个特殊的用途创建并保存许多paint对象，
 * 从canvas中分离颜色和字体等属性，放在paint中，可以使canvas的save/restore更加有效。
 * canvas所做的就是保存matrix和clip设置的堆栈。
 *
 *12条Porter-Duff规则及其表示的含义：
     PorterDuff.Mode.CLEAR 清除画布上图像
     PorterDuff.Mode.SRC 显示上层图像
     PorterDuff.Mode.DST 显示下层图像
     PorterDuff.Mode.SRC_OVER上下层图像都显示，上层层居上显示
     PorterDuff.Mode.DST_OVER 上下层都显示,下层居上显示
     PorterDuff.Mode.SRC_IN 取两层图像交集部分,只显示上层图像
     PorterDuff.Mode.DST_IN 取两层图像交集部分,只显示下层图像
     PorterDuff.Mode.SRC_OUT 取上层图像非交集部分
     PorterDuff.Mode.DST_OUT 取下层图像非交集部分
     PorterDuff.Mode.SRC_ATOP 取下层图像非交集部分与上层图像交集部分
     PorterDuff.Mode.DST_ATOP 取上层图像非交集部分与下层图像交集部分
     PorterDuff.Mode.XOR 取两层图像的非交集部分
 *
 * 裁剪
 * 那么他们什么含义呢？ 形象的举个例子
     A:表示第一个裁剪的形状;
     B:表示第二次裁剪的形状;
     Region.Op.DIFFERENCE ：是A形状中不同于B的部分显示出来
     Region.Op.REPLACE：是只显示B的形状
     Region.Op.REVERSE_DIFFERENCE ：是B形状中不同于A的部分显示出来，这是没有设置时候默认的
     Region.Op.INTERSECT：是A和B交集的形状
     Region.Op.UNION：是A和B的全集
     Region.Op.XOR：是全集形状减去交集形状之后的部分
 *
 *  path
*   Path.Direction.CCW
    逆时针
    Path.Direction.CW
    顺时针
 *
 * 定义了平铺的3种模式：
    Shader.TileMode CLAMP: 边缘拉伸.
    Shader.TileMode MIRROR：在水平方向和垂直方向交替景象, 两个相邻图像间没有缝隙.
    Shader.TillMode REPETA：在水平方向和垂直方向重复摆放,两个相邻图像间有缝隙缝隙.
 *
 *
 *Shader的直接子类:
 BitmapShader    : 位图图像渲染
 LinearGradient  : 线性渲染
 RadialGradient  : 环形渲染
 SweepGradient   : 扫描渐变渲染/梯度渲染
 ComposeShader   : 组合渲染，可以和其他几个子类组合起来使用
 *
 *
 *
 *
 *
 *
 *
 *
 * Created by Busap-112 on 2017/12/15.
 */

public class CanvasStudy {

    /**
     * 1,drawArc() 中的(boolean useCenter)参数
     * 这个参数的作用是设置我们的圆弧在绘画的时候，是否经过圆形
     值得注意的是，这个参数在我们的 mPaint.setStyle(Paint.Style.STROKE); 设置为描边属性的时候，是看不出效果的。
     *
     *
     *
     *
     *
     *
     */




    public static void main(String[] args){


        test();
    }


    public static int x = 0;

    public static void test(){
        //Canvas
        Paint paint = new Paint();

        for(int i =0;i<4;i++){
            x = (x+1)%4;
            System.out.println("x ="+x);
        }

    }



}
