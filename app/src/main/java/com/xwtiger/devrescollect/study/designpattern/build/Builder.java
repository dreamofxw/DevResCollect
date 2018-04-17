package com.xwtiger.devrescollect.study.designpattern.build;

/**
 * Created by xwadmin on 2018/4/9.
 */

public abstract class Builder {
    public abstract void buildCpu(String cpu);
    public abstract void buildMainboard(String mainboard);
    public abstract  void buildRam(String ram);
    public abstract Computer createComputer();
}
