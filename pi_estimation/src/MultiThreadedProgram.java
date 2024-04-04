import consumer.PointConsumer;
import producer.PointProducer;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadedProgram {
    private static final int MAX_SIZE = 100000000;
    private static final Thread[] PRODUCERS = new Thread[3];
    private static final Thread[] CONSUMERS = new Thread[3];
    private static final BlockingQueue<double[]> queue = new ArrayBlockingQueue<>(MAX_SIZE * PRODUCERS.length);

    private static final AtomicLong trialCount = new AtomicLong(0);
    private static final AtomicLong inCircleCount = new AtomicLong(0);

    public static void main(String[] args) {
        for (int i = 0; i < PRODUCERS.length; i++) {
            PRODUCERS[i] = new Thread(new PointProducer(MAX_SIZE, queue));
            PRODUCERS[i].start();
        }

        for (int i = 0; i < CONSUMERS.length; i++) {
            CONSUMERS[i] = new Thread(new PointConsumer(queue, trialCount, inCircleCount));
            CONSUMERS[i].start();
        }

        Arrays.stream(PRODUCERS)
                .forEach(producer -> {
                    try {
                        producer.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });

        Arrays.stream(CONSUMERS)
                .forEach(consumer -> {
                    try {
                        consumer.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }


}
