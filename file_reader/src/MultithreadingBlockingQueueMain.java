import util.Buffer;
import util.BufferAdapter;

import java.util.concurrent.ArrayBlockingQueue;

public class MultithreadingBlockingQueueMain extends MultithreadingBufferMain {

    public static void main(String[] args) {
        Buffer buffer = new BufferAdapter(new ArrayBlockingQueue<>(10));
        new MultithreadingBlockingQueueMain().test(buffer);
    }
}
