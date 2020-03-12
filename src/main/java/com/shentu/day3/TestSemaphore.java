package com.shentu.day3;

import java.util.concurrent.Semaphore;
//可以用于限流
public class TestSemaphore {
    public static void main(String[] args) {
//        Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(2,true);//公平竞争
        for (int i = 0; i < 10; i++) {
            final int j = i;
            new Thread (() -> {
                try {
                    s.acquire();
                    Thread.sleep(1000);
                    System.out.println("Thread-" + j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s.release();
                }
            }).start();
        }
    }
}
