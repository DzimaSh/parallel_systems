package test;

import counter.DefaultCounter;

public class UnsynchronizedCounterTest extends CounterTest {
    public static void main(String[] args) throws InterruptedException {
        test(new DefaultCounter());
    }
}
