package org.example.executorServices;

public class CachedThreadPool {

    public static void main(String[] args){
        // Create a cached thread pool
        try(var executor = java.util.concurrent.Executors.newCachedThreadPool()){
            for(int i = 0; i < 50; i++){
                executor.execute(new Task(i));
            }
        }
    }

}
