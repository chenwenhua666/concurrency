package com.plm.concurrency.threadcoreknowledge.createthreads;

/**
 * @author chenwenhua
 * @date 2020\3\22 0022 14:14
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();

    }
}
