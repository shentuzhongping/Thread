package com.shentu.day3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock也是可重入锁，可以替代synchronized ,
 * 但有格式要求 try{lock.lock()}finally{lock.unlock()} 最后要手动关闭
 */
public class TestLock {
    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m1,"Thread1").start();

        new Thread(t::m2,"Thread2").start();
    }
}

class T {
    final Lock lock = new ReentrantLock();
    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    void m2() {
        try {
            lock.lock();
            System.out.println("m2 start");
        } finally {
            lock.unlock();
        }
    }
}