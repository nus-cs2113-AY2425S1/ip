import exception.LiaException;
import UI.Ui;
import storage.Storage;
import task.TaskList;
import commands.Command;
import parser.Parser;

/**
 * Represents the main application for the Lia chatbot.
 * This application manages tasks and facilitates user interactions
 * through a command-line interface.
 */
public class Lia {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Lia chatbot instance with the specified file path for storage.
     *
     * @param filePath The path to the file where tasks will be loaded from or saved to.
     */
    public Lia(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LiaException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot, displaying the welcome message and continuously
     * accepting user input until an exit command is given.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                storage.save(tasks.getTasks());
                isExit = command.isExit();
            } catch (LiaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    /**
     * The main method that starts the Lia chatbot application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Lia("./data/lia.txt").run();
    }
}
