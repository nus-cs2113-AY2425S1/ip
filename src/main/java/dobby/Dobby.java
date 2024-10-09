package dobby;

import dobby.parser.Parser;
import dobby.storage.Storage;
import dobby.tasklist.TaskList;
import dobby.ui.Ui;

import java.util.Scanner;

/**
 * The main class of the Dobby application. This class initializes the core components:
 * Storage, TaskList, Ui, and Parser. It loads tasks from storage, processes user commands,
 * and saves tasks back to storage upon exit.
 */
public class Dobby {

    /**
     * The entry point of the Dobby application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Parser parser = new Parser();

        storage.loadTasks(taskList.getTaskList());

        try (Scanner in = new Scanner(System.in)) {
            ui.printWelcomeMessage();
            String line;
            while (true) {
                line = in.nextLine().trim();
                if (line.equalsIgnoreCase("bye") || line.equalsIgnoreCase("sock")) {
                    break;
                }
                try {
                    parser.processCommand(line, taskList, ui, storage);
                } catch (Exception exception) {
                    ui.handleExceptions(exception);
                }
            }

            storage.saveTasks(taskList.getTaskList());
            ui.printGoodbyeMessage();
        }

    }


}
