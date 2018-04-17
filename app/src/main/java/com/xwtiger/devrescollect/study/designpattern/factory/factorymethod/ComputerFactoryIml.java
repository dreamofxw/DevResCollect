package com.xwtiger.devrescollect.study.designpattern.factory.factorymethod;

import com.xwtiger.devrescollect.study.designpattern.factory.simplefactory.Computer;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class ComputerFactoryIml extends ComputerFactory {

    @Override
    public <T extends Computer> T createCompuer(Class<T> clz) {
        Computer computer = null;
        String name = clz.getName();
        try {
            computer  = (Computer) Class.forName(name).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) computer;
    }
}
