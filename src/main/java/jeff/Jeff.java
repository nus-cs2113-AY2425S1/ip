package jeff;

import jeff.command.Command;
import jeff.exception.InvalidFormatException;
import jeff.helper.Parser;
import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;
import java.io.IOException;

/**
 * The <code>Jeff</code> class represents the entry point, and is the chatbot.
 * It initializes the user interface, storage, and task list,
 * and manages the execution of user commands.
 */
public class Jeff {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a <code>Jeff</code> object with the specified file path.
     *
     * @param filePath The file path for loading and saving tasks.
     * @throws IOException If an input or output exception occurs while accessing the file.
     */
    public Jeff(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);

        try{
            tasks = new TaskList(storage.loadTaskList());
        } catch (InvalidFormatException errorMessage) {
            ui.showLoadingError(String.valueOf(errorMessage));
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the application, which continuously reads user commands,
     * processes them, and executes the corresponding actions until the exit command is given.
     *
     * @throws IOException If an input or output exception occurs during command processing.
     */
    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            String fullCommand = ui.readCommand();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            ui.showNewLine();
            ui.showDivider();
            ui.showNewLine();
        }
    }

    /**
     * The main method that starts the application.
     *
     * @param args Command line arguments (not used).
     * @throws IOException If an input or oustput exception occurs while running the application.
     */
    public static void main(String[] args) throws IOException {
        new Jeff("data/taskList.txt").run();
    }
}
