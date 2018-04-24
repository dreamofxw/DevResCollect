package com.xwtiger.devrescollect.study.androidapi.draw;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.refresh.xlistview.XListView;

import java.lang.ref.WeakReference;

/**
 * Created by xwadmin on 2018/4/22.
 */

public class TestXListViewActivity extends BaseActivity {

    private XListView xlistview;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            xlistview.stopRefresh();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testxlistview);
        init();
    }

    @Override
    public void initView() {
        xlistview = (XListView) findViewById(R.id.xlistview);

    }

    @Override
    public void initData() {

        xlistview.setXListViewListener(loaddataListener);
        xlistview.setAdapter(new MyAdapter(this));
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    XListView.IXListViewListener loaddataListener = new XListView.IXListViewListener(){

        @Override
        public void onRefresh() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    xlistview.stopRefresh();
                }
            },20000);
        }

        @Override
        public void onLoadMore() {

        }
    };


    private static class MyAdapter extends BaseAdapter {

        private WeakReference<BaseActivity> reference;

        public MyAdapter(BaseActivity baseActivity){
            reference = new WeakReference<BaseActivity>(baseActivity);
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
            holder.setData(position);
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

        public void setData(int position){
            tv.setText("我是第"+position+"个item");
        }

    }
}