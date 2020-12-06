package com.example.libjava2;

public class TestBean {

    public String name;
    public String desc;

    public TestBean(String name,String desc){
        this.name = name;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void test11(){};


    public class TestModel{

        public void test(){
            System.out.println("test outer"+this);
        }
    }
}
