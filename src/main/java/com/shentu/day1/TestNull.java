package com.shentu.day1;

//锁对象如果是变成null会报空指针
public class TestNull {
    public static void main(String[] args) {
        B b = new B();
        new Thread(() -> b.m()).start();
        b.o = null;
    }
}
class B {
    volatile Object o = new Object();
    public void m () {
        synchronized (o) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(1);
        }
    }
}