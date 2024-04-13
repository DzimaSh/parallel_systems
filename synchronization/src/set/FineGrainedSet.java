package set;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class FineGrainedSet implements SynchronizedSet {
    private final ConcurrentHashMap<Integer, ReentrantLock> map =
            new ConcurrentHashMap<>();

    @Override
    public void add(int value) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            map.put(value, lock);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean contains(int value) {
        ReentrantLock lock = map.get(value);
        if (lock != null) {
            lock.lock();
            try {
                return map.containsKey(value);
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    @Override
    public void remove(int value) {
        ReentrantLock lock = map.get(value);
        if (lock != null) {
            lock.lock();
            try {
                map.remove(value);
            } finally {
                lock.unlock();
            }
        }
    }
}
