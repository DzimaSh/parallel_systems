package producer;

import util.Buffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Producer implements Runnable {
    private final Buffer buffer;
    private final File inputFile;

    public Producer(Buffer buffer, File inputFile) {
        this.buffer = buffer;
        this.inputFile = inputFile;
    }

    @Override
    public void run () {
        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNext()) {
                buffer.put(scanner.next());
            }
            buffer.put(null); // EOF
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
