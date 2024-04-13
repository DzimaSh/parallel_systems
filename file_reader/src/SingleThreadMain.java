import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SingleThreadMain {
    public static void main(String[] args) {
        File inputFile = new File("file_reader/resources/input.txt");
        File outputFile = new File("file_reader/resources/output.txt");

        try (Scanner scanner = new Scanner(inputFile);
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                writer.print(new StringBuilder(word).reverse());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
