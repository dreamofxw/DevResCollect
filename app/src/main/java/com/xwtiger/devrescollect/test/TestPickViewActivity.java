package com.xwtiger.devrescollect.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Busap-112 on 2017/12/12.
 */

public class TestPickViewActivity extends BaseActivity {

    private TextView tv_selectedtime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testpickview);
        init();

    }

    @Override
    public void initView() {
        tv_selectedtime = (TextView) findViewById(R.id.tv_selectedtime);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        tv_selectedtime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_selectedtime:
                showPick();
                break;
        }
    }


    private void showPick(){

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
        startDate.set(1991,0,1);
        endDate.set(2050,11,28);

        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //tvTime.setText(getTime(date));
            }
        }).setType(new boolean[]{true,true,false,false,false,false})
                .setBackgroundId(Color.parseColor("#00000000"))//设置rootviewbg
                .setBgColor(Color.parseColor("#ffffff"))//设置wheelview bg
                .setTitleBgColor(Color.parseColor("#ffffff")) //设置标题栏背景
                .setRangDate(startDate,endDate)
                .setLabel("","","","","","")
                .build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }


    private void showOptionPick(){
        OptionsPickerView pvOptions = new  OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(option2)
//                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
//                tvOptions.setText(tx);
            }
        }).build();
        //pvOptions.setPicker(options1Items, options2Items, options3Items);
        pvOptions.show();

    }
}
