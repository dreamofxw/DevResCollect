package com.xwtiger.devrescollect.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.dialog.BottomerDialog;

import java.util.ArrayList;
import java.util.List;

public class TestLayoutAnmationActivity extends BaseActivity {


    private RecyclerView recyclerview;

    private List<String> listdata;

    private TestAnmationAdapter adapter;

    private  TextView tv_testdialog;


    int count=0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if(count >8){
                        for(int i=8;i<10;i++){
                            listdata.add("testhandledata ="+i);
                        }
                        adapter.notifyItemRangeChanged(8,listdata.size()-1);
                        recyclerview.scrollToPosition(listdata.size()-1);
                        handler.removeCallbacksAndMessages(null);
                        return;
                    }
                    listdata.add("testhandledata ="+count);
                    adapter.notifyItemChanged(listdata.size()-1);
                    //runLayoutAnimation(listdata.size()-1);
                    //recyclerview.scrollToPosition(listdata.size()-1);
                    count++;
                    handler.sendEmptyMessageDelayed(1,300);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.testlayoutanimation);

       init();




        listdata = new ArrayList<>();
//        for(int i=0;i<50;i++){
//            listdata.add("testitemanimation"+i);
//        }

//        init();



        boolean testboolean = true;

        Log.d("testboo", "onCreate: before testboolean="+testboolean);
        testboolean = !testboolean;
        Log.d("testboo", "onCreate: after testboolean="+testboolean);





    }

    @Override
    public void initView() {
        recyclerview = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        adapter = new TestAnmationAdapter();
        recyclerview.setLayoutManager(linearLayoutManager);


//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.item_showanimation);
//        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(animation,500);
//        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        recyclerview.setLayoutAnimation(layoutAnimationController);

        //recyclerview.setAdapter(adapter);



        handler.sendEmptyMessageDelayed(1,1000);


        tv_testdialog = findViewById(R.id.tv_testdialog);


        //recyclerview.scrollToPosition(adapter.getItemCount()-1);


        findViewById(R.id.tv_livepullstream_notice);

    }


    private void runLayoutAnimation(int position) {

//        final LayoutAnimationController controller =
//                AnimationUtils.loadLayoutAnimation(this, R.anim.item_showanimation);
//        recyclerview.setLayoutAnimation(controller);
//        recyclerview.getAdapter().notifyItemChanged(position);
//        recyclerview.scheduleLayoutAnimation();
    }

    @Override
    public void initData() {

    }

    private BottomerDialog dialog;
    @Override
    public void setListener() {
        tv_testdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialog ==null){
                    dialog = new BottomerDialog(TestLayoutAnmationActivity.this);
                }
                dialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }


    class TestAnmationAdapter extends RecyclerView.Adapter{


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = View.inflate(TestLayoutAnmationActivity.this, R.layout.item_testlayoutanmation, null);
            return new MyHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyHolder){
                MyHolder holder1 = (MyHolder) holder;
                holder1.tv_testlayoutanmation.setText("testlayoutnamation "+position);
            }
        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public TextView tv_testlayoutanmation;
        public MyHolder(View itemView) {
            super(itemView);
            tv_testlayoutanmation = itemView.findViewById(R.id.tv_testlayoutanmation);
        }
    }
}
