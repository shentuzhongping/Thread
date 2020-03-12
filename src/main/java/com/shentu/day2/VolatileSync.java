package com.shentu.day2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * volatile 只能保证线程间可见，但不能保证原子性
 * 也就是说变量变了，可以看到，但当把变了之后的值，同步到堆内存的时候，就不可控了
 * 所以volatile是不能替代synchronized的
 */
public class VolatileSync {
    public static void main(String[] args) {
        T2 t = new T2();
        Collection<Thread> list = new ArrayList<>();
        for (int i = 0;i < 10;i++) {
//            list.add(new Thread(() -> t.m()));
            list.add(new Thread(t::m,"thread-" + i));
        }
        list.stream().forEach((o) -> o.start());
        list.stream().forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });//加了线程合并 97358
        System.out.println(t.count);
    }
}
class T2 {
    volatile int count = 0;
    void m () {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }
}
