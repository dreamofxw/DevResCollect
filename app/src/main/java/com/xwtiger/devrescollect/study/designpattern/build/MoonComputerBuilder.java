package com.xwtiger.devrescollect.study.designpattern.build;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class MoonComputerBuilder extends Builder {

    private Computer mComputer = new Computer();

    @Override
    public void buildCpu(String cpu) {
        mComputer.setmCpu(cpu);
    }

    @Override
    public void buildMainboard(String mainboard) {
        mComputer.setmMainBoard(mainboard);
    }

    @Override
    public void buildRam(String ram) {
        mComputer.setmRam(ram);
    }

    @Override
    public Computer createComputer() {
        return mComputer;
    }
}
