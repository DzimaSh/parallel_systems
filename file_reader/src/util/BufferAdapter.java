package util;

import java.util.concurrent.BlockingQueue;

public class BufferAdapter extends Buffer {
    private final BlockingQueue<String> queue;

    public BufferAdapter(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public String get() {
        return queue.poll();
    }

    @Override
    public void put(String s) {
        queue.offer(s);
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
