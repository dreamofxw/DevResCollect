package com.xwtiger.devrescollect.study.androidapi.behavoir;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Busap-112 on 2017/11/15.
 */

public class BehaviorForTaoBaoDetailActivity extends Activity {


    private TabLayout tabLayout;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_taobao);


        tabLayout = findViewById(R.id.tabLayout);
        recyclerview = findViewById(R.id.recyclerview);

        initTablayout();
        initRecyclerview();
    }


    private void initTablayout(){
        tabLayout.addTab(tabLayout.newTab().setText("test1"));
        tabLayout.addTab(tabLayout.newTab().setText("test2"));
        tabLayout.addTab(tabLayout.newTab().setText("test3"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int a = list_test1.size()>0?list_test1.size():1;
                int b = list_test2.size()>0?list_test2.size():1;
                int c = list_test3.size()>0?list_test3.size():1;
                int position = tab.getPosition();
                Log.d("onTabSelected", "onTabSelected: position="+position);
                Log.d("onTabSelected", "onTabSelected: a="+a);
                Log.d("onTabSelected", "onTabSelected: b="+b);
                Log.d("onTabSelected", "onTabSelected: c="+c);

                if(position ==0){
                    recyclerview.scrollToPosition(0);
                }else if(position ==1){

                    ((LinearLayoutManager)recyclerview.getLayoutManager()).scrollToPositionWithOffset(a,0);
                }else if(position ==2){
                    ((LinearLayoutManager)recyclerview.getLayoutManager()).scrollToPositionWithOffset(a+b,0);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initRecyclerview(){
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        fillData();
        recyclerview.setAdapter(new MyAdapter(this));

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE :

                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING :

                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING :

                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstCompletelyVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                Log.d("onScrolled", "onScrolled: firstCompletelyVisibleItemPosition="+firstCompletelyVisibleItemPosition);
                int a = list_test1.size()>0?list_test1.size():1;
                int b = list_test2.size()>0?list_test2.size():1;
                int c = list_test3.size()>0?list_test3.size():1;
                if(firstCompletelyVisibleItemPosition <a){
                    tabLayout.setScrollPosition(0,0,true);
                }else if(firstCompletelyVisibleItemPosition <a+b){
                    tabLayout.setScrollPosition(1,0,true);

                }else{
                    tabLayout.setScrollPosition(2,0,true);

                }

            }
        });
    }


    private void fillData(){
        for(int i=0;i<30;i++){
            list_test1.add("test1_"+i);
        }
        for(int j=0;j<60;j++){
            list_test2.add("test1_"+j);
        }
        for(int k=0;k<50;k++){
            list_test3.add("test1_"+k);
        }
        if(list_test1.size()>0){
            list_all.addAll(list_test1);
        }else{
            list_all.add("test1_null");
        }
        if(list_test2.size()>0){
            list_all.addAll(list_test2);
        }else{
            list_all.add("test2_null");
        }
        if(list_test3.size()>0){
            list_all.addAll(list_test3);
        }else{
            list_all.add("test3_null");
        }
    }

    private List<String> list_test1 = new ArrayList<>();
    private List<String> list_test2 = new ArrayList<>();
    private List<String> list_test3 = new ArrayList<>();
    private List<String> list_all= new ArrayList<>();

     class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        private Context context;
        int a ;
        int b ;
        int d;

        public  MyAdapter(Context c){
            this.context = c;
            a = list_test1.size();
            b = list_test2.size();
            d = list_test3.size();
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView tv = new TextView(context);
            return new ViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.setData(list_all.get(position),position);

        }

        @Override
        public int getItemCount() {
            int total =0;
            total += (list_test1.size()>0?list_test1.size():1);
            total += (list_test2.size()>0?list_test2.size():1);
            total += (list_test3.size()>0?list_test3.size():1);
            return list_all.size();
        }


    }

     class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }

        public void setData(String str ,int position){
            tv.setText(str+"=="+position);
        }
    }

}
