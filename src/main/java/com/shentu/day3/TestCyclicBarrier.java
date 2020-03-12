package com.shentu.day3;

import java.util.concurrent.CyclicBarrier;
//循环砸烂锁
public class TestCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(5,() -> System.out.println("满员"));
        for (int i = 0;i < 10;i++) {
            new Thread(() -> {
                try {
                    cb.await();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
