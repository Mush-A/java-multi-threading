package org.example.syncronized;

public class Syncronized {

    private static int counter = 0;
    private static int syncedCounter = 0;

    public static void main(String[] args) throws InterruptedException {

        // Create two threads, each incrementing the counters 10,000 times
        Thread one = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter++; // Increment the regular counter (not thread-safe)
                increment(); // Increment the synchronized counter
            }
        });

        Thread two = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter++; // Increment the regular counter (not thread-safe)
                increment(); // Increment the synchronized counter
            }
        });

        // Start both threads
        one.start();
        two.start();

        // Wait for both threads to finish before continuing
        one.join();
        two.join();

        // Print the values of the counters
        System.out.println(counter);
        System.out.println(syncedCounter);
    }

    private static synchronized void increment() {
        syncedCounter++;
    }
}

/**
 * private static synchronized void increment() {
 *    syncedCounter++;
 * }
 *
 * This method is synchronized, meaning only one thread can execute it at a time.
 * The synchronized keyword ensures that the method is thread-safe, preventing
 * race conditions by locking the class object for static methods (or the instance
 * for non-static methods) until the method execution completes. This ensures that
 * each increment operation on syncedCounter is performed atomically, with no other
 * thread able to interfere.
 *
 * Race Condition Explanation:
 * The code contains two counters:
 * - counter: Incremented directly by both threads without synchronization.
 *            As a result, it may not reliably reach 20,000 (10,000 increments
 *            from each thread) because the increment operation (counter++) is not
 *            atomic. It consists of three steps: read, increment, and write, which
 *            can be interleaved by multiple threads, leading to missed increments.
 *
 * - syncedCounter: Incremented inside a synchronized method, ensuring thread safety.
 *                  The synchronized keyword ensures that when one thread is executing
 *                  the increment() method, any other thread attempting to call it
 *                  will be blocked until the first thread finishes. This prevents race
 *                  conditions and ensures that syncedCounter always reaches 20,000.
 *
 * Thread Flow:
 * 1. Thread one and thread two start simultaneously, each incrementing the counters
 *    10,000 times.
 * 2. For counter++, since it is not synchronized, the final value may be less than
 *    20,000 due to race conditions.
 * 3. For syncedCounter++, the synchronization guarantees correct increments, resulting
 *    in a final value of exactly 20,000.
 *
 * ---------------------------------------------------------------------------------------------------------------------
 *
 * While this ensures thread safety for incrementing syncedCounter, there are some
 * drawbacks to using method-level synchronization:
 *
 * 1. Coarse-Grained Locking: The entire method is locked, even if only part of the
 *    code is critical. This can lead to unnecessary blocking and reduced concurrency,
 *    especially if the method contains non-critical sections that don't need to be
 *    synchronized.
 *
 * 2. Lock Contention: Multiple threads competing for the lock can create a bottleneck
 *    and reduce application performance, as only one thread can access the method at a time.
 *
 * 3. Static Methods: If the method is static, the lock is applied to the class-level
 *    object, causing all threads across different instances to be blocked when accessing
 *    synchronized static methods, increasing contention.
 *
 * To improve concurrency, consider using block-level synchronization, where only the
 * critical section of the code is synchronized, allowing non-critical code to execute
 * without blocking other threads.
 */