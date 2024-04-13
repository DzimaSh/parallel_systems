import set.CoarseGrainedSet;
import set.FineGrainedSet;
import set.SynchronizedSet;

public class SynchronizationTest {
    private final int numThreads = 10;
    private final int numOperations = 10000;

    public static void main(String[] args) {
        try {
            new SynchronizationTest().test();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void test() throws InterruptedException {
        System.out.println(
                "Грубая синхронизация заняла " + testAlgorithm(new CoarseGrainedSet(), numThreads, numOperations) + " наносекунд"
        );

        System.out.println(
                "Тонкая синхронизация заняла " + testAlgorithm(new FineGrainedSet(), numThreads, numOperations) + " наносекунд"
        );
    }

    private long testAlgorithm(
            SynchronizedSet set,
            int numThreads,
            int numOperations
    ) throws InterruptedException {
        Thread[] threads = new Thread[numThreads];
        long startTime = System.nanoTime();

        final Runnable test = () -> {
            for (int j = 0; j < numOperations; j++) {
                set.add(j);
                set.contains(j);
                set.remove(j);
            }
        };

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(test);
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}
