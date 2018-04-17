
package com.xwtiger.devrescollect.study.designpattern.factory.factorymethod;

import com.xwtiger.devrescollect.study.designpattern.factory.simplefactory.HpComputer;
import com.xwtiger.devrescollect.study.designpattern.factory.simplefactory.LenovoComputer;

/**
 * Created by xwadmin on 2018/4/9.
 */

public class TestAbstractFactory {

    public static void main(String[] args){
        ComputerFactory computerFactoryIml = new ComputerFactoryIml();
        computerFactoryIml.createCompuer(LenovoComputer.class).start();

        computerFactoryIml.createCompuer(HpComputer.class).start();
    }



}
