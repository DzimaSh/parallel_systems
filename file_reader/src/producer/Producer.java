package producer;

import util.Buffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer implements Runnable {
    private final Buffer buffer;
    private final File inputFile;
    private final AtomicBoolean done;

    public Producer(Buffer buffer, File inputFile, AtomicBoolean done) {
        this.buffer = buffer;
        this.inputFile = inputFile;
        this.done = done;
    }

    @Override
    public void run () {
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNext()) {
                buffer.put(scanner.next());
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            buffer.put("");
            done.compareAndSet(false, true); // EOF
        }
    }
}
