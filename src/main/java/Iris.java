import data.Storage;
import exceptions.IrisException;
import task.TaskList;
import ui.Ui;
import command.Parser;
import command.Command;

public class Iris {
    private static final String STORAGE_FILE_PATH = "C:\\Users\\pingh\\OneDrive\\Desktop\\ip\\src\\main\\java\\data\\iris.txt";

    private static Storage storage;
    private TaskList tasks;
    private static Ui ui;

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

    public void run() {
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

    public static void main(String[] args) {
        new Iris(STORAGE_FILE_PATH).run();
    }
}
