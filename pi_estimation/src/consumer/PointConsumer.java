package consumer;

import util.Point;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static util.PiEstimator.estimateForPi;

public class PointConsumer implements Callable<Long> {
    private final BlockingQueue<Point> queue;

    public PointConsumer(BlockingQueue<Point> queue) {
        this.queue = queue;
    }

    @Override
    public Long call() {
        long inCircleCount = 0L,
            i = 0L;
        while (true) {
            try {
                Point point = queue.poll(2, TimeUnit.SECONDS);
                if (point != null) {
                    i++;
//                    System.out.println("Thread: " + Thread.currentThread().getName() + ". Point " + i + " is taken from queue. Point: " + point);
                    if (point.isInCircle()) {
                        inCircleCount++;
                    }
                } else {
                    System.out.println(
                            "Thread: " + Thread.currentThread().getName() + ". " +
                                    i + " points are taken from queue. " +
                                    inCircleCount + " are in circle. Percentage for current thread: " + (double) inCircleCount / i);
                    return inCircleCount;
                }
            } catch (InterruptedException e) {
                return inCircleCount;
            }
        }
    }
}
