package org.example.executorServices;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledThreadPool {

    public static void main(String[] args){

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        service.scheduleAtFixedRate(new Task(0), 0, 1, java.util.concurrent.TimeUnit.SECONDS);

        try {
            if (!service.awaitTermination(10, java.util.concurrent.TimeUnit.SECONDS)) {
                service.shutdown();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
