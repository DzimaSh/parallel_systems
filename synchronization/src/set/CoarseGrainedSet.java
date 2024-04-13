package set;

import java.util.HashSet;
import java.util.Set;

public class CoarseGrainedSet implements SynchronizedSet {
    private final Set<Integer> set = new HashSet<>();
    private final Object lock = new Object();

    public void add(int value) {
        synchronized(lock) {
            set.add(value);
        }
    }

    public boolean contains(int value) {
        synchronized(lock) {
            return set.contains(value);
        }
    }

    public void remove(int value) {
        synchronized(lock) {
            set.remove(value);
        }
    }
}
