package com.xwtiger.devrescollect.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.study.androidapi.AnmitorStudy;

/**
 * Created by Busap-112 on 2017/11/10.
 *
 */

public class TestActivity extends Activity implements View.OnClickListener{

    private LinearLayout container;
    private Button btn_start;
    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.testlayout);
        container = (LinearLayout) findViewById(R.id.container);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        tv = new TextView(this);
        //TestView textView = new TestView(this);
        tv.setText("我会飘");
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        container.addView(tv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Log.d("testValueAnmitor","btn_start height ="+(btn_start.getHeight()));
                //AnmitorStudy.testValueAnmitor(tv);
                addTestThread();
                break;
        }
    }


    public class TestView extends View{


        public TestView(Context context) {
            super(context);

        }


        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.translate(200,200);
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ff0000"));
            paint.setTextSize(50);
            paint.setTextAlign(Paint.Align.LEFT);

            canvas.drawText("hahah",0,0,paint);
        }
    }


    public void addTestThread(){

        for (int i = 0; i <30 ; i++) {
            Thread thread1 = new Thread(new TimeThread());
            thread1.start();
        }



    }

    int x = 1;
    boolean isloop = true;
    String msg;
    StringBuffer sb = new StringBuffer();

    class TimeThread implements Runnable{
        @Override
        public void run() {
            while (isloop){
                try {
                    Thread.sleep(100);
                    x++;
                    //sb.append("msg");
                    //sb.append(x);
                    //msg = sb.toString();
                    //sb.delete(0,sb.length());

                   //Log.d("TimeThread",msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void onDestroy() {
        isloop = false;
        super.onDestroy();
    }



}
