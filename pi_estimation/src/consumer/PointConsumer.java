package consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import static util.PiEstimator.estimateForPi;

public class PointConsumer implements Runnable {
    private final BlockingQueue<double[]> queue;
    private final AtomicLong trialCount;
    private final AtomicLong inCircleCount;

    public PointConsumer(
            BlockingQueue<double[]> queue,
            AtomicLong trialCount,
            AtomicLong inCircleCount
    ) {
        this.queue = queue;
        this.trialCount = trialCount;
        this.inCircleCount = inCircleCount;
    }

    @Override
    public void run() {
        while (true) {
            try {
                double[] point = queue.take();
                double x = point[0];
                double y = point[1];
                trialCount.incrementAndGet();
                if (x*x + y*y < 1)
                    inCircleCount.incrementAndGet();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            double estimateForPi = estimateForPi(inCircleCount.get(), trialCount.get());
            System.out.println("Estimate for Pi: " + estimateForPi);
        }
    }
}
