package com.shentu.day3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
//LockSupport的park unpark()方法都是调用了Unsafe里面的park()方法
//unpark() 可以在park()前调用，如果在执行过程中调用park()方法就会失去效果
public class TestLockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0;i < 10;i++) {
                System.out.println(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i ==4) {
                    LockSupport.park();
                }
            }
        });
        t.start();
        LockSupport.unpark(t);

    }
}
