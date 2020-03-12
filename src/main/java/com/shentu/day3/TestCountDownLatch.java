package com.shentu.day3;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        Thread[] threads = new Thread[100];

        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> {
                try {
                    Thread.sleep(100);
                    System.out.println("thread-" + finalI);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            });
        }
        new Thread (() -> {
            try {
                latch.await();
                System.out.println("i am starting");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

    }
}
