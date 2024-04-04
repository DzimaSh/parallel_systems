package test;

import counter.Counter;
import counter.DefaultCounter;
import thread.ActionThread;

public class UnsynchronizedCounterTest {
    public static void main(String[] args) throws InterruptedException {
        Counter<Integer> counter = new DefaultCounter();
        int times = 10000;

        Thread incrementThread = new ActionThread(counter::increment, times);
        Thread decrementThread = new ActionThread(counter::decrement, times);

        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

        System.out.println("Actual counter value: " + counter.value());
        System.out.println("Expected counter value: " + 0);
    }
}
