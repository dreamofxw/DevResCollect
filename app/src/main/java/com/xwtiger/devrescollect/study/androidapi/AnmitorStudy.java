package com.xwtiger.devrescollect.study.androidapi;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Transition;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Busap-112 on 2017/11/8.
 *
 * 动画研究
 *
 * Animation
 *
 *
 * Animator 属性动画 property animation
 * 子类AnimatorSet,ValueAnimator
 *
 * TimeInterpolator与TypeEvaluator共同作用在ValueAnimator上，
 * 通过复合的方式产生最后的数据，这也就是数学上的『复合函数』，
 * TimeInterpolator控制在何时取值，
 * 而TypeEvaluator控制在当前时间点需要取多少值
 *
 *
 * sequentially 按照顺序（串行）
 * simultaneously 同时（并行）
 *
 *
 * evaluator 求值程序
 *
 * 简单的概括，就是：
 * TimeInterpolator控制动画的速度，
 * 而TypeEvaluator控制动画的值，他们可以共同作用，
 * 也可以单独作用（让另一个使用默认值）。
 实际上，TypeEvaluator中的一个参数fraction，
 就是『复合函数』中TimeInterpolator计算的结果。
 即fraction=getInterpolation()。
 *
 *
 *
 * setTranslationY();是添加了坐标位移但是控件本身的y坐标还是在原来的位置
 *
 *
 * 下面这个网站可以设置自己的曲线函数，
 * http://inloop.github.io/interpolator/
 */

public class AnmitorStudy {

    /**
     *
     * 简介
     * propertyName: View.X.getName();View.Y.getName(),View.SCALE_X.getName()
     *
     * View.SCALE_Y.getName()
     *
     */

    public static void test3(View target){

//        ObjectAnimator.ofInt(target, new Property<View, Integer>("","x") {
//            @Override
//            public Integer get(View object) {
//                return null;
//            }
//        },target.getX(),target.getX()+target.getWidth());



    }


    public static void test2(View target){

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", target.getX()+target.getWidth());
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", target.getY()+target.getHeight());
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(target, pvhX, pvhY);
        objectAnimator.start();
        Log.d("testobjectAnimator",objectAnimator.toString());
    }



    public static void test1(View target){
        //myView.animate().x(50f).y(100f);
        //target.animate().x(target.getX()+target.getWidth());
        target.animate().x(target.getX()+target.getWidth()).scaleX(2.0f).alpha(1).setDuration(1000);
    }


    public static void testNoValue(View target){
        ObjectAnimator animator = ObjectAnimator.ofFloat(target,View.X.getName(),target.getX()+target.getWidth());
        animator.setInterpolator(new OvershootInterpolator());
        animator.setDuration(1000);
        animator.start();
    }

    public static void test(){
        Log.d("testProperty","x ="+View.X.getName());
        Log.d("testProperty","y ="+View.Y.getName());
        Log.d("testProperty","scale_x ="+View.SCALE_X.getName());
    }


    public static void testValueAnmitor(final View target){
        float x = target.getX();
        float y = target.getY();

        int height = target.getHeight();
        final int width = target.getWidth();
        Log.d("testValueAnmitor","width ="+(width));
        Log.d("testValueAnmitor","y ="+(y));
        Log.d("testValueAnmitor","x ="+(x));
        Log.d("testValueAnmitor","height ="+(height));
        Log.d("testValueAnmitor","top ="+(target.getTop()));
        Log.d("testValueAnmitor","left ="+(target.getLeft()));
        Log.d("testValueAnmitor","getTranslationX ="+(target.getTranslationX()));
        Log.d("testValueAnmitor","getTranslationY ="+(target.getTranslationY()));




        AnimatorSet animatorSet = new AnimatorSet();

        float startx = 0;
        if(target.getTranslationX() >0){
            startx +=target.getTranslationX();
        }

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startx,startx+1.3f*width);
        valueAnimator.setDuration(2000);
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d("leftmargin","animatorend left="+target.getLeft());
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //setTranslationX();是添加了坐标位移但是控件本身的x坐标还是在原来的位置
                target.setTranslationX((float)animation.getAnimatedValue());
                //Log.d("testValueAnmitor","animation ="+animation.getAnimatedValue());
            }
        });

        float startvalue = 0;
        if(target.getTranslationY() >0){
            startvalue +=target.getTranslationY();
        }
        //offloat 第一个参数基本都是0因为setTranslationY();是添加了坐标位移但是控件本身的y坐标还是在原来的位置
        ValueAnimator valueAnimatory = ValueAnimator.ofFloat(startvalue,startvalue+2*height);
        valueAnimatory.setDuration(2000);
        valueAnimatory.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("leftmargin","end getTop ="+target.getTop());
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimatory.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //setTranslationY();是添加了坐标位移但是控件本身的y坐标还是在原来的位置
                target.setTranslationY((float)animation.getAnimatedValue());
                //Log.d("testValueAnmitor","animation y ="+animation.getAnimatedValue());
            }
        });

        //animatorSet.playSequentially(valueAnimator,valueAnimatory);
        animatorSet.playSequentially(valueAnimatory,valueAnimator);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.start();

    }


    public static void testAnmitor(View target){
        float x = target.getX();
        float y = target.getY();
        int height = target.getHeight();
        int width = target.getWidth();

        AnimatorSet animatorset = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, View.X.getName(), x,x+1.2f*width);
        objectAnimator.setDuration(1500);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(target, View.Y.getName(), y,1.1f*(y+height));
        objectAnimator1.setDuration(1500);

        ObjectAnimator objectAnimator_scale = ObjectAnimator.ofFloat(target, View.SCALE_X.getName(), 1.0f, 1.5f,1.0f);
        objectAnimator_scale.setDuration(1500);

        ObjectAnimator objectAnimator_scaley = ObjectAnimator.ofFloat(target, View.SCALE_Y.getName(), 1.0f,1.5f,1.0f);
        objectAnimator_scaley.setDuration(1500);


        float rotation = target.getRotation();
        ObjectAnimator objectAnimator_rotation = ObjectAnimator.ofFloat(target, View.ROTATION.getName(), rotation, rotation+45,rotation);
        objectAnimator_rotation.setDuration(1500);

        ObjectAnimator objectAnimator_alpha = ObjectAnimator.ofFloat(target, View.ALPHA.getName(), 1, 0);
        objectAnimator_alpha.setDuration(1500);

        //animatorset.playTogether(objectAnimator,objectAnimator_scale,objectAnimator_scaley,objectAnimator_rotation,objectAnimator1,objectAnimator_alpha);

        animatorset.playSequentially(objectAnimator,objectAnimator_scale,objectAnimator_scaley,objectAnimator_rotation,objectAnimator1);
        animatorset.setInterpolator(new AccelerateDecelerateInterpolator());

        animatorset.start();

    }

    public static void reversalAnmitor(View target,float endpoint){
        float x = target.getX();
        float y = target.getY();
        int height = target.getHeight();
        int width = target.getWidth();

        AnimatorSet animatorset = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(target, "X", x,0);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(target, "Y", y,endpoint);
        //animatorset.playTogether(objectAnimator,objectAnimator1);
        animatorset.playSequentially(objectAnimator,objectAnimator1);
        animatorset.start();
    }

    /**
     * 转场动画
     */
    public void testActivityTransition(){

    }

}
