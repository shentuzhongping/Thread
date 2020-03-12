package com.shentu.day2;
//volatile的作用
// 1.保证线程间可见
// 2.禁止指令重排序
public class TestVolatile {
    public static void main(String[] args) {
        T1 t = new T1();
        new Thread(t::m,"t1").start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        t.running = false;
        System.out.println("0");
    }

}

class T1{
    /*volatile*/ boolean running = true;
    int i = 0;
    void m() {
        System.out.println("I am starting");
        while (running) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(i++);
        }
        System.out.println("I am stopping");
    }
}
