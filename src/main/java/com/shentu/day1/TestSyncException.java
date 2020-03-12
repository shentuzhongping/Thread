package com.shentu.day1;

//某个线程执行某段同步代码的时候，
// 如果遇到异常，并且没有处理，改线程会结束(terminated)，并且释放锁对象
//如果处理了异常，线程就会继续执行
public class TestSyncException {
    public static void main(String[] args) {
        A a = new A();

        Thread t1 = new Thread (() -> a.m());
        Thread t2 = new Thread (() -> {
            System.out.println(t1.getState());
            a.m1(t1);
        });

        t2.start();
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t1.start();

    }

}
class A {
    public synchronized void m() {
        System.out.println("start");
        int i = 1;
        while (i < 10) {
            synchronized (this) {
                System.out.println(i++);
            }
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (i == 5) {
//                try{
                    i = i/0;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
    public /*synchronized*/ void m1(Thread t) {
//        System.out.println(Thread.currentThread().getName() + "start");
        synchronized (this) {
            for (int i = 0; i < 15 ;i++) {
                System.out.println(Thread.currentThread().getName() + i);
                System.out.println(t.getState());
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}