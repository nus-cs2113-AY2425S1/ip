package poppy;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The {@code Poppy} class represents the main application that manages tasks.
 * It handles the interaction with the user interface, storage of tasks,
 * and the overall execution of the application.
 */
public class Poppy {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new {@code Poppy} application with the specified file path for storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Poppy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            storage.createFileIfNotExists();
            taskList = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showMessage("File not found");
            taskList = new TaskList();
        } catch (IOException e) {
            ui.showMessage("Error updating file");
        }
    }

    /**
     * Runs the Poppy application, managing user interactions and command processing.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            String input = ui.getUserInput();
            try {
                Parser.processCommand(input, taskList);
                storage.save(taskList.getTasks());
            } catch (IOException e) {
                ui.showMessage(e.getMessage());
            }
            if (input.equals("Bye")) {
                isExit = true;
            }
        }
        ui.showGoodbye();
        ui.close();
    }

    /**
     * The entry point of the Poppy application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Poppy("./data/Poppy.txt").run();
    }
}

