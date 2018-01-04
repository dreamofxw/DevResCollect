package com.xwtiger.devrescollect.study.datastructuresandalgorithm;

/**
 * Created by Busap-112 on 2018/1/4.
 */

public class fig1_5 {

    public static void main(String[] args){
        MemoryCell memoryCell = new MemoryCell();
        memoryCell.write("37");
        String val = (String) memoryCell.read();
        System.out.println("Contents are:"+val);
    }

    public static  class MemoryCell{

        private Object storedValue;

        public Object read(){
            return storedValue;
        }

        public void write(Object x){
            storedValue =x;
        }

    }

}
