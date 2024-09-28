package medea;

import medea.command.Command;
import medea.core.Parser;
import medea.core.Storage;
import medea.core.TaskList;
import medea.core.Ui;
import medea.exceptions.MedeaException;

public class Medea {
    private static final String DEFAULT_FILE_PATH = "./data/medea.txt";
    private final Ui userInterface;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser commandParser;

    public Medea(String filePath) {
        userInterface = new Ui();
        storage = new Storage(filePath);
        commandParser = new Parser();
        taskList = loadTasks();
    }

    private TaskList loadTasks() {
        try {
            return new TaskList(storage.loadTasks());
        } catch (MedeaException e) {
            handleError(e);
            return new TaskList(); // Return an empty TaskList if loading fails
        }
    }

    public static void main(String[] args) {
        new Medea(DEFAULT_FILE_PATH).run();
    }

    public void run() {
        userInterface.showWelcome();
        handleUserCommands();
        userInterface.showFarewell();
        saveTasks();
    }

    private void handleUserCommands() {
        while (true) {
            try {
                String userCommand = userInterface.readCommand();
                Command command = commandParser.parse(userCommand);
                if (command.isExit()) return;
                executeCommand(command);
            } catch (MedeaException exception) {
                handleError(exception);
            }
        }
    }

    private void executeCommand(Command command) {
        wrapWithLine(() -> command.execute(taskList, userInterface, storage));
    }

    private void handleError(MedeaException exception) {
        wrapWithLine(() -> userInterface.showError(exception));
    }

    private void wrapWithLine(Runnable action) {
        userInterface.showLine();
        action.run();
        userInterface.showLine();
    }
    private void saveTasks() {
        storage.saveTasks(taskList.toCSVString());
    }
}
