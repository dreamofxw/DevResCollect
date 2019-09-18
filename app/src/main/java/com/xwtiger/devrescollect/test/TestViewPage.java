package com.xwtiger.devrescollect.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseActivity;
import com.xwtiger.devrescollect.view.TestArcView;

import java.util.ArrayList;
import java.util.List;

public class TestViewPage extends BaseActivity{

    private static String TAG ="testviewpage";

    ViewPager viewPager;
    TestArcView testArcView;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.testviewpage);
        init();

    }

    @Override
    public void initView() {
        viewPager = findViewById(R.id.viewpager);
        testArcView = findViewById(R.id.testarcview);
        list.add("#ff0000");
        list.add("#00ff00");
        list.add("#0000ff");
        list.add("#111111");
        list.add("#555555");
    }

    @Override
    public void initData() {
        viewPager.setAdapter(new TestAdapter());
    }


    private boolean isleft = true;
    private int lastVlaue ;
    private boolean isScrolledSuccess = false;
    private int preposition = 0;
    private int isScrollleft = 0;//没有1左，2 右

    @Override
    public void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG,"onPageScrolled ======position="+position);
//                Log.d(TAG,"onPageScrolled ======positionOffset="+positionOffset);
//                Log.d(TAG,"onPageScrolled ======positionOffsetPixels="+positionOffsetPixels);

                if(lastVlaue >positionOffsetPixels){
                    isleft = false;
                    isScrollleft = 2;
                }else{
                    isleft = true;
                    isScrollleft =1;
                }
                lastVlaue = positionOffsetPixels;

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG,"onPageSelected --------------+position="+position);
                isScrolledSuccess = true;
                preposition = position;

                testArcView.changBackground(list.get(preposition),1);

            }

            //viewpage滑动流程
            //正常1--2---0                                   (如果切换了就会调用该方法)
            //onPageScrollStateChanged（1） ---  onPageScrolled ---onPageSelected ---onPageScrolled--onPageScrollStateChanged

            @Override
            public void onPageScrollStateChanged(int state) {
                //1,拖拽  2，滑动  0 空闲
                Log.d(TAG,"onPageScrollStateChanged >>>>>>>>>>>>>>state="+state+"\\\\isScrollleft ="+isScrollleft);
                switch (state){
                    case 1://拖拽
//                        if(isScrollleft ==1){
//                            testArcView.changBackground(list.get(preposition+1>=5?4:preposition+1),0.5f);
//                        }else if(isScrollleft ==2){
//                            testArcView.changBackground(list.get(preposition-1<0?0:preposition-1),0.5f);
//                        }
                        break;
                    case 2://滑动
//                        if(isScrollleft ==1){
//                            testArcView.changBackground(list.get(preposition+1>=5?4:preposition+1),0.5f);
//                        }else if(isScrollleft ==2){
//                            testArcView.changBackground(list.get(preposition-1<0?0:preposition-1),0.5f);
//                        }

                        break;
                    case 0://空闲
                        isScrolledSuccess = false;
                        isScrollleft = 0;
                        testArcView.changBackground(list.get(preposition),1);
                        break;

                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }



    class TestAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view ==object;
        }


        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            ImageView imageView = new ImageView(TestViewPage.this);
            String colorstring = list.get(position);
//            if(position ==0){
//                colorstring = "#ff0000";
//            }else if(position ==1){
//                colorstring = "#00ff00";
//            }else if(position ==2){
//                colorstring = "#0000ff";
//            }else if(position ==3){
//                colorstring = "#00ffff";
//            }else if(position ==4){
//                colorstring = "#111111";
//            }
            imageView.setBackgroundColor(Color.parseColor(colorstring));
            container.addView(imageView);
            return imageView;
        }


        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View)object);
        }
    }
}
