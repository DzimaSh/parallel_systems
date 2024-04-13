import consumer.Consumer;
import producer.Producer;
import util.Buffer;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class MultithreadingBufferMain {
    public static void main(String[] args) {
        new MultithreadingBufferMain().test(new Buffer());
    }

    protected void test(Buffer buffer) {
        AtomicBoolean done = new AtomicBoolean(false);

        File inputFile = new File("file_reader/resources/input.txt");
        File outputFile = new File("file_reader/resources/output.txt");

        Producer producer = new Producer(buffer, inputFile, done);
        Consumer consumer = new Consumer(buffer, outputFile, done);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
