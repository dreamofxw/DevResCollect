package com.xwtiger.devrescollect.study.androidapi.draw.testdraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;

public class TestDrawActivity extends BaseActivity {


    private TestRelativelayout testrelativelayout;
    private TestTextView1 textview1;
    private TestFragmentLayout testFragmentLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_testdraw);
        testrelativelayout = findViewById(R.id.testrelativelayout);
        textview1 = findViewById(R.id.textview1);

        if(testFragmentLayout ==null){
            testFragmentLayout = new TestFragmentLayout(this);
            testFragmentLayout.setListenerDraw(new TestFragmentLayout.IListenerDraw() {
                @Override
                public boolean onDrawEnd() {
                    Log.d("testdrawprocess", "onDrawEnd: ");
                    return false;
                }
            });
        }

        ViewParent parent = testrelativelayout.getParent();

        if(parent instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.addView(testFragmentLayout);
        }

        while(parent !=null){
            Log.d("testparent", "onCreate: parent="+parent);
            if(parent instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup) parent;
                for(int i=0;i<viewGroup.getChildCount();i++){
                    View childAt = viewGroup.getChildAt(i);
                    Log.d("testparent", "onCreate: childAt="+childAt);
                }
            }
            parent = parent.getParent();
        }



        textview1.setOnClickListener(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textview1:
                Toast.makeText(this,"点击textview1",Toast.LENGTH_SHORT).show();
                break;
        }
    }



}
