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

    public static String REG_POS = ".*+f00";
    public static String REG_REL = ".*?f00";
    public static String REG_GRE = ".*f00";
    /**
     * 官方表示
     *  Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aaaaab");
        boolean b = m.matches();
     */
    public static boolean regex(String reg,String input){
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(input);
        return matcher.matches();
    }

    public static boolean regex2(String reg,String input){
        return Pattern.matches(reg,input);
    }

    public static void regex3(String reg,String input){
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(input);
        while (matcher.find()) {
            System.out.println("matched form " + matcher.start() + " to " + matcher.end());
        }
    }

    /**
     *
     *  Greedy Reluctant Possessive
     *  贪婪的  勉强的    所有的
     *
     * 这三者的区别
     * greedy量词被看作“贪婪的”，因为它们在试图搜索第一个匹配之前读完（或者说吃掉）整个输入字符串。
     * 如果第一个匹配尝试（整个输入字符串）失败，匹配器就会在输入字符串中后退一个字符并且再次尝试，
     * 重复这个过程，直到找到匹配或者没有更多剩下的字符可以后退为止。根据表达式中使用的量词，
     * 它最后试图匹配的内容是1个或者0个字符。
     *
     但是，reluctant量词采取相反的方式：它们从输入字符串的开头开始，
     然后逐步地一次读取一个字符搜索匹配。
     它们最后试图匹配的内容是整个输入字符串。

     最后，possessive量词总是读完整个输入字符串，
     尝试一次（而且只有一次）匹配。和greedy量词不同，
     possessive从不后退，即使这样做能允许整体匹配成功
     *
     * Possessive 一般针对精确的正则；eg:".{5}+f00"(精确了具体的数字)
     *
     */



}
