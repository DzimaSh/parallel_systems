import consumer.Consumer;
import producer.Producer;
import util.Buffer;

import java.io.File;

public class MultithreadingBufferMain {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        File inputFile = new File("file_reader/resources/input.txt");
        File outputFile = new File("file_reader/resources/output.txt");

        Producer producer = new Producer(buffer, inputFile);
        Consumer consumer = new Consumer(buffer, outputFile);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
