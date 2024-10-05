import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class containing the main method and driver of the code.
 * Initializes storage, the list of tasks, and a Gertrude object.
 * Runs the parser and saves to storage at each iteration.
 */
public class Gertrude {
    public static final String FILE_PATH = "data/gertrude.txt"; //"src/main/java/data/gertrude.txt";

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Gertrude
     * Initializes storage and tasks.
     * @param filePath path to save and load file to
     *
     */
    public Gertrude(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (GertrudeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        } catch (CorruptedFileException | IndexOutOfBoundsException e) {
            Ui.showCorruptionError();
            tasks = new TaskList();
        }
    }

    /**
     * Contains the driving while loop for the program.
     * At each loop, it checks that the program is still running and saves.
     * @throws RuntimeException if an IOException occurs
     */
    public void run() {
        Ui.printIntroduction();

        while (InputParser.parseInput(tasks)) {
            // Save every loop
            try {
                storage.saveFile(tasks);
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Couldn't save.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Creates a Gertrude object and calls run().
     * @param args isn't used
     */
    public static void main(String[] args) {
        new Gertrude(FILE_PATH).run();
    }

}
