package counter;

public abstract class AbstractIntegerCounter implements Counter<Integer> {
    private int counter = 0;

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public void decrement() {
        counter--;
    }

    @Override
    public Integer value() {
        return counter;
    }
}
