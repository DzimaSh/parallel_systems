package counter;

public interface Counter<T> {

    void increment();
    void decrement();
    T value();
}
