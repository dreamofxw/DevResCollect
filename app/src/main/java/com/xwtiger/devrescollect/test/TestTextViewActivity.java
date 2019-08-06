package com.xwtiger.devrescollect.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.utils.Utils;
import com.xwtiger.devrescollect.view.ExpandTextView;

public class TestTextViewActivity extends BaseActivity{


    private TextView tv_null;
    private TextView tv_context;
    private GridView gridView;
    private TextView tv_expandable;
    private ExpandTextView expandtextview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testtextview);
        init();

    }

    @Override
    public void initView() {

        tv_context = findViewById(R.id.tv_content);
        String desc = "中国中大短发短发短发久了；阿简单阿大发大发中国中大短发短发短发久了；阿简单阿大发大发中国中大短发短发短发久了；阿简单阿大发大发中国中大短发短发短发久了；阿简单阿大发大";
        tv_expandable = findViewById(R.id.tv_expandable);

        gridView = findViewById(R.id.gridview);
        gridView.setAdapter(new TestAdapter());

        RelativeLayout.LayoutParams rllp =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
        rllp.addRule(RelativeLayout.BELOW,R.id.fl_top);
        rllp.topMargin =50;
        gridView.setLayoutParams(rllp);

        tv_context.setText(Utils.getSpannableString(desc,100));


        expandtextview = findViewById(R.id.expandtextview);

        int whidth = Utils.getScreenWidth(this);
        expandtextview.initWidth(whidth);
        // 设置最大行数(如果SDK >= 16 也可以直接在xml里设置)
        expandtextview.setMaxLines(3);
        String content = "茫茫的长白大山，浩瀚的原始森林，大山脚下，原始森林环抱中散落着几十户人家的" +
                "一个小山村，茅草房，对面炕，烟筒立在屋后边。在村东头有一个独立的房子，那就是青年点，" +
                "窗前有一道小溪流过。学子在这里吃饭，由这里出发每天随社员去地里干活。干的活要么上山伐" +
                "树，抬树，要么砍柳树毛子开荒种地。在山里，可听那吆呵声：“顺山倒了！”放树谨防回头棒！" +
                "树上的枯枝打到别的树上再蹦回来，这回头棒打人最厉害。";

        //String content ="树上的枯枝打到别的树上再蹦回来";
        expandtextview.setCloseText(content);

        expandtextview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        expandtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestTextViewActivity.this,"click",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }


    public class TestAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 30;
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
            TextView tv = new TextView(TestTextViewActivity.this);
            tv.setText("testview"+position);
            return tv;
        }
    }
}
