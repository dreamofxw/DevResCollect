package com.xwtiger.devrescollect.test.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TestAdapterActivity extends BaseActivity {

    RecyclerView recyclerview;
    TestRecycleViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testadapter);
        init();

        
    }

    @Override
    public void initView() {
        recyclerview = findViewById(R.id.recyclerview);
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setItemViewCacheSize(5);
        adapter = new TestRecycleViewAdapter(this);
        adapter.addData(addTestData());
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void setListener() {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        Log.d("testreflectRecycleView", "onScrollStateChanged: ------------分界线---------");
                        reflectRecycleView("mAttachedScrap");
                        reflectRecycleView("mChangedScrap");
                        reflectRecycleView("mCachedViews");
                        reflectRecycleView("mUnmodifiableAttachedScrap");
                        reflectRecycleViewForPool();
                        break;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerview.clearOnScrollListeners();
    }

    public void reflectRecycleView(String fieldName){
        Field mRecycler = null;
        try {
            mRecycler = recyclerview.getClass().getDeclaredField("mRecycler");
            mRecycler.setAccessible(true);
            RecyclerView.Recycler o = (RecyclerView.Recycler) mRecycler.get(recyclerview);
            //Field mAttachedScrap = o.getClass().getField("mAttachedScrap");
            Field mAttachedScrap = o.getClass().getDeclaredField(fieldName);
            mAttachedScrap.setAccessible(true);
            ArrayList<RecyclerView.ViewHolder> o1 = (ArrayList<RecyclerView.ViewHolder>) mAttachedScrap.get(o);
            if(o1 !=null && o1.size()>0){
                StringBuilder sb = new StringBuilder();
                sb.append("size ="+o1.size()+"  ");
                for(int i=0;i<o1.size();i++){
                    RecyclerView.ViewHolder holder = o1.get(i);
                    sb.append( holder.getAdapterPosition());
                    if(i !=o1.size()-1){
                        sb.append(",");
                    }
                }
                Log.d("testreflectRecycleView", "reflectRecycleView: "+fieldName+",="+ sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reflectRecycleViewForPool(){
        Field mRecycler = null;
        try {
            mRecycler = recyclerview.getClass().getDeclaredField("mRecycler");
            mRecycler.setAccessible(true);
            RecyclerView.Recycler o = (RecyclerView.Recycler) mRecycler.get(recyclerview);
            Field mAttachedScrap = o.getClass().getDeclaredField("mRecyclerPool");
            mAttachedScrap.setAccessible(true);
            RecyclerView.RecycledViewPool recycledViewPool  = (RecyclerView.RecycledViewPool) mAttachedScrap.get(o);
            Field mScrap = recycledViewPool.getClass().getDeclaredField("mScrap");
            mScrap.setAccessible(true);
            SparseArray o1 = (SparseArray) mScrap.get(recycledViewPool);
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<o1.size();i++){
                Object o2 = o1.valueAt(i);
                Field mScrapHeap = o2.getClass().getDeclaredField("mScrapHeap");
                mScrapHeap.setAccessible(true);
                ArrayList<RecyclerView.ViewHolder> o3 = (ArrayList<RecyclerView.ViewHolder>) mScrapHeap.get(o2);
                sb.append("tostring= "+o3.toString()+"   ");
                if(o3 !=null && o3.size()>0){
                    for(int a =0;a<o3.size();a++){
                        RecyclerView.ViewHolder holder = o3.get(a);
                        sb.append(holder.getLayoutPosition());
                        if(a !=o3.size()-1){
                            sb.append(",");
                        }
                    }
                }
                sb.append("\r\n");
            }
            Log.d("testreflectRecycleView", "reflectRecycleViewForPool: size="+o1.size()+",child ="+sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<TestAdapterBean> addTestData(){
        List<TestAdapterBean> list = new ArrayList<TestAdapterBean>();
        for(int i=0;i<500;i++){
            TestAdapterBean  bean = new TestAdapterBean();
            bean.name = "name"+i;
            bean.desc = "desc"+i;
            list.add(bean);
        }
        return list;
    }
}
