package org.example.simple;

// Runnable is an interface that is implemented by a class whose instances are intended to be executed by a thread.
// This is more flexible than extending Thread class because Java does not support multiple inheritance.
// The class implementing Runnable interface can extend another class.
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            System.out.println("Hello from My Runnable " + i + "\n");
        }
    }
}
