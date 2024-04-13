package producer;

import util.Point;

import java.util.concurrent.BlockingQueue;

public class PointProducer implements Runnable {
    private final long pointsCount;
    private final BlockingQueue<Point> queue;

    public PointProducer(long pointsCount, BlockingQueue<Point> queue) {
        this.pointsCount = pointsCount;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (long i = 0; i < pointsCount; i++) {
            try {
                queue.put(new Point());
//                System.out.println("Thread: " + Thread.currentThread().getName() + ". Point " + i + " added to queue");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Thread: " + Thread.currentThread().getName() + ". Point count " + pointsCount);
    }
}
