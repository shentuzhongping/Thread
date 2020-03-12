package com.shentu.day2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;
//atomic包下的类对常用数据类型的计算进行了封装，进行了CAS（compare and set） 优化  自旋/乐观锁
//保证了该变量的原子性

/**
 * 这个包里面的类在进行运算的时候会用到Unsafe类里面的方法，它的用法和c c++类似，可以直接操作内存
 * 最主要的是里面有个   compareAndSet(v,except,newvalue)
 * 里面的逻辑是，在做运算前，我先记录一下这个值，作为except值，
 * 然后在修改这个变量的值的时候，会比较这个变量的现在值v和except，如果相等，就会把新值newValue赋值给变量
 * 如果不相等，就会记录下现在的值作为except,再执行一遍运算，直到把值赋值给变量
 * 在赋值过程中，执行的是CPU原语，不可能被其他线程打断的
 * 有ABA问题，jdk老版本可能有这个问题，新版本会对v进行版本判断，每改变一次，版本号加一
 */
public class CASTest {
    public static void main(String[] args) {
        T3 t = new T3();
        Collection<Thread> list = new ArrayList<>();
        for (int j = 0;j < 10;j++) {
            list.add(new Thread(t::m,"Thread-" + j));
        }
        list.stream().forEach((o) -> o.start());
        list.stream().forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });//如果不加线程合并，main线程中打印出来的值就不是最后值了
        System.out.println(t.toi);
    }
}
class T3 {
    AtomicInteger toi = new AtomicInteger(0);
    void m() {
        for (int i = 0;i < 10000;i++) {
            toi.incrementAndGet();
        }
    }
}
