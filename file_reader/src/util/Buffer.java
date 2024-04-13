package util;

public class Buffer {
    private String word;
    private boolean available = false;

    public synchronized String get() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        available = false;
        notifyAll();
        return word;
    }

    public synchronized void put(String word) {
        while (available) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        this.word = word;
        available = true;
        notifyAll();
    }

    public synchronized boolean isEmpty() {
        return !available;
    }
}