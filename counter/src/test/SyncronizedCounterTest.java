package test;

import counter.SynchronizedCounter;

public class SyncronizedCounterTest extends CounterTest {
    public static void main(String[] args) throws InterruptedException {
        test(new SynchronizedCounter());
    }
}
