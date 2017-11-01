package com.xwtiger.devrescollect.study.javaapi;

/**
 * Created by Busap-112 on 2017/11/1.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 研究正则表达式的使用
 *
 *
 */
public class PatternStudy {

    public static String reg = "";
    /**
     * 官方表示
     *  Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aaaaab");
        boolean b = m.matches();
     */
    public static boolean regex(String input){
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(input);
        return matcher.matches();
    }

    public static boolean regex2(String input){
        return Pattern.matches(reg,input);
    }

    /**
     *
     *  Greedy Reluctant Possessive
     *  贪婪的  勉强的    所有的
     *
     * 这三者的区别
     *
     *
     *
     *
     */



}
