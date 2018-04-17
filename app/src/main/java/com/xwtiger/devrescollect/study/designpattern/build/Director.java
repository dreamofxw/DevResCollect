package com.xwtiger.devrescollect.study.designpattern.build;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class Director {
    private Builder mBuilder;

    public Director(Builder builder){
        this.mBuilder = builder;
    }

    public Computer createComputer(String cpu,String mainboard,String ram){
        mBuilder.buildCpu(cpu);
        mBuilder.buildMainboard(mainboard);
        mBuilder.buildRam(ram);
        return mBuilder.createComputer();
    }

}
