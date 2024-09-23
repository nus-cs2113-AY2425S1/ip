import data.Storage;
import exceptions.IrisException;
import task.TaskList;
import ui.Ui;
import command.Parser;
import command.Command;

/**
 * @author Tan Ping Hui
 */

public class Iris {
    private static final String STORAGE_FILE_PATH = "C:\\Users\\pingh\\OneDrive\\Desktop\\ip\\src\\main\\java\\data\\iris.txt";

    private static Storage storage;
    private TaskList tasks;
    private static Ui ui;

    /**
     * Instantiate Ui to start Scanner
     * Load TaskList from a text file at filePath
     * @param filePath is the text file path to load and save the task list
     */
    public Iris(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IrisException e) {
            Ui.showErrorMessage(e.getMessage());
            Ui.showDivider();
            tasks = new TaskList();
        }
    }

    private void run() {
        Ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Ui.showDivider();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (IrisException e) {
                Ui.showErrorMessage(e.getMessage());
            } finally {
                Ui.showDivider();
            }
        }
    }

    /**
     * Main loop to run program
     * @param args is not utilised in this version
     */
    public static void main(String[] args) {
        new Iris(STORAGE_FILE_PATH).run();
    }
}
