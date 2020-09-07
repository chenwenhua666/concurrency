package com.plm.concurrency.synchroized;

/**
 * @author chenwenhua
 */
public class DisappearRequest1 implements Runnable {
    static DisappearRequest1 disappearRequest1 = new DisappearRequest1();
    static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(disappearRequest1);
        Thread thread2 = new Thread(disappearRequest1);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(i);
    }

    @Override
    public synchronized void run() {
        for (int j = 0; j < 100000 ; j++) {
            i++;
        }
    }
}
