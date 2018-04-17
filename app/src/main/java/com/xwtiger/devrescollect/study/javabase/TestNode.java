package com.xwtiger.devrescollect.study.javabase;

/**
 * Created by xwadmin on 2018/4/7.
 */

public class TestNode<E> {
    public E item;
    public TestNode pre;
    public TestNode next;

    public TestNode(E item ,TestNode pre,TestNode next){
        this.item = item;
        this.pre = pre;
        this.next = next;
    }




}
