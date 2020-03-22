package com.plm.concurrency.threadcoreknowledge.createthreads;

/**
 * @author chenwenhua
 * @date  2020\3\22 13:23
 */
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("使用Runnable方式实现线程");
    }
}
