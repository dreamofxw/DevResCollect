package com.xwtiger.devrescollect.study.designpattern.factory.factorymethod;

import com.xwtiger.devrescollect.study.designpattern.factory.simplefactory.Computer;

/**
 * Created by xwadmin on 2018/4/9.
 * 抽象工厂
 */

public abstract class ComputerFactory {
    public abstract <T extends Computer> T createCompuer(Class<T> clz);
}
