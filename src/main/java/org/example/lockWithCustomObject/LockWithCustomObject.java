package org.example.lockWithCustomObject;

public class LockWithCustomObject {

    private static int counter1 = 0;
    private static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment1();
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment2();
            }
        });

        // Start both threads
        one.start();
        two.start();

        // Wait for both threads to finish before continuing
        one.join();
        two.join();

        // Print the values of the counters
        System.out.println(counter1);
        System.out.println(counter2);
    }

    private static synchronized void increment1() {
        synchronized (lock1) {
            counter1++;
        }
    }

    private static void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }
}
