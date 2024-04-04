package thread;

public class ActionThread extends Thread {
    private final Runnable action;
    private final int times;

    public ActionThread(Runnable action, int times) {
        this.action = action;
        this.times = times;
    }

    public void run() {
        for (int i = 0; i < times; i++) {
            action.run();
        }
    }
}
