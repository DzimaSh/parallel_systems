package consumer;

import util.Buffer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class Consumer implements Runnable {
    private final Buffer buffer;
    private final File outputFile;

    public Consumer(Buffer buffer, File outputFile) {
        this.buffer = buffer;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            while (true) {
                String word = buffer.get();
                if (Objects.isNull(word))
                    break; // EOF

                writer.print(new StringBuilder(word).reverse());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
