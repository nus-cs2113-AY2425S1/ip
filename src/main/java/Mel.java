import main.TaskList;
import main.Parser;
import main.Storage;
import main.Ui;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Mel {

    private static final String LIST_FILE_PATH = "data" + File.separator + "Mel.txt"; // Use File.separator for cross-platform compatibility

    private Storage storage;
    private TaskList userList;
    private Ui ui;
    private Scanner in;

    public Mel(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        // Set up scanner for user input
        in = new Scanner(System.in);
        userList = new TaskList(ui);
    }

    public void run() {
        ui.printIntroMessage();

        try {
            storage.writerSetUp();
            storage.loadDataFromFile(userList); // Load saved tasks
        } catch (IOException e) {
            System.out.println("An error occurred when setting up writer.");
        }

        Parser.getUserInput(in, storage, ui, userList);
    }

    public static void main(String[] args) throws IOException {
        new Mel(LIST_FILE_PATH).run();
    }
}
