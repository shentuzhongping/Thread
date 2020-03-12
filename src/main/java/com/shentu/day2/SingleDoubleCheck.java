package com.shentu.day2;
// 2.禁止指令重排序
//在非常高的并发的情况下，对象是已经new出来了，但赋值给INSTANCE还可能是null;
public class SingleDoubleCheck {
    private /*volatile*/ static SingleDoubleCheck INSTANCE;
    private SingleDoubleCheck(){}
    public static SingleDoubleCheck getInstance () {
        if (INSTANCE == null) {
            synchronized (SingleDoubleCheck.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingleDoubleCheck();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {

    }
}
