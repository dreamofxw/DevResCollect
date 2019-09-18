package com.xwtiger.devrescollect.study.annotation;

import com.xwtiger.devrescollect.MyException;

import java.lang.reflect.Field;

/**
 * Created by Busap-112 on 2018/1/15.
 */

public class Apple {


    public static void main(String[] args){

       // Class apple = Apple.class.getClass();
        TestBean apple = new TestBean();
        try {
            Field appleColor = apple.getClass().getDeclaredField("name");
            appleColor.setAccessible(true);
            if(appleColor.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitcolor = appleColor.getAnnotation(FruitColor.class);
                appleColor.set(apple,fruitcolor.fruitColor().getName());
                //apple.setAppleColor(fruitcolor.fruitColor().getName());
                System.out.println(fruitcolor.fruitColor().getPosition());
                System.out.println(appleColor.getName());
                System.out.println(appleColor.get(apple));
            }

        } catch (NoSuchFieldException e) {
            MyException.printStr(e);
        } catch (IllegalAccessException e) {
            MyException.printStr(e);
        }

    }


    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.BULE)
    private String appleColor;


    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleColor() {
        return appleColor;
    }

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }
}
