package org.example.producerConsumer;

import java.util.ArrayList;

public class Worker {
    private int sequence = 0;
    private final Integer MAX;
    private final Integer MIN;
    private final ArrayList<Integer> container;
    private final Object lock = new Object();

    public Worker(Integer min, Integer max) {
        this.MIN = min;
        this.MAX = max;
        this.container = new ArrayList<>();
    }

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (container.size() == MAX) {
                    System.out.println("Container is full, waiting for consumer to consume...");
                    lock.wait();
                } else {
                    System.out.println("Produced: " + sequence);
                    container.add(sequence++);
                    lock.notify();
                }
                Thread.sleep(1000);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (container.size() == MIN) {
                    System.out.println("Container is empty, waiting for producer to produce...");
                    lock.wait();
                } else {
                    System.out.println("Consumed: " + container.removeFirst());
                    lock.notify();
                }
                Thread.sleep(1000);
            }
        }
    }
}