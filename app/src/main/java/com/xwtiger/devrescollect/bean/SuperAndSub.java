package com.xwtiger.devrescollect.bean;

public class SuperAndSub {
  
    public static void main(String[] args) {  
  
        // Super s1 = new Sub();  
        // Super s2 = new Super();  
        Sub s3 = new Sub();

        TestFinalize tf = new TestFinalize(true);


    }  
}  
  
class Super {  
  
    static int a = getA();  
  
    static {  
        System.out.println("加载Super的静态块");  
    }  
  
    int b = getB();  
  
    {  
        System.out.println("加载Super的普通块");  
    }  
  
    Super() {  
        System.out.println("加载Super的构造器");  
    }  
  
    static int getA() {  
        System.out.println("加载Super的静态变量");  
        return 1;  
    }  
  
    static int getB() {  
        System.out.println("加载Super的实例变量");  
        return 2;  
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
  
class Sub extends Super {  
  
    static int c = getC();  
  
    static {  
        System.out.println("加载Sub的静态块");  
    }  
  
    int d = getD();  
  
    {  
        System.out.println("加载Sub的普通块");  
    }  
  
    Sub() {  
        System.out.println("加载Sub的构造器");  
    }  
  
    static int getC() {  
        System.out.println("加载Sub的静态变量");  
        return 1;  
    }  
  
    static int getD() {  
        System.out.println("加载Sub的实例变量");  
        return 2;  
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}