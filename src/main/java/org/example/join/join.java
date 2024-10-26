package org.example.join;

public class join {

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread 1: " + i);
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 25; i++) {
                System.out.println("Thread 2: " + i);
            }
        });

        System.out.println("Before executing");

        one.start();
        two.start();

        one.join(); // waits for thread one to finish

        System.out.println("Done");
    }
}

// The main thread is the thread that is created when the program is started.
// It has the highest priority.

// In order to wait for a thread to finish, we can use the join method.
// The join method waits for the thread to finish before continuing with the main thread.


