package counter;

public class SynchronizedCounter extends DefaultCounter {
    @Override
    public synchronized void increment() {
        super.increment();
    }

    @Override
    public synchronized void decrement() {
        super.decrement();
    }
}
