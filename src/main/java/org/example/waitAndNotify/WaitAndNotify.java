package org.example.waitAndNotify;

public class WaitAndNotify {

    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread one = new Thread(() -> {
            try {
                methodOne();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread two = new Thread(() -> {
            try {
                methodTwo();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Start both threads
        one.start();
        two.start();
}

    private static void methodOne() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Hello from method one");
            lock.wait();
            System.out.println("Hello again from method one");
        }
    }

    private static void methodTwo() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Hello from method two");
            lock.notify();
            System.out.println("Hello again from method two");
        }
    }
}
