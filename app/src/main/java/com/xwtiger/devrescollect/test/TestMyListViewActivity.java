package com.xwtiger.devrescollect.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.view.MyListView;

import java.lang.ref.WeakReference;

/**
 * author:xw
 * Date:2018-09-17 10:37
 * Description:
 */
public class TestMyListViewActivity extends BaseActivity {


    private MyListView myListView;
    private TestAdapter mTestAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_testlistview);
        init();
    }

    @Override
    public void initView() {
        myListView = findViewById(R.id.mylistview);
    }

    @Override
    public void initData() {
        mTestAdapter = new TestAdapter(this);
        myListView.setAdapter(mTestAdapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }



    static class TestAdapter extends BaseAdapter{
        
        private WeakReference<Activity> mWeakReference;
        private int count;

        public TestAdapter(Activity activity){
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public int getCount() {
            return 5;
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
            ViewHolder holder = null;
            if(convertView==null){
                count ++;
                System.out.println("count ==== "+count);
                if(mWeakReference.get() != null){
                    convertView = View.inflate(mWeakReference.get(),R.layout.item_testlistview,null);
                    holder = new ViewHolder();
                    holder.tv = convertView.findViewById(R.id.tv_test);
                    convertView.setTag(holder);
                }
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv.setText("testlistview===="+position);
            return convertView;
        }
    }
    
    static class ViewHolder{
        TextView tv;
    }



}
