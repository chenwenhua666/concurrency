package com.plm.concurrency.synchroized;

/**
 * @author chenwenhua
 * @date 2020\7\10 0010 20:29
 */
public class SynchroizedOtherMethod11 {


    public static void main(String[] args) {
        SynchroizedOtherMethod11 synchroizedReentrant10 = new SynchroizedOtherMethod11();
        synchroizedReentrant10.method1();
    }

    public synchronized void method1() {
        System.out.println("method1");
        method2();
    }

    public synchronized void method2() {
        System.out.println("method2");
    }
}
