package com.xwtiger.devrescollect.study.designpattern.build;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class CreateComputer {

    public static void main(String[] args){
        Builder builder = new MoonComputerBuilder();
        Director director = new Director(builder);
        director.createComputer("i5","mainboard","ram");
    }
}
