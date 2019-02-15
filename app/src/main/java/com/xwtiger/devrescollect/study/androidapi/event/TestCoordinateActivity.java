package com.xwtiger.devrescollect.study.androidapi.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;


/**
 * 测试坐标
 */
public class TestCoordinateActivity extends BaseActivity{


    private RelativeLayout rl_first;
    private RelativeLayout rl_second;
    private TextView tv_first;
    float lastx =0f;
    float lasty =0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testcoordinate);
        init();
    }

    @Override
    public void initView() {
        rl_first = findViewById(R.id.rl_first);
        rl_second = findViewById(R.id.rl_second);
        tv_first = findViewById(R.id.tv_first);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        //tv_first.setOnClickListener(this);

        //setTouchForgetRawx();
        setTouchforgetx();

        //上面两个触摸事件的区别：
        //getRawx()：需要每次都赋值lastx
        //
        //

        //getx():只需要第一次down的时候赋值lastx
        // (由于getx是触摸点相对于view的坐标当move动的时候 move的)







    }

    public void setTouchforgetx(){
        tv_first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float tempx = event.getX();
                float tempy = event.getY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN :
                        Log.d(TAG, "onTouch:ACTION_DOWN ");
                        Log.d(TAG, "onTouch: ##############################");
                        Log.d(TAG, "onTouch: event.getX()="+event.getX());
                        Log.d(TAG, "onTouch: event.getY()="+event.getY());
                        Log.d(TAG, "onTouch: event.getRawX()="+event.getRawX());
                        Log.d(TAG, "onTouch: event.getRawY()="+event.getRawY());
                        lastx = tempx;
                        lasty = tempy;
                        getCoordinate();
                        break;
                    case MotionEvent.ACTION_MOVE :
                        Log.d(TAG, "onTouch: lastx ="+lastx);
                        Log.d(TAG, "onTouch: tempx ="+tempx);
                        float x = tempx -lastx;
                        float y = tempy-lasty;
                        Log.d(TAG, "onTouch:ACTION_MOVE x ="+x);
                        Log.d(TAG, "onTouch:ACTION_MOVE y ="+y);

//                        float leftx = v.getLeft()+x;
//                        float topx = v.getTop()+y;

//                        v.offsetLeftAndRight(()x);
//                        v.offsetTopAndBottom((int)y);

                        float del_x = (v.getTranslationX() +x);
                        float del_y = (v.getTranslationY() +y);

                        v.setTranslationX(del_x);//修改translationx
                        v.setTranslationY(del_y);//修改translationy

                        break;
                    case MotionEvent.ACTION_UP :
                        getCoordinate();
                        Log.d(TAG, "onTouch:ACTION_UP ");
                        break;
                }
                return true;
            }
        });
    }



    public void setTouchForgetRawx(){
        tv_first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float tempx = event.getRawX();
                float tempy = event.getRawY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN :
                        Log.d(TAG, "onTouch:ACTION_DOWN ");
                        Log.d(TAG, "onTouch: ##############################");
                        Log.d(TAG, "onTouch: event.getX()="+event.getX());
                        Log.d(TAG, "onTouch: event.getY()="+event.getY());
                        Log.d(TAG, "onTouch: event.getRawX()="+event.getRawX());
                        Log.d(TAG, "onTouch: event.getRawY()="+event.getRawY());

                        getCoordinate();
                        break;
                    case MotionEvent.ACTION_MOVE :
                        Log.d(TAG, "onTouch: lastx ="+lastx);
                        Log.d(TAG, "onTouch: tempx ="+tempx);
                        float x = tempx -lastx;
                        float y = tempy-lasty;
                        Log.d(TAG, "onTouch:ACTION_MOVE x ="+x);
                        Log.d(TAG, "onTouch:ACTION_MOVE y ="+y);

                        v.offsetLeftAndRight((int)x);//修改的是mleft or mright
                        v.offsetTopAndBottom((int)y);//修改的是mtop or mbottom

//                        float del_x = (v.getTranslationX() +x);
//                        float del_y = (v.getTranslationY() +y);
//
//                        v.setTranslationX(del_x);
//                        v.setTranslationY(del_y);

                        break;
                    case MotionEvent.ACTION_UP :
                        getCoordinate();
                        Log.d(TAG, "onTouch:ACTION_UP ");
                        break;
                }

                lastx = tempx;
                lasty = tempy;
                return true;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_first:
                //getCoordinate();
                break;
        }
    }

    public void getCoordinate(){
        Log.d(TAG, "getCoordinate: ----------------------------");
        Log.d(TAG, "getCoordinate: rl_first.getX()="+rl_first.getX());
        Log.d(TAG, "getCoordinate: rl_first.getY()="+rl_first.getY());
        Log.d(TAG, "getCoordinate: rl_second.getY() ="+rl_second.getY());
        Log.d(TAG, "getCoordinate: rl_second.getX()="+rl_second.getX());
        Log.d(TAG, "getCoordinate: tv_first.getX()="+tv_first.getX());
        Log.d(TAG, "getCoordinate: tv_first.getY()"+tv_first.getY());
        Log.d(TAG, "getCoordinate: tv_first.getTranslationX()"+tv_first.getTranslationX());
        Log.d(TAG, "getCoordinate: tv_first.getTranslationY()"+tv_first.getTranslationY());
        Log.d(TAG, "getCoordinate: tv_first.getWidth()"+tv_first.getWidth());
        Log.d(TAG, "getCoordinate: tv_first.getHeight()"+tv_first.getHeight());
        Log.d(TAG, "getCoordinate: tv_first.getLeft()"+tv_first.getLeft());

    }
}
