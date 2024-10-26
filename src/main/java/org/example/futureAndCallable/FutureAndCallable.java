package org.example.futureAndCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureAndCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService excecutorService = Executors.newFixedThreadPool(2);

        Future<Integer> result = excecutorService.submit(new Task());

        // This will block the main thread until the result is available
        System.out.println(result.get());
        System.out.println("Main thread is done");
    }
}
