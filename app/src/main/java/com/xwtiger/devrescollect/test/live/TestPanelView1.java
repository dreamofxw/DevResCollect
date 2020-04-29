package com.xwtiger.devrescollect.test.live;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.xwtiger.devrescollect.R;

public class TestPanelView1 implements IPanelView{


    private Context mContext;
    private ViewGroup mRootView;
    private View inflate;

    public TestPanelView1(Context context, ViewGroup rootview){
        this.mContext = context;
        this.mRootView = rootview;
        initView();
    }

    boolean isAnimation =false;
    @Override
    public void show() {
        if(mRootView !=null&&inflate !=null&&!isAnimation){
            inflate.clearAnimation();

            if(isAnimation){
                mRootView.removeView(inflate);
            }
            FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.MATCH_PARENT);
            flp.gravity = Gravity.RIGHT|Gravity.CENTER_VERTICAL;
            mRootView.addView(inflate,flp);
            int width = mRootView.getWidth();
            int left = (int) mContext.getResources().getDimension(R.dimen.x600);
            Log.d("testclickoutside", "show: width="+width+",left="+left);

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(inflate,"x",width,width-left);
            objectAnimator.setDuration(2000);


            objectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.d("testclickoutside", "show: onAnimationStart=");

                    isAnimation = true;

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.d("testclickoutside", "show: onAnimationEnd=");

                    isAnimation = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.d("testclickoutside", "show: onAnimationCancel=");

                    isAnimation =false;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            objectAnimator.start();
        }
    }

    @Override
    public void hide() {
        if(mRootView !=null&&inflate !=null){
            Log.d("testclickoutside", "hide: ---------");
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(inflate,"x",inflate.getLeft(),mRootView.getWidth());
            objectAnimator.setDuration(2000);


            objectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isAnimation = true;
                    Log.d("testclickoutside", "hide:onAnimationStart ---------");

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.d("testclickoutside", "hide:onAnimationEnd ---------");

                    mRootView.removeView(inflate);
                    isAnimation = false;
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.d("testclickoutside", "hide:onAnimationCancel ---------");

                    mRootView.removeView(inflate);
                    isAnimation = false;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });

            objectAnimator.start();


        }
    }


    public void initView(){
         inflate = View.inflate(mContext, R.layout.testpanelview, null);
    }


    public int getwidth(){
        return inflate.getWidth();
    }

    public int getHeight(){
        return inflate.getHeight();
    }

    public int getLeft(){
        return inflate.getLeft();
    }

    public int getTop(){
        return inflate.getTop();
    }


    public boolean isAninmation(){
        return isAnimation;
    }

    public boolean isVisible(){
        return inflate.getVisibility() ==View.VISIBLE;
    }

    public boolean isNeedHide(){
        return !isAnimation&&inflate.getParent() !=null;
    }

}
