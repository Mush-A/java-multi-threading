package org.example;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class TicketSimulation {
    private int ticketsAvailable;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketSimulation(int initialTickets) {
        this.ticketsAvailable = initialTickets;
    }

    // Method for sellers to add tickets
    public void sellTicket(int tickets) {
        lock.lock();
        try {
            ticketsAvailable += tickets;
            System.out.println(Thread.currentThread().getName() + " sold " + tickets + " tickets. Total available: " + ticketsAvailable);
        } finally {
            lock.unlock();
        }
    }

    // Method for buyers to buy tickets
    public void buyTicket(int tickets) {
        lock.lock();
        try {
            if (ticketsAvailable >= tickets) {
                ticketsAvailable -= tickets;
                System.out.println(Thread.currentThread().getName() + " bought " + tickets + " tickets. Tickets left: " + ticketsAvailable);
            } else {
                System.out.println(Thread.currentThread().getName() + " tried to buy " + tickets + " tickets but only " + ticketsAvailable + " available.");
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final int initialTickets = 50;
        TicketSimulation simulation = new TicketSimulation(initialTickets);

        // Executor services for sellers and buyers
        ScheduledExecutorService sellerService = Executors.newScheduledThreadPool(2);
        ScheduledExecutorService buyerService = Executors.newScheduledThreadPool(5);

        // Simulating sellers adding tickets at a fixed rate
        Runnable sellerTask = () -> simulation.sellTicket((int) (Math.random() * 5) + 1); // Randomly sells 1-5 tickets
        sellerService.scheduleAtFixedRate(sellerTask, 0, 2, TimeUnit.SECONDS); // Sells every 2 seconds

        // Simulating buyers buying tickets at a fixed rate
        Runnable buyerTask = () -> simulation.buyTicket((int) (Math.random() * 3) + 1); // Randomly buys 1-3 tickets
        buyerService.scheduleAtFixedRate(buyerTask, 0, 1, TimeUnit.SECONDS); // Buys every second

        // Run the simulation for a fixed amount of time
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            System.out.println("Stopping simulation...");
            sellerService.shutdown();
            buyerService.shutdown();
            try {
                if (!sellerService.awaitTermination(5, TimeUnit.SECONDS)) {
                    sellerService.shutdownNow();
                }
                if (!buyerService.awaitTermination(5, TimeUnit.SECONDS)) {
                    buyerService.shutdownNow();
                }
            } catch (InterruptedException e) {
                sellerService.shutdownNow();
                buyerService.shutdownNow();
            }
        }, 20, TimeUnit.SECONDS); // Simulation runs for 20 seconds
    }
}
