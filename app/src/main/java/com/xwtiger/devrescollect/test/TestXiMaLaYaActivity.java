package com.xwtiger.devrescollect.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xwtiger.devrescollect.MyException;
import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.view.TestArcView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestXiMaLaYaActivity extends BaseActivity{


    public RecyclerView recyclerview;

    public TextView tv_bg;

    private TestArcView testArcView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_ximalaya);
        init();

    }

    @Override
    public void initView() {
        recyclerview = findViewById(R.id.recyclerview);
        tv_bg = findViewById(R.id.tv_bg);
        testArcView = findViewById(R.id.testarcview);
    }

    @Override
    public void initData() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(new TestAdapter(this));


        //add test data

        final List<String> list = new ArrayList();
        list.add("#00ff00");
        list.add("#0000ff");
        list.add("#ff0000");

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<list.size();i++){
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        MyException.printStr(e);
                    }
                    testArcView.changBackground(list.get(i),0.5f);
                }
            }
        });


    }

    public int scrolly;
    boolean isup = false;

    @Override
    public void setListener() {
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrolly +=dy;
                Log.d("testscroll", "onScrolled: scrolly="+scrolly);
                if(scrolly >20){
                    if(!isup){
                        isup = true;
                        tv_bg.setText("test up");
                        tv_bg.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                }else{
                    if(isup){
                        isup = false;
                        tv_bg.setText("test down");
                        tv_bg.setBackgroundColor(Color.parseColor("#00000000"));

                    }
                }

            }
        });
    }

    @Override
    public void onClick(View v) {

    }



    class TestAdapter extends RecyclerView.Adapter{

        public  int type_first = 1;
        public  int type_second = 2;

        private Context mContext;
        public TestAdapter(Context context){
            this.mContext = context;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(viewType ==type_first){
                View inflate = View.inflate(mContext, R.layout.item_testximalaya2, null);
                return new TestHolder(inflate);
            }else{
                View inflate = View.inflate(mContext, R.layout.item_testximalaya, null);
                return new TestHolder(inflate);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof TestHolder){
                TestHolder testHolder = (TestHolder) holder;
                testHolder.tv.setText("test"+position);
            }
        }

        @Override
        public int getItemCount() {
            return 100;
        }


        @Override
        public int getItemViewType(int position) {
            if(position ==0){
                return type_first;
            }else{
                return type_second;
            }
        }
    }

    class TestHolder extends RecyclerView.ViewHolder{

        public TextView tv;

        public TestHolder(View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.tv);
        }
    }


}
