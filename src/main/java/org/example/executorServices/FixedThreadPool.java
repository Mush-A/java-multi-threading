package org.example.executorServices;

public class FixedThreadPool {

    public static void main(String[] args){
        // Create a fixed thread pool with 5 threads
        try(var executor = java.util.concurrent.Executors.newFixedThreadPool(5)){
            for(int i = 0; i < 50; i++){
                executor.execute(new Task(i));
            }
        }
    }
}
