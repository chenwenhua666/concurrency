package com.plm.concurrency.synchroized;

/**
 * @author chenwenhua
 * @date 2020\7\10 0010 20:29
 */
public class SynchroizedReentrant10 {
    int a = 0;

    public static void main(String[] args) {
        SynchroizedReentrant10 synchroizedReentrant10 = new SynchroizedReentrant10();
        synchroizedReentrant10.method1();
    }

    private synchronized void method1() {
        System.out.println("这是method1，a=" + a);
        if (a == 0) {
            a++;
            method1();

        }
    }


}
