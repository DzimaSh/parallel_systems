import consumer.PointConsumer;
import producer.PointProducer;
import util.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MultiThreadedProgram {
    private final int POINTS_COUNT = 1_000_000_000;
    private final BlockingQueue<Point> queue = new ArrayBlockingQueue<>(POINTS_COUNT);

    public static void main(String[] args) {
        new MultiThreadedProgram().start();
    }

    private void start() {
        final int PRODUCERS_COUNT = 4;
        final int CONSUMERS_COUNT = 2;

        try (ExecutorService executor = Executors.newFixedThreadPool(PRODUCERS_COUNT + CONSUMERS_COUNT);) {
            startProducers(executor, PRODUCERS_COUNT);
            List<Future<Long>> futures = startConsumers(executor, CONSUMERS_COUNT);

            executor.shutdown();
            executor.awaitTermination(20, TimeUnit.SECONDS);

            double piEstimate = estimatePi(futures);
            System.out.println("Estimate for pi: " + piEstimate);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println(e.getMessage());
        }
    }

    private void startProducers(ExecutorService executor, int producersCount) {
        for (int i = 0; i < producersCount; i++) {
            executor.submit(new PointProducer((POINTS_COUNT / producersCount), queue));
        }
    }

    private List<Future<Long>> startConsumers(ExecutorService executor, int consumersCount) {
        List<Future<Long>> futures = new ArrayList<>();
        for (int i = 0; i < consumersCount; i++) {
            futures.add(executor.submit(new PointConsumer(queue)));
        }

        return futures;
    }

    private double estimatePi(List<Future<Long>> futures) throws ExecutionException, InterruptedException {
        int totalInsideCircle = 0;
        for (Future<Long> future : futures) {
            totalInsideCircle += future.get();
        }

        return  4.0 * totalInsideCircle / POINTS_COUNT;
    }
}
