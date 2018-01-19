package com.xwtiger.devrescollect.study.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.security.PublicKey;

/**
 * Created by Busap-112 on 2018/1/15.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {

    public enum Color{
        BULE("蓝色",0),RED,GREEN;

        private String name;
        private int position;
        Color(){
            System.out.println("null constraction");
        }

         Color(String name,int position){
             this.name = name;
             this.position = position;
             System.out.println("has args constraction");
         }


        public String getName() {
            return name;
        }

        public int getPosition() {
            return position;
        }
    };

    Color fruitColor() default Color.GREEN;

}
