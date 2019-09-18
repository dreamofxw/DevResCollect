package com.xwtiger.devrescollect.study.javabase.enums;

/**
 * author:xw
 * Date:2018-09-28 17:52
 * Description:
 */
public enum Colors {
    
    RED{
        @Override
        public void handle(String s) {
            super.handle("red"+s);
        }
    },GREEEN{
        @Override
        public void handle(String s) {
            super.handle("GREEEN"+s);
        }
    },ORENGEL;
    
    protected void handle(String s){
        System.out.println(s);
    }
    
}
