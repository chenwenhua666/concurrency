package com.plm.concurrency.synchroized;

/**
 * @author chenwenhua
 * 抛出异常依然可以自动释放锁
 */
public class SynchronizedException9 implements Runnable{

    static SynchronizedException9 instance = new SynchronizedException9();

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
        System.out.println("加锁1："+ Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
        // System.out.println(Thread.currentThread().getName()+"同步线程1运行结束");
    }

    public synchronized void method2() {
        System.out.println("加锁2："+ Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"同步2运行结束");
    }

}
