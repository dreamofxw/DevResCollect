package com.xwtiger.devrescollect.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;

import java.util.ArrayList;
import java.util.List;

public class TestRecycleViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<TestAdapterBean> mList;
    public TestRecycleViewAdapter(Context context){
        this.mContext = context;
        this.mList = new ArrayList<>();
    }

    public void addData(List<TestAdapterBean> list){
        if(mList !=null&&list !=null && list.size()>0){
            mList.addAll(list);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(mContext, viewType, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            TestAdapterBean testAdapterBean = mList.get(position);
            MyViewHolder holder1 = (MyViewHolder) holder;
            holder1.setData(testAdapterBean);
        }
    }

    @Override
    public int getItemCount() {
        return mList !=null?mList.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        int temptype = position % 10;
        if(temptype ==3){
            return R.layout.item_testadapter3;
        }else if(temptype ==5){
            return R.layout.item_testadapter2;
        }else if(temptype ==4){
            return R.layout.item_testadapter4;
        }else if(temptype ==2){
            return R.layout.item_testadapter5;
        }else if(temptype ==6){
            return R.layout.item_testadapter6;
        }else if(temptype ==7){
            return R.layout.item_testadapter7;
        }
        return R.layout.item_testadapter;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_txt);
        }

        public void setData(TestAdapterBean bean){
            textView.setText("name ="+bean.name+",desc ="+bean.desc);
        }
    }

    static class MyViewHolder2 extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewHolder2(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_txt);
        }

        public void setData(TestAdapterBean bean){
            textView.setText("name ="+bean.name+",desc ="+bean.desc);
        }
    }


}
