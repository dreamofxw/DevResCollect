package com.xwtiger.devrescollect.study.designpattern.factory;

/**
 * Created by xwadmin on 2017/12/23.
 */

public abstract class PizzaStore {

    public Pizza orderPizza(String type){
        Pizza pizza = null;

        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    /**
     * 工厂方法
     * @param type
     * @return
     */
    public abstract  Pizza createPizza(String type);

}
