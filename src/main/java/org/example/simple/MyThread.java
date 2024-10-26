package org.example.simple;

public class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello from MyThread " + i + "\n");
        }
    }
}
