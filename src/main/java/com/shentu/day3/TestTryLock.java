package com.shentu.day3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock : 尝试加锁，可以给时间，如果在给定的时间内拿不到锁，就会加锁无效，相当与没加锁
 * lock.tryLock有返回值，如果是true是加锁成功，如果是false是加锁失败
 */
public class TestTryLock {
    public static void main(String[] args) {
        T1 t = new T1();
        new Thread(t::m1,"Thread1").start();

        new Thread(t::m2,"Thread2").start();
    }
}

class T1 {
    final Lock lock = new ReentrantLock();
    void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
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
        boolean locked = false;
        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked) lock.unlock();
        }
    }
}
