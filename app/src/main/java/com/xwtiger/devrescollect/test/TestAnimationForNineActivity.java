package com.xwtiger.devrescollect.test;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

public class TestAnimationForNineActivity extends BaseActivity {


    private ImageView iv1;
    private ImageView iv2;
    private LinearLayout ll1;

    private ImageView iv2_1;
    private ImageView iv2_2;

    private ImageView iv3_1;
    private ImageView iv3_2;

    private ImageView iv4_1;
    private ImageView iv4_2;


    private ImageView iv5_1;
    private ImageView iv5_2;

    private ImageView iv6_1;
    private ImageView iv6_2;

    private ImageView iv7_1;
    private ImageView iv7_2;

    private ImageView iv8_1;
    private ImageView iv8_2;

    private ImageView iv9_1;
    private ImageView iv9_2;


    private TextView tv_start;
    private TextView tv_revser;

    private int width;
    private int height;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testanimationfornine);
        init();
    }

    @Override
    public void initView() {
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        ll1 = findViewById(R.id.ll1);

        iv2_1 = findViewById(R.id.iv2_1);
        iv2_2 = findViewById(R.id.iv2_2);


        iv3_1 = findViewById(R.id.iv3_1);
        iv3_2 = findViewById(R.id.iv3_2);

        iv4_1 = findViewById(R.id.iv4_1);
        iv4_2 = findViewById(R.id.iv4_2);

        iv5_1 = findViewById(R.id.iv5_1);
        iv5_2 = findViewById(R.id.iv5_2);

        iv6_1 = findViewById(R.id.iv6_1);
        iv6_2 = findViewById(R.id.iv6_2);

        iv7_1 = findViewById(R.id.iv7_1);
        iv7_2 = findViewById(R.id.iv7_2);

        iv8_1 = findViewById(R.id.iv8_1);
        iv8_2 = findViewById(R.id.iv8_2);


        iv9_1 = findViewById(R.id.iv9_1);
        iv9_2 = findViewById(R.id.iv9_2);



        tv_start = findViewById(R.id.tv_start);
        tv_revser = findViewById(R.id.tv_revser);
    }

    @Override
    public void initData() {
        width = getResources().getDimensionPixelSize(R.dimen.x150);
        height = getResources().getDimensionPixelSize(R.dimen.x150);
    }

    @Override
    public void setListener() {
        tv_start.setOnClickListener(this);
        tv_revser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_start:
                isPlay = true;
                handler.sendEmptyMessage(1);
                break;
            case R.id.tv_revser:
                isPlay = false;
                break;
        }
    }


    boolean isPlay = true;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(!isPlay){
                return;
            }

            switch (msg.what){
                case 1:
                    playFirstAnimation();
                    break;
                case 2:
                    playSecondAnimation();
                    break;
                case 3:
                    playFirstAnimationForResever();
                    break;
                case 4:
                    playSecondAnimationForRevser();
                    break;

            }




        }
    };

//    1，3，5，7，9 同时动
//    1，3，7，左移动化
//    5，9，相反动画
    public void playFirstAnimation(){

        final AnimatorSet animatorSet = playAnimationWithFromLeftToRight(iv1, iv2);
        playAnimationWithFromLeftToRight(iv3_1,iv3_2);
        playAnimationWithFromLeftToRight(iv7_1,iv7_2);

        playAnimationForFromRightToLeft(iv5_1,iv5_2);
        playAnimationForFromRightToLeft(iv9_1,iv9_2);


        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.removeListener(this);
                if(isPlay){
                    handler.sendEmptyMessage(2);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }


    public void playFirstAnimationForResever(){

        final AnimatorSet animatorSet = playAnimationForFromRightToLeft(iv1, iv2);
        playAnimationForFromRightToLeft(iv3_1,iv3_2);
        playAnimationForFromRightToLeft(iv7_1,iv7_2);

        playAnimationWithFromLeftToRight(iv5_1,iv5_2);
        playAnimationWithFromLeftToRight(iv9_1,iv9_2);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.removeListener(this);
                if(isPlay){
                    handler.sendEmptyMessage(4);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }


    /**
     * 2，4，6，8，同时动
     * 2，8 左右同方向动画
     * 4，6，上下相反动画
     */
    public void playSecondAnimation(){
        final AnimatorSet animatorSet = playAnimationWithFromLeftToRight(iv2_1, iv2_2);
        playAnimationWithFromLeftToRight(iv8_1,iv8_2);

        playAnimationForTopToBommber(iv4_1,iv4_2);
        playAnimationForBottmerToTop(iv6_1,iv6_2);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.removeListener(this);
                if(isPlay){
                    handler.sendEmptyMessage(3);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }


    public void playSecondAnimationForRevser(){
        final AnimatorSet animatorSet = playAnimationForFromRightToLeft(iv2_1, iv2_2);
        playAnimationForFromRightToLeft(iv8_1,iv8_2);

        playAnimationForBottmerToTop(iv4_1,iv4_2);
        playAnimationForTopToBommber(iv6_1,iv6_2);

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet.removeListener(this);
                if(isPlay){
                    handler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }




    public AnimatorSet playAnimationWithFromLeftToRight(ImageView iv_visibile,ImageView iv_inVisibile){
        float x = iv_visibile.getX();
        Log.d("testanimation", "playAnimation: x="+x);
        Log.d("testanimation", "playAnimation: width="+width);
        AnimatorSet as = new AnimatorSet();
        ObjectAnimator  oa1 =  ObjectAnimator.ofFloat(iv_visibile,"x",0,-width);
        ObjectAnimator  oa2 =  ObjectAnimator.ofFloat(iv_inVisibile,"x",width,0);
        as.playTogether(oa1,oa2);
        as.setDuration(1000);
        as.start();
        return as;
    }


    public AnimatorSet playAnimationForFromRightToLeft(ImageView iv_visibile,ImageView iv_inVisibile){
        float x = iv_visibile.getX();
//        float x1 = ll1.getX();
        //int width = iv_visibile.getWidth();
        Log.d("testanimation", "playAnimation: x="+x);
//        Log.d("testanimation", "playAnimation: x1="+x1);
        Log.d("testanimation", "playAnimation: width="+width);
        AnimatorSet as = new AnimatorSet();
        ObjectAnimator  oa1 =  ObjectAnimator.ofFloat(iv_visibile,"x",-width,0);
        ObjectAnimator  oa2 =  ObjectAnimator.ofFloat(iv_inVisibile,"x",0,width);
        as.playTogether(oa2,oa1);
        as.setDuration(1000);
        as.start();
        return as;
    }

    public AnimatorSet playAnimationForTopToBommber(ImageView iv_visibile,ImageView iv_inVisibile){
        AnimatorSet as = new AnimatorSet();
        ObjectAnimator  oa1 =  ObjectAnimator.ofFloat(iv_visibile,"y",0,height);
        ObjectAnimator  oa2 =  ObjectAnimator.ofFloat(iv_inVisibile,"y",-height,0);
        as.playTogether(oa1,oa2);
        as.setDuration(1000);
        as.start();


        return as;

    }



    public AnimatorSet playAnimationForBottmerToTop(ImageView iv_visibile,ImageView iv_inVisibile){
        AnimatorSet as = new AnimatorSet();
        ObjectAnimator  oa1 =  ObjectAnimator.ofFloat(iv_visibile,"y",height,0);
        ObjectAnimator  oa2 =  ObjectAnimator.ofFloat(iv_inVisibile,"y",0,-height);
        as.playTogether(oa2,oa1);
        oa1.setStartDelay(50);
        as.setDuration(1000);
        as.start();

        return as;
    }


}
