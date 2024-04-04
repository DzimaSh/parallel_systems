package util;

public class PiEstimator {
    public static double estimateForPi(long inCircleCount, long trialCount) {
        return 4 * ((double) inCircleCount / trialCount);
    }
}
