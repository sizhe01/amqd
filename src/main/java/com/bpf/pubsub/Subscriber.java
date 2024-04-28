package com.bpf.pubsub;

public class Subscriber {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Subscribe());
        t1.setName("订阅者1");
        Thread t2 = new Thread(new Subscribe());
        t2.setName("订阅者2");
        t1.start();
        t2.start();
    }

}
