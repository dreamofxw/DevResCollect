package com.xwtiger.devrescollect.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.test.live.CustomerFrameLayout;
import com.xwtiger.devrescollect.test.live.IPanelView;
import com.xwtiger.devrescollect.test.live.TestPanelView1;

public class TestLiveActivity extends BaseActivity {


    private CustomerFrameLayout customcontainer;
    private FrameLayout fl_headcontainer;
    private FrameLayout fl_contentcontainer;
    private TextView tv_click1;

    private IPanelView panelView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testliveact);
        init();
    }

    @Override
    public void initView() {
        customcontainer = findViewById(R.id.customcontainer);
        fl_headcontainer = findViewById(R.id.fl_headcontainer);
        fl_contentcontainer = findViewById(R.id.fl_contentcontainer);
        tv_click1 = findViewById(R.id.tv_click1);


    }

    @Override
    public void initData() {

    }

    @Override
    public void setListener() {
        tv_click1.setOnClickListener(this);
        customcontainer.setiInterceptEvent(iInterceptEvent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_click1:
                Log.d("testclickoutside", "onClick: click");
                if(panelView ==null){
                    panelView = new TestPanelView1(this,fl_headcontainer);
                }
                panelView.show();
                break;
        }
    }

    CustomerFrameLayout.IInterceptEvent iInterceptEvent = new CustomerFrameLayout.IInterceptEvent() {
        @Override
        public void interceptEvent(MotionEvent ev) {
            if(panelView !=null){
//                Log.d("testclickoutside", "interceptEvent: rawX ="+rawX);
//                Log.d("testclickoutside", "interceptEvent: rawY ="+rawY);
                if(panelView instanceof TestPanelView1){
                    Log.d("testclickoutside", "interceptEvent: 111111");
                    TestPanelView1 testPanelView1 = (TestPanelView1) panelView;
                    if(!testPanelView1.isNeedHide()){
                        return;
                    }
                    Log.d("testclickoutside", "interceptEvent: 22222");
                    float rawX = ev.getRawX();
                    final float rawY = ev.getRawY();
                    int left = testPanelView1.getLeft();
                    int getwidth = testPanelView1.getwidth();
                    int top = testPanelView1.getTop();
                    int height = testPanelView1.getHeight();

//                    Log.d("testclickoutside", "interceptEvent: left ="+left+",left+getwidth="+(left+getwidth));
//                    Log.d("testclickoutside", "interceptEvent: top ="+top+",ltop+height="+(top+height));

                    if((rawX <left ||rawX >(left+getwidth))||(rawY<top||rawY>(top+height))){
                        Log.d("testclickoutside", "interceptEvent: 3333");
                        panelView.hide();
                    }
                }


            }
        }
    };

}
