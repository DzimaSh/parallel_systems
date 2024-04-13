package consumer;

import util.Buffer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable {
    private final Buffer buffer;
    private final File outputFile;
    private final AtomicBoolean done;

    public Consumer(Buffer buffer, File outputFile, AtomicBoolean done) {
        this.buffer = buffer;
        this.outputFile = outputFile;
        this.done = done;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            while (true) {
                String word = buffer.get();
                if (word.isEmpty())
                    break; // EOF

                writer.print(new StringBuilder(word).reverse());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
