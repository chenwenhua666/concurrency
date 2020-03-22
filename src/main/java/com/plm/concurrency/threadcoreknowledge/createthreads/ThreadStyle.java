package com.plm.concurrency.threadcoreknowledge.createthreads;

/**
 * @author chenwenhua
 * @date  2020\3\22 13:23
 */
public class ThreadStyle extends Thread {

    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run() {
        System.out.println("使用thread方式实现线程");
    }
}

