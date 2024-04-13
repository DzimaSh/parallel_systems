package service;

import java.util.concurrent.atomic.AtomicInteger;

import static util.Util.isPrime;

public class PrimeChecker implements Runnable {

    private final AtomicInteger counter;
    private final AtomicInteger primeCounter;
    private final int range;

    public PrimeChecker(AtomicInteger counter, AtomicInteger primeCounter, int range) {
        this.counter = counter;
        this.primeCounter = primeCounter;
        this.range = range;
    }

    @Override
    public void run() {
        while (counter.get() < range) {
            int currentNumber = counter.getAndIncrement();
            if (isPrime(currentNumber)) {
                primeCounter.incrementAndGet();
            }
        }
    }
}
