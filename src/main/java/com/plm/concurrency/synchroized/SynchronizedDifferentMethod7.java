package com.plm.concurrency.synchroized;

/**
 * @author chenwenhua
 */
public class SynchronizedDifferentMethod7 implements Runnable{

    static SynchronizedDifferentMethod7 instance = new SynchronizedDifferentMethod7();

    public static void main(String[] args) {
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);
        thread1.start();
        thread2.start();
        while (thread1.isAlive() || thread2.isAlive()) {

        }
        System.out.println("finished");
    }

    @Override
    public void run() {
       if (Thread.currentThread().getName().equals("Thread-0")) {
           method1();
       } else {
           method2();
       }
    }

    public synchronized void method1() {
        System.out.println("同步线程1开始："+ Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"同步线程1运行结束");
    }

    public synchronized void method2() {
        System.out.println("同步线程2开始："+ Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"同步2运行结束");
    }

}
