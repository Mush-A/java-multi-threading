package org.example.executorServices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {

    public static void main(String[] args){
        try(ExecutorService executor = Executors.newSingleThreadExecutor()){
            for(int i = 0; i < 50; i++){
                executor.execute(new Task(i));
            }
        }
    }

}
