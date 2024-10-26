package org.example.simple;

public class Main {
    public static void main(String[] args) {

        // Once the threads are created they are in the NEW state
        Thread myThread = new MyThread();
        Thread myRunnable = new Thread(new MyRunnable());

        Thread myThread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello from MyThread2 " + i + "\n");
            }
        });

        // Once they are started they are in the RUNNABLE state
        myThread.start();
        myRunnable.start();
        myThread2.start();
    }
}

// New: A thread that is created but not yet started.
// Runnable: A thread that is ready to run is moved to a runnable state.
// Running: The processor is actively executing the thread code.
// Waiting: A thread is in a blocked state waiting for a resource or waiting for another thread to perform an action.
// Timed Waiting: A thread is in a blocked state waiting for a resource or waiting for another thread to perform an action for a specified waiting time.
// Terminated: A thread that has completed its task is in a terminated state.