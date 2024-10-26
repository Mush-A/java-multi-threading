package org.example.executorServices;

public class Task implements Runnable{

    private final int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task is " + taskId + " running on thread " + Thread.currentThread().getName());
    }
}
