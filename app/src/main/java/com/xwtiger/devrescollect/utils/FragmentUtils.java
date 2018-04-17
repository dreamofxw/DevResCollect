package com.xwtiger.devrescollect.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by xwadmin on 2018/4/17.
 */

public class FragmentUtils {

    public static synchronized void addActivityToFragment(FragmentManager fm, Fragment fragment, int fragmentid){
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(fragmentid,fragment);
        fragmentTransaction.commit();
    }

}
