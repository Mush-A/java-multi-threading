package org.example.daemon;

public class Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Hello from normal thread\n");
            }
            System.out.println("Thread is done");
        });

        Thread daemonThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("Hello from daemon thread\n");
            }
            System.out.println("Daemon thread is done");
        });

        daemonThread.setDaemon(true);

        thread.start();
        daemonThread.start();
    }
}

// A daemon thread is a thread that does not prevent the JVM from exiting when the program finishes but the thread is still running.
// The JVM will only exit when all the non-daemon threads have finished.
// The JVM will exit even if there are daemon threads running.
// Daemon threads are used for background tasks such as garbage collection and other maintenance tasks.