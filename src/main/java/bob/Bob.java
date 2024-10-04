package bob;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;
import bob.ui.Parser;
import bob.command.Command;

/**
 * Represents the main application class for the chatbot task management system.
 * It manages the interaction between the user interface, task storage,
 * and task list.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * Initialises the Bob application with the specified file path for storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Bob(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main loop of the application, allowing the user to input commands.
     * The application continues until the user chooses to exit.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showSeparatorLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                this.ui.showSeparatorLine();
            } catch (NullPointerException e) {
                this.ui.showSeparatorLine();
            }
        }
    }

    /**
     * The main method that starts the Bob application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Bob(FILE_PATH).run();
    }
}