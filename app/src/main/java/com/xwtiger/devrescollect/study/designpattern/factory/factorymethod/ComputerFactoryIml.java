package com.xwtiger.devrescollect.study.designpattern.factory.factorymethod;

import com.xwtiger.devrescollect.MyException;
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
            MyException.printStr(e);
        } catch (IllegalAccessException e) {
            MyException.printStr(e);
        } catch (ClassNotFoundException e) {
            MyException.printStr(e);
        }
        return (T) computer;
    }
}
