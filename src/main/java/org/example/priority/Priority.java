package org.example.priority;

public class Priority  {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 1 is running");
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Thread 2 is running");
            }
        });

        thread.setPriority(10);
        thread2.setPriority(1);

        thread.start();
        thread2.start();
    }
}
