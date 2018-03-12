package com.xwtiger.devrescollect;

import com.xwtiger.devrescollect.bean.TestFinalize;

/**
 * Created by xwadmin on 2018/3/2.
 */

public class TestUapte {
    
    public static void main(String[] args)
    {

        TestFinalize tf = new TestFinalize(true);

        System.out.println("create a object");
        //tf = null;
        System.gc();//当对象没有引用的时候就会就会去条用对象的finalize方法，
        // 然后条用垃圾回收机制，例如tf对象没有置空就不会引起垃圾回收因为这个对象
        //还有应用，只有引用为空的时候调用gc才会触发垃圾回收

    }

}
