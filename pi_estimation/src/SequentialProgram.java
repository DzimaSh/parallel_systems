import static util.PiEstimator.estimateForPi;

public class SequentialProgram {
    public static void main(String[] args) {
        long MAX_SIZE = 36_854_775_807L;
        long trialCount = 0;
        long inCircleCount = 0;

        for (long i = 0; i < MAX_SIZE; i++) {
            double x = Math.random();
            double y = Math.random();
            trialCount++;
            if (x*x + y*y < 1)
                inCircleCount++;

            if (i % 1_000_000_000L == 0)
                System.out.println("Iteration " + i);
        }
        System.out.println("Estimate for Pi: " + estimateForPi(inCircleCount, trialCount));
    }
}
