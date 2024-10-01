import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Gertrude {
    public static final String FILE_PATH = "data/gertrude.txt"; //"src/main/java/data/gertrude.txt";

    private Storage storage;
    private TaskList tasks;

    public Gertrude(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (GertrudeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        } catch (CorruptedFileException | IndexOutOfBoundsException | IOException e) {
            Ui.showCorruptionError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.printIntroduction();

        boolean runLoop = true;
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

    public static void main(String[] args) {
        new Gertrude(FILE_PATH).run();
    }

}
