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
                AnmitorStudy.testValueAnmitor(tv);
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
}
