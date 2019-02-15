package com.xwtiger.devrescollect.study.androidapi.behavoir;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;

/**
 * Created by Busap-112 on 2017/11/15.
 */

public class BehaviorActivity extends Activity {


    private TabLayout tabLayout;
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testbehavoir);


        tabLayout = findViewById(R.id.tabLayout);
        recyclerview = findViewById(R.id.recyclerview);

        initTablayout();
        initRecyclerview();
    }


    private void initTablayout(){
        tabLayout.addTab(tabLayout.newTab().setText("test1"));
        tabLayout.addTab(tabLayout.newTab().setText("test2"));
    }

    private void initRecyclerview(){
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(new MyAdapter(this));
    }


    static class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        private Context context;

        public  MyAdapter(Context c){
            this.context = c;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView tv = new TextView(context);
            return new ViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setData(position);

        }

        @Override
        public int getItemCount() {
            return 120;
        }


    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }

        public void setData(int position){
            tv.setText("test"+position);
        }
    }

}
