package com.xwtiger.devrescollect.study.androidapi.draw;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * Created by xwadmin on 2018/4/22.
 */

public class TestViewActivity extends BaseActivity {

    private HorizontalScrollViewEx mHorizontalScrollViewEx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testhorizonsv);
        init();
    }

    @Override
    public void initView() {
        mHorizontalScrollViewEx = (HorizontalScrollViewEx) findViewById(R.id.hsvex);

    }

    @Override
    public void initData() {
        for(int i =0;i<3;i++){
            ListView lv = new ListView(mContext);
            lv.setAdapter(new MyAdapter(this,i+1));
            mHorizontalScrollViewEx.addView(lv);
        }
    }

    @Override
    public void setListener() {

    }


    @Override
    public void onClick(View v) {

    }

    private static class MyAdapter extends BaseAdapter{

        private WeakReference<BaseActivity> reference;
        private int index;

        public MyAdapter(BaseActivity baseActivity,int index){
            reference = new WeakReference<BaseActivity>(baseActivity);
            this.index = index;
        }

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                convertView = View.inflate(reference.get(),R.layout.item_testscrollv,null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.setData(position, index);
            return convertView;
        }


    }

    private static class ViewHolder{

        private TextView tv;
        private View v;
        public ViewHolder(View v){
            this.v = v;
            tv = (TextView) v.findViewById(R.id.tv);
        }

        public void setData(int position,int index){
            tv.setText("我是第"+index+"页，第"+position+"个item");
        }

    }

}
