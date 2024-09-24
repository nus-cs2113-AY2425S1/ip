package codecatalyst;

import codecatalyst.command.Command;

import java.io.IOException;

/**
 * The main class of the CodeCatalyst program, responsible for initializing and running the task manager.
 * It handles user commands, manages tasks, and interacts with the UI and storage.
 */
public class CodeCatalyst {
    private static final String FILE_PATH = "data/CodeCatalystData.txt";
    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * Constructs a {@code CodeCatalyst} object.
     * It initializes the UI, storage, and task list. If the task list cannot be loaded from the file, an empty task list is initialized.
     *
     * @param filepath The path of the file from which tasks are loaded.
     */
    public CodeCatalyst(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);

        try {
            tasklist = new TaskList(storage.loadTasksFromFile());
        } catch (IOException e){
            ui.printLoadingError();
            tasklist = new TaskList();
        }
    }

    /**
     * Starts and runs the CodeCatalyst task management system.
     * This method repeatedly reads user input, processes commands, and updates the task list until the exit command is given.
     */
    public void runCodeCatalyst() {
        ui.printGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printDivider();
                Command command = Parser.parse(fullCommand);
                command.execute(tasklist, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        new CodeCatalyst(FILE_PATH).runCodeCatalyst();
    }
}
