package com.plm.concurrency.synchroized;

/**
 * @author chenwenhua
 * 对象锁实例1，代码块形式
 */
public class SynchronizedObjectMethod3 implements Runnable{

    static SynchronizedObjectMethod3 instance = new SynchronizedObjectMethod3();

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
        method();
    }

    public synchronized void method() {
        System.out.println("线程开始："+ Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"运行结束");
    }

}
