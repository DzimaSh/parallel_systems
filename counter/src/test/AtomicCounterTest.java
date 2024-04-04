package test;

import counter.AtomicCounter;

public class AtomicCounterTest extends CounterTest {
    public static void main(String[] args) throws InterruptedException {
        test(new AtomicCounter());
    }
}
