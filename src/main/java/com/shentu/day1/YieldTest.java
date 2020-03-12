package com.shentu.day1;

public class YieldTest {
    public static void main(String[] args) {
//        thread();
        yield();
    }

    public static void thread () {
        new Thread(() -> {
            for (int i = 0;i < 20;i++) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 20; i < 40; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public static void yield () {
        Thread t1 = new Thread(() -> {
            for (int i = 0;i < 20;i++) {
                System.out.println(Thread.currentThread().getName() + i);
                try {
                    Thread.sleep(100);
                    Thread.yield();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 20; i < 40; i++) {
                System.out.println(Thread.currentThread().getName() + i);
                try {
                    Thread.sleep(100);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
