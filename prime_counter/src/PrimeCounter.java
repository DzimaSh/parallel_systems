import service.PrimeChecker;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class PrimeCounter {
    private final int RANGE = 50_000_000; // Заданный диапазон
    private final int COUNTERS_AMOUNT = 4;

    private final AtomicInteger counter = new AtomicInteger();
    private final AtomicInteger primeCounter = new AtomicInteger();

    public static void main(String[] args) {
        new PrimeCounter().start();
    }

    private void start() {
        long startTime = System.currentTimeMillis();
        List<Thread> counters = IntStream.range(0, COUNTERS_AMOUNT)
                .boxed()
                .map(i -> new Thread(new PrimeChecker(counter, primeCounter, RANGE)))
                .toList();

        counters.forEach(Thread::start);

        counters.forEach(counter -> {
            try {
                counter.join();
            } catch (InterruptedException e) {
                System.err.println("Thread " + Thread.currentThread().getName() + " interrupted");
            }
        });

        long endTime = System.currentTimeMillis();
        System.out.println("Простые чисел в диапазоне от 0 до " + RANGE + ": " + primeCounter.get());
        System.out.println("Time spent: " + (endTime - startTime) + "ms");
    }
}
