package com.example.libjava2;

public class TestChild extends TestParent{


    @Override
    public void test() {
        super.test();
        System.out.println("child print a message");
    }
}
