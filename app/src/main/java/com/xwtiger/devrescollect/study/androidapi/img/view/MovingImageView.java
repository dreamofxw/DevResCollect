package com.xwtiger.devrescollect.study.androidapi.img.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Author: tongming
 * Comment: 可以移动的imageview，图片实际高宽比控件大小高宽大
 * Date: 2018-06-24 20:09
 */
public class MovingImageView extends ImageView implements ValueAnimator.AnimatorUpdateListener {

    // 动画时长
    private int duration = 4000;

    private float scaleRatio;
    private final Matrix matrix = new Matrix();

    // 当前位置
    private long currentPlayTime;
    // 动画
    private ValueAnimator animator;

    // 控件高宽
    private float vWidth;
    private float vHeight;
    // 图片高宽
    private float dWidth;
    private float dHeight;

    private int type = 0;

    private boolean isStart = false;
    public MovingImageView(Context context) {
        super(context);
    }

    public MovingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
        if (isStart) {
            start();
        }
    }

    private void init() {
        if (getDrawable() != null) {
            setScaleType(ScaleType.MATRIX);
            // 控件高宽
            vWidth = getMeasuredWidth();
            vHeight = getMeasuredHeight();
            // 图片高宽
            dWidth = getDrawable().getIntrinsicWidth();
            dHeight = getDrawable().getIntrinsicHeight();

            Log.d("testmovingimageview", "init:vWidth ="+vWidth);
            Log.d("testmovingimageview", "init:vHeight ="+vHeight);
            Log.d("testmovingimageview", "init:dWidth ="+dWidth);
            Log.d("testmovingimageview", "init:dHeight ="+dHeight);
            Log.d("testmovingimageview", "init:dWidth/dHeight ="+dWidth/dHeight);
            Log.d("testmovingimageview", "init:vWidth/vHeight ="+vWidth/vHeight);

            if (dWidth / dHeight > vWidth / vHeight) {
                type = 0;
                scaleRatio = vHeight / dHeight;
                matrix.reset();
                matrix.postScale(scaleRatio, scaleRatio);
                setImageMatrix(matrix);
            } else {
                type = 1;
                scaleRatio = vWidth / dWidth;
                matrix.reset();
                matrix.postScale(scaleRatio, scaleRatio);
                setImageMatrix(matrix);
            }
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Log.d("testmovingimageview", "onAnimationUpdate: getAnimatedValue ="+valueAnimator.getAnimatedValue());
        matrix.reset();
        matrix.postScale(scaleRatio, scaleRatio);
        if (type == 1) {
            matrix.postTranslate(0, (Float) valueAnimator.getAnimatedValue());
        } else {
            matrix.postTranslate((Float) valueAnimator.getAnimatedValue(), 0);
        }
        setImageMatrix(matrix);
    }

    public void start() {
        isStart = true;
        type =0;
        if (vHeight > 0 && dHeight > 0 && vWidth > 0 && dWidth > 0) {
            if (animator == null) {
                if (type == 1) {
                    //animator = ValueAnimator.ofFloat(0, vHeight - dHeight * scaleRatio);
                    animator = ValueAnimator.ofFloat(0,-100);
                    animator.setDuration(duration);
                    animator.setRepeatCount(3);
                    animator.setRepeatMode(ValueAnimator.REVERSE);
                    animator.addUpdateListener(this);
                    animator.start();
                } else {
                    //animator = ValueAnimator.ofFloat(0, vWidth - dWidth * scaleRatio);
                    animator = ValueAnimator.ofFloat(0, -100);
                    animator.setDuration(duration);
                    animator.setRepeatCount(3);//ValueAnimator.INFINITE
                    animator.setRepeatMode(ValueAnimator.REVERSE);
                    animator.addUpdateListener(this);
                    animator.start();
                }
            } else  {
                animator.setDuration(duration);//设置动画时间
                if (animator.isStarted()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        animator.resume();
                    } else {
                        animator.start();
                        animator.setCurrentPlayTime(currentPlayTime);
                    }
                } else {
                    animator.start();
                    animator.setCurrentPlayTime(currentPlayTime);
                }
            }
        }
    }

    public void stop() {
        isStart = false;
        if (animator != null && animator.isRunning()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                animator.pause();
            } else {
                currentPlayTime = animator.getCurrentPlayTime();
                animator.cancel();
            }
        }
    }

    public void onDestory(){
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }
}
