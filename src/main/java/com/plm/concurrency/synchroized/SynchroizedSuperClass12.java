package com.plm.concurrency.synchroized;

/**
 * @author chenwenhua
 * @date 2020\7\10 0010 20:29
 */
public class SynchroizedSuperClass12 {

    public synchronized void dosomething() {
        System.out.println("我是父类方法");
    }
}
class Test extends SynchroizedSuperClass12 {
    public synchronized void dosomething() {
        System.out.println("我是子类方法");
        super.dosomething();
    }

    public static void main(String[] args) {
        new Test().dosomething();
    }
}