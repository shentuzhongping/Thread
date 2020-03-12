package com.shentu.day3;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//读写锁
public class TestReadWriteLock {
    private static final Lock lock = new ReentrantLock();
    private static final ReadWriteLock READ_WRITE_LOCK = new ReentrantReadWriteLock();
    private static final Lock read = READ_WRITE_LOCK.readLock();
    private static final Lock write = READ_WRITE_LOCK.writeLock();

    public static void main(String[] args) {
        Random r = new Random(10);
        T3 t3 = new T3();
        for (int i = 0;i < 2;i++) {
            new Thread(() -> t3.write(lock,r.nextInt())).start();
        }
        for (int i = 0;i < 10;i++) {
            new Thread(() -> t3.read(read)).start();
        }
        for (int i = 0;i < 2;i++) {
            new Thread(() -> t3.write(write,r.nextInt())).start();
        }

    }

}

class T3 {
    private Random random = new Random(1000);
    private int i;
    public void minitWait() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void read (Lock lock) {
        try {
            lock.lock();
            minitWait();
            System.out.println("read");
        } finally {
            lock.unlock();
        }
    }
    public void write (Lock lock,int v) {
        try {
            lock.lock();
            minitWait();
            i = v;
            System.out.println("write");
        } finally {
            lock.unlock();
        }
    }
}
