import static util.PiEstimator.estimateForPi;

public class SequentialProgram {
    public static void main(String[] args) {
        long MAX_SIZE = Long.MAX_VALUE;
        long trialCount = 0;
        long inCircleCount = 0;

        for (long i = 0; i < MAX_SIZE; i++) {
            double x = Math.random();
            double y = Math.random();
            trialCount++;
            if (x*x + y*y < 1)
                inCircleCount++;
        }
        System.out.println("Estimate for Pi: " + estimateForPi(inCircleCount, trialCount));
    }
}
