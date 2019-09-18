package com.xwtiger.devrescollect.test;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwtiger.devrescollect.R;
import com.xwtiger.devrescollect.base.BaseLazyFragment;
import com.xwtiger.devrescollect.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * author:xw
 * Date:2018-12-06 14:06
 * Description:
 * //<editor-fold desc="">
 * //</editor-fold>
 */
public class Fragment_Vp extends BaseLazyFragment{


    private ViewPager vp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_vp, container, false);
        vp = inflate.findViewById(R.id.viewpage);




        return inflate;
    }

    @Override
    protected void lazyLoad() {
        
    }

    static class MyPageAdapter extends FragmentPagerAdapter {

        List<android.support.v4.app.Fragment> list = new ArrayList<>();

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(List<Fragment> list){
            list.addAll(list);
            //notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }


    }
}
