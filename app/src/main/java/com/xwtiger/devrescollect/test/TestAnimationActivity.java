package com.xwtiger.devrescollect.test;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.silencedut.fpsviewer.FpsViewer;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.study.thread.threadpool.TestThreadPool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class TestAnimationActivity extends BaseActivity {


    private TextView tv_animation;
    private TextView tv_testthread;


    private ImageView iv_first;
    private ImageView iv_second;
    private ImageView iv_third;

    private TextView tv_switch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testanimation);
        FpsViewer.getViewer().appendSection(this.getClass().getSimpleName()+"_Section");
        init();
        addData();

        Glide.with(this).load(list.get(0)).placeholder(Color.parseColor("#eeeeee")).into(iv_first);


        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView() {
        tv_animation = findViewById(R.id.tv_animation);
        tv_testthread = findViewById(R.id.tv_testthread);

        iv_first = findViewById(R.id.iv_first);
        iv_second = findViewById(R.id.iv_second);
        iv_third = findViewById(R.id.iv_third);
        tv_switch = findViewById(R.id.tv_switch);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        tv_animation.setOnClickListener(this);
        tv_testthread.setOnClickListener(this);
        tv_switch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_animation:
                startAnimation();
                break;
            case R.id.tv_testthread:
                TestThreadPool.test();
                break;
            case R.id.tv_switch:
//                Message obtain = Message.obtain();
//                obtain.what=what_play;
//                hanler.sendMessage(obtain)
                if(localTimer ==null){
                    localTimer = new LocalTimer("daojishi");
                    localTimer.setListerTimer(new IListerTimer() {
                        @Override
                        public void update(final long time) {
                            Log.d("testlocaltimer1", "update: time="+time+",threadname="+Thread.currentThread().getName());
                            tv_switch.post(new Runnable() {
                                @Override
                                public void run() {
//                                    long minutes = (time % ( 60 * 60)) /60;
//                                    long seconds = time % 60;


                                    long minutes = time/60;
                                    long seconds = time % 60;
                                    Log.d("testlocaltimer1", "update: minutes="+minutes+",seconds="+seconds);


                                    String str_minutes = "";
                                    String str_seconds = "";

                                    if(minutes ==0){
                                        str_minutes = "00";
                                    }else if(minutes >0&&minutes <=9){
                                        str_minutes = "0"+minutes;
                                    }else{
                                        str_minutes = String.valueOf(minutes);
                                    }
                                    if(seconds ==0){
                                        str_seconds = "00";
                                    }else if(seconds >0&&seconds <=9){
                                        str_seconds = "0"+seconds;
                                    }else{
                                        str_seconds = String.valueOf(seconds);
                                    }
                                    tv_switch.setText(str_minutes+":"+str_seconds);
                                }
                            });
                        }

                        @Override
                        public void end() {
                            Log.d("testlocaltimer1", "end:");

                        }

                        @Override
                        public void init() {
                            Log.d("testlocaltimer1", "init:");

                        }

                        @Override
                        public void pause() {
                            Log.d("testlocaltimer1", "pause:");
                        }
                    });
                }
                if(!isTimeing){
                    isTimeing = true;
                    localTimer.start();
                }else{
                    isTimeing = false;
                    localTimer.pause();
                }
                break;
        }
    }




    private LocalTimer localTimer;







    Subscription subscribe;
    public long initVaule = 0;
    Observable<Long> interval;

    boolean isTimeing = false;

    public void timer1(){
         if(interval ==null){
             interval = Observable.interval(initVaule, 1, TimeUnit.SECONDS, Schedulers.computation());
         }
         subscribe = interval.subscribe(observer);
    }


    Observer observer = new Observer<Long>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Long aLong) {
            initVaule = aLong;
            if(aLong >20){
                Log.d("testtimer1", "取消 call: aLong =" + aLong + ",threadname=" + Thread.currentThread().getName());
                if(!subscribe.isUnsubscribed()){
                    subscribe.unsubscribe();
                }
            }else{
                Log.d("testtimer1", "call: aLong =" + aLong + ",threadname=" + Thread.currentThread().getName());
            }
        }
    };


    public void startAnimation(){
        int duration = getResources().getDimensionPixelSize(R.dimen.x100);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv_animation,"y",0,duration);
        objectAnimator.start();
    }





    public ObjectAnimator startAnmationForshow(final ImageView iv){

        ObjectAnimator objectAnimator_show = ObjectAnimator.ofFloat(iv,"alpha",0,0.7f,0.7f,0.7f,0.7f,0.7f,0.8f,0.9f,1);
        objectAnimator_show.setDuration(1000);
        objectAnimator_show.setInterpolator(new LinearInterpolator());
        objectAnimator_show.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if(hidePosition+1<list.size()){
                    hidePosition++;
                }else{
                    hidePosition=0;
                }

                Glide.with(TestAnimationActivity.this).load(list.get(hidePosition)).placeholder(Color.parseColor("#eeeeee")).into(iv);

                iv.setVisibility(View.VISIBLE);


            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //iv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return objectAnimator_show;
    }


    public ObjectAnimator startAnmationForhide(final ImageView iv){
        ObjectAnimator objectAnimator_hide = ObjectAnimator.ofFloat(iv,"alpha",1,0.9f,0.8f,0.7f,0.7f,0,0);
        objectAnimator_hide.setDuration(1000);
        objectAnimator_hide.setInterpolator(new LinearInterpolator());
        objectAnimator_hide.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Glide.with(TestAnimationActivity.this).load(list.get(hidePosition)).placeholder(Color.parseColor("#eeeeee")).into(iv);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return objectAnimator_hide;
    }


    int hidePosition =0;


    public void play(){
        if(list !=null && list.size()<2){
            return;
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(startAnmationForhide(iv_first.getVisibility() ==View.VISIBLE?iv_first:iv_second),startAnmationForshow(iv_second.getVisibility() !=View.VISIBLE?iv_second:iv_first));
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Message obtain = Message.obtain();
                obtain.what=what_timer;
                hanler.sendMessage(obtain);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }



    public final static int what_play =1;
    public final static int what_timer =2;

    public Handler hanler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d("testmsg1", "handleMessage: "+msg.what);
            switch (msg.what){
                case what_play:
                    play();
                    break;
                case what_timer:
                    Message obtain = Message.obtain();
                    obtain.what = what_play;
                    hanler.sendMessageDelayed(obtain,2000);
                    break;
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();
        hanler.removeCallbacksAndMessages(null);
    }

    List<String> list = new ArrayList<String>();

    public void addData(){
//        for(int i=0;i<5;i++){
//            int i1 = new Random(255).nextInt();
//            String s = Integer.toHexString(i1).substring(2);
//
//        }
        String[] urls = getResources().getStringArray(R.array.test_num_url);
        list = Arrays.asList(urls);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("testmsg1", "onDestroy: ");
        hanler.removeCallbacksAndMessages(null);
        FpsViewer.getViewer().removeSection(this.getClass().getSimpleName()+"_Section");
        if(subscribe !=null&&!subscribe.isUnsubscribed()){
            subscribe.unsubscribe();
        }

        if(localTimer !=null){
            localTimer.destroy();
        }
    }


    /**
     *  1,2,3
     *
     *
     *
     */




}
