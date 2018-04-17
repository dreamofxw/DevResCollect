package com.xwtiger.devrescollect.study.thread.lock;

/**
 * Created by xwadmin on 2018/4/6.
 */

public class TestLockClass {

    public static void main(String[] args) {

        TestForSynchronized temp = new TestForSynchronized();
        for (int index = 0; index < 10; index++) {
            MyThread thread = new MyThread(temp);
            thread.start();
        }
        System.out.println("main thread end");

    }

    static class MyThread extends Thread {

        private TestForSynchronized testForSynchronized;

        public MyThread(TestForSynchronized testForSynchronized) {
            this.testForSynchronized = testForSynchronized;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "--" + testForSynchronized.setID_02());
            System.out.println(Thread.currentThread().getName() + "--" + testForSynchronized.setID_01());

        }
    }
}
    

