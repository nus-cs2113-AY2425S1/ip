package XiaoMe;

import XiaoMe.commands.Command;
import XiaoMe.task.Task;

import java.util.ArrayList;

/**
 * The main class for the XiaoMe task management application.
 * It initializes the user interface, task storage, and handles the main application logic.
 */
public class XiaoMe {
    TaskList tasks;
    private UI ui;
    Storage storage;

    /**
     * The main method to run the XiaoMe application.
     *
     * @param args command-line arguments
     * @throws XiaoMeException if an error occurs during initialization
     */
    public static void main(String[] args) throws XiaoMeException {
        new XiaoMe().run();
    }

    /**
     * Constructs a XiaoMe object, initializes the user interface and storage,
     * and loads tasks from the storage.
     *
     * @throws XiaoMeException if an error occurs during task loading
     */
    public XiaoMe() throws XiaoMeException {
        ui = new UI();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.readFile());
        } catch (XiaoMeException e) {
            ui.printToUser(e.getError());
            tasks = new TaskList(); // start with empty list if error occurs when reading file
        }
    }

    /**
     * Runs the main application loop, allowing users to input commands and interact with tasks.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parseCommand(userInput);
                ui.printToUser(c.execute(tasks));
                isExit = c.isExit();
            } catch (XiaoMeException e) {
                ui.printToUser(e.getError());
            }
        }
    }
}
