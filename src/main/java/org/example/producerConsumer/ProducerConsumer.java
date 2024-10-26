package org.example.producerConsumer;

public class ProducerConsumer {
   public static void main(String[] args) {
      Worker worker = new Worker(0, 5);

      Thread producer = new Thread(() -> {
         try {
            worker.producer();
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });

      Thread consumer = new Thread(() -> {
         try {
            worker.consumer();
         } catch (InterruptedException e) {
            throw new RuntimeException(e);
         }
      });

      producer.start();
      consumer.start();
   }
}

