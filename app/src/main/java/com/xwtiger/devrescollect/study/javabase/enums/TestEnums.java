package com.xwtiger.devrescollect.study.javabase.enums;

/**
 * author:xw
 * Date:2018-09-28 17:55
 * Description:
 */
public class TestEnums {


    public static Colors mColors;

    public static void main(String[] args){

        testColors(Colors.RED);
    }


    public static void testColors(Colors c){
        switch (c){
            case RED:
                c.handle("aaaa11");
                break;
            case GREEEN:
                c.handle("aaaa22");
                break;
            case ORENGEL:
                c.handle("aaaa33");
                break;
        }
    }

}
