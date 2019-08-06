package com.xwtiger.devrescollect.study.androidapi.behavoir;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.view.MyGridView;

import java.util.List;

/**
 * Created by Busap-112 on 2017/11/15.
 */

public class BehaviorActivity extends Activity {


    private TabLayout tabLayout;
    private RecyclerView recyclerview;

    private AppBarLayout appbarlayout;
    private TextView tv1;
    private CollapsingToolbarLayout collapsingtoolbarlayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testbehavoir);


        tabLayout = findViewById(R.id.tabLayout);
        recyclerview = findViewById(R.id.recyclerview);
        appbarlayout = findViewById(R.id.appbarlayout);
        tv1 = findViewById(R.id.tv1);
        collapsingtoolbarlayout = findViewById(R.id.collapsingtoolbarlayout);

        Log.d("testoffset", "onCreate: testheight"+getResources().getDimensionPixelSize(R.dimen.testheight1));

        final float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.testheight1);
        appbarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(verticalOffset ==0){
                    Log.d("testoffset", "onOffsetChanged: height="+collapsingtoolbarlayout.getHeight());
                    Log.d("testoffset", "onOffsetChanged: appbar height="+appBarLayout.getHeight());
                }
                Log.d("testoffset", "onOffsetChanged: verticalOffset="+verticalOffset);

                int i = (int)((Math.abs((float)verticalOffset) / dimensionPixelSize)*255);
                Log.d("testoffset", "onOffsetChanged: i="+i);

                tv1.setBackgroundColor(Color.argb(i,255,255,255));
            }
        });


        // 0     0    0
        // -1    1    0.5
        //-432  255 ff 1





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
            View inflate = View.inflate(context, R.layout.item_testgridview, null);
            return new ViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setData(position);

        }

        @Override
        public int getItemCount() {
            return 100;
        }


    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        private MyGridView myGridView;
        int width_item;
        int itemspace;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_test);
            myGridView = itemView.findViewById(R.id.mygridview);
            width_item = tv.getContext().getResources().getDimensionPixelSize(R.dimen.test100);
            itemspace = tv.getContext().getResources().getDimensionPixelSize(R.dimen.test10);
        }

        public void setData(int position){
            tv.setText("test new"+position);
            int size = 0;


            myGridView.setHorizontalSpacing(itemspace);
            myGridView.setVerticalSpacing(itemspace);
            setData(position,width_item,itemspace);

        }


        public void setData( int size, int itemwidth, int itemspace){
            if(size>0){
                myGridView.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams layoutParams = myGridView.getLayoutParams();
                switch (size){
                    case 1:
                        myGridView.setNumColumns(1);
                        layoutParams.width =itemwidth;
                        break;
                    case 2:
                    case 4:
                        myGridView.setNumColumns(2);
                        layoutParams.width =itemwidth*2+itemspace;
                        break;
                    default:
                        myGridView.setNumColumns(3);
                        layoutParams.width =itemwidth*3+itemspace*2;
                        break;
                }
                myGridView.setLayoutParams(layoutParams);
                GridViewAdapter gridViewAdapter = new GridViewAdapter(tv.getContext(),size);
                myGridView.setAdapter(gridViewAdapter);
            }else{
                myGridView.setVisibility(View.GONE);
            }
        }
    }

    public static class GridViewAdapter extends BaseAdapter{

        private int size =4;
        private Context context;
        public GridViewAdapter(Context context,int imgsize){
            size = imgsize;
            this.context = context;
        }

        @Override
        public int getCount() {
            return size;
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
            if(convertView ==null){
                convertView = View.inflate(context,R.layout.item_gridview,null);

            }
            int size = context.getResources().getDimensionPixelSize(R.dimen.test100);
            AbsListView.LayoutParams alp = new AbsListView.LayoutParams(size,size);
            convertView.setLayoutParams(alp);
            return convertView;
        }
    }

    static class HolderGridView {

    }

}
