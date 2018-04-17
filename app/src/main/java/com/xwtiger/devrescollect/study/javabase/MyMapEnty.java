package com.xwtiger.devrescollect.study.javabase;

/**
 * Created by xwadmin on 2018/3/23.
 */

public class MyMapEnty<K,V> {

    private K key;
    private V value;
    private MyMapEnty next;

    public MyMapEnty(K key,V value ,MyMapEnty next){
        this.key = key;
        this.value = value;
        this.next = next;
    }

}
