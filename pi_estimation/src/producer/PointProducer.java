package producer;

import java.util.concurrent.BlockingQueue;

public class PointProducer implements Runnable {

    private final long maxSize;
    private final BlockingQueue<double[]> queue;

    public PointProducer(long maxSize, BlockingQueue<double[]> queue) {
        this.maxSize = maxSize;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (long i = 0; i < maxSize; i++) {
            double x = Math.random();
            double y = Math.random();

            try {
                queue.put(new double[]{x, y});
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
