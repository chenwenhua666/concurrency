package com.plm.concurrency.synchroized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenwenhua
 * @date 2020\7\10 0010 21:06
 */
public class SynchroizedLock13 {
    Lock lock = new ReentrantLock();

    public synchronized void method1() {
        System.out.println("sync形式的锁");
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("lock锁");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchroizedLock13 lock13 = new SynchroizedLock13();
        lock13.method1();
        lock13.method2();
    }

}
