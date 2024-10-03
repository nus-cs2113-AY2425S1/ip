import CassHelpers.commands.Command;
import CassHelpers.exceptions.*;
import CassHelpers.util.Parser;
import CassHelpers.util.Storage;
import CassHelpers.util.TaskList;
import CassHelpers.util.UI;

/**
 * The Cassandra class is the main class for the task management application.
 * It manages user input, parses commands, and interacts with other components like TaskList, Storage, and UI.
 */
public class Cassandra {

    private UI ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for the Cassandra class.
     * It initializes the UI, Storage, and TaskList components.
     * If there is an issue loading tasks from the storage file, it catches the exception and initializes an empty task list.
     */
    public Cassandra() {
        ui = new UI();
        storage = new Storage("./data", "tasks.txt");
        try {
            tasks = new TaskList(storage.readTaskFromFile(), storage);
        } catch (CassandraException e) {
            ui.showError(e);
            ui.drawLine();
            tasks = new TaskList(storage);
        }
    }

    /**
     * Starts the Cassandra task management application.
     * It displays the introduction and runs a loop that processes user input until the user exits the app.
     */
    public void run() {
        ui.displayIntroduction();
        while (tasks.getRunningState()) {
            String userInput = Parser.getUserInput();
            String[] commandArgs = Parser.getCommandArgsFromUserInput(userInput);
            ui.drawLine();
            try {
                Command c = Parser.parse(userInput, commandArgs, ui, tasks);
                c.execute();
            } catch (TaskNotFoundException | NoTaskIndexFoundException | TaskAlreadyMarkedException |
                     NoCommandException | TaskAlreadyUnmarkedException | InvalidEventFormatException |
                     InvalidDeadlineFormatException | InvalidCommandException | NoTaskContainsMatchingPromptException |
                     InvalidDateFormatException | InvalidDateRangeException e) {
                ui.showError(e);
            }
            ui.drawLine();
        }
    }

    /**
     * The main entry point for the Cassandra application.
     * It creates an instance of the Cassandra class and starts the application by calling the run method.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Cassandra().run();
    }
}
