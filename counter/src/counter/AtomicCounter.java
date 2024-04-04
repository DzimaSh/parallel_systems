package counter;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter implements Counter<Integer> {
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public void decrement() {
        counter.decrementAndGet();
    }

    @Override
    public Integer value() {
        return counter.get();
    }
}
